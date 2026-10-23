package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.entity.Acta;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoActa;
import pe.edu.upeu.lamb.evaluacion.event.ActaEvento;
import pe.edu.upeu.lamb.evaluacion.event.SeccionAbiertaEvento;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.messaging.EvaluacionEventosProducer;
import pe.edu.upeu.lamb.evaluacion.entity.Nota;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.repository.ActaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActaService {

    private static final String ACTA_ABIERTA = "acta.abierta";
    private static final String ACTA_RECHAZADA = "acta.rechazada";
    private static final String ACTA_CERRADA = "acta.cerrada";
    private static final BigDecimal NOTA_APROBATORIA = new BigDecimal("10.5");
    private static final String MOTIVO_SIN_DOCENTE = "la seccion no tiene docente responsable asignado";

    private final ActaRepository actaRepository;
    private final EvaluacionEventosProducer producer;
    private final NotaRepository notaRepository;

    @Value("${spring.application.name}")
    private String nombreServicio;

    @Transactional(readOnly = true)
    public List<Acta> listar() {
        return actaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Acta obtener(Long id) {
        return actaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acta no encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public Acta obtenerPorSeccion(Long idSeccion) {
        return actaRepository.findByIdSeccion(idSeccion)
                .orElseThrow(() -> new ResourceNotFoundException("La seccion " + idSeccion + " no tiene acta"));
    }

    /**
     * Paso 2 de la saga. Consumidor idempotente en dos capas:
     * 1) revision explicita (findByIdSeccion) para el reintento normal de Kafka,
     * 2) restriccion UNIQUE uk_actas_seccion como respaldo ante una carrera real.
     */
    @Transactional
    public void procesar(SeccionAbiertaEvento seccion) {
        if (actaRepository.findByIdSeccion(seccion.getSeccionId()).isPresent()) {
            log.warn("component=processor seccionId={} status=ignored motivo=\"acta ya procesada (evento duplicado)\"",
                    seccion.getSeccionId());
            return;
        }

        // Regla de negocio: sin docente responsable no se abre el registro de notas
        boolean rechazada = seccion.getDocente() == null || seccion.getDocente().isBlank();
        EstadoActa estadoResultante = rechazada ? EstadoActa.RECHAZADA : EstadoActa.ABIERTA;

        Acta acta;
        try {
            acta = actaRepository.saveAndFlush(Acta.builder()
                    .idSeccion(seccion.getSeccionId())
                    .codigoCurso(seccion.getCodigoCurso())
                    .periodo(seccion.getPeriodo())
                    .docente(seccion.getDocente())
                    .creditos(seccion.getCreditos())
                    .estado(estadoResultante)
                    .motivo(rechazada ? MOTIVO_SIN_DOCENTE : null)
                    .fechaRegistro(LocalDateTime.now())
                    .build());
        } catch (DataIntegrityViolationException ex) {
            log.warn("component=processor seccionId={} status=ignored motivo=\"acta ya procesada (condicion de carrera)\"",
                    seccion.getSeccionId());
            return;
        }

        producer.publicarTrasCommit(ActaEvento.builder()
                .tipoEvento(rechazada ? ACTA_RECHAZADA : ACTA_ABIERTA)
                .actaId(acta.getId())
                .seccionId(acta.getIdSeccion())
                .estado(acta.getEstado().name())
                .motivo(acta.getMotivo())
                .origen(nombreServicio)
                .timestamp(Instant.now().toEpochMilli())
                .build());

        log.info("component=processor seccionId={} actaId={} estado={} status=processed",
                acta.getIdSeccion(), acta.getId(), acta.getEstado());
    }

    /**
     * Cierre del acta: nota final = promedio de las notas de cada estudiante en la seccion.
     * Publica acta.cerrada con los resultados; lo consume trayectoria para armar el historial.
     */
    @Transactional
    public Acta cerrar(Long id) {
        Acta acta = obtener(id);
        if (acta.getEstado() != EstadoActa.ABIERTA) {
            throw new BusinessRuleException("El acta " + id + " esta " + acta.getEstado() + "; solo se cierra un acta ABIERTA");
        }
        List<Nota> notas = notaRepository.findByIdSeccion(acta.getIdSeccion());
        if (notas.isEmpty()) {
            throw new BusinessRuleException("La seccion " + acta.getIdSeccion() + " no tiene notas registradas");
        }

        Map<Long, List<Nota>> porEstudiante = notas.stream()
                .collect(Collectors.groupingBy(Nota::getIdPersona, TreeMap::new, Collectors.toList()));
        List<ActaEvento.ResultadoNota> resultados = porEstudiante.entrySet().stream()
                .map(e -> {
                    BigDecimal suma = e.getValue().stream().map(Nota::getCalificacion).reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal promedio = suma.divide(BigDecimal.valueOf(e.getValue().size()), 2, RoundingMode.HALF_UP);
                    return new ActaEvento.ResultadoNota(e.getKey(), promedio, promedio.compareTo(NOTA_APROBATORIA) >= 0);
                })
                .toList();

        acta.setEstado(EstadoActa.CERRADA);
        acta.setFechaCierre(LocalDateTime.now());

        producer.publicarTrasCommit(ActaEvento.builder()
                .tipoEvento(ACTA_CERRADA)
                .actaId(acta.getId())
                .seccionId(acta.getIdSeccion())
                .estado(acta.getEstado().name())
                .codigoCurso(acta.getCodigoCurso())
                .periodo(acta.getPeriodo())
                .creditos(acta.getCreditos())
                .resultados(resultados)
                .origen(nombreServicio)
                .timestamp(Instant.now().toEpochMilli())
                .build());

        log.info("component=processor actaId={} seccionId={} estudiantes={} estado={} status=processed",
                acta.getId(), acta.getIdSeccion(), resultados.size(), acta.getEstado());
        return acta;
    }
}
