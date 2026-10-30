package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.entity.Acta;
import pe.edu.upeu.lamb.evaluacion.entity.EscalaCalificacion;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoActa;
import pe.edu.upeu.lamb.evaluacion.event.ActaEvento;
import pe.edu.upeu.lamb.evaluacion.event.GrupoAbiertoEvento;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.messaging.EvaluacionEventosProducer;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.repository.ActaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActaService {

    private static final String ACTA_ABIERTA = "acta.abierta";
    private static final String ACTA_RECHAZADA = "acta.rechazada";
    private static final String ACTA_CERRADA = "acta.cerrada";
    private static final String MOTIVO_SIN_DOCENTE = "el grupo no tiene docente titular asignado";

    private final ActaRepository actaRepository;
    private final EvaluacionEventosProducer producer;
    private final CalculoNotasService calculoNotasService;

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
    public Acta obtenerPorGrupo(Long idGrupo) {
        return actaRepository.findByIdGrupo(idGrupo)
                .orElseThrow(() -> new ResourceNotFoundException("El grupo " + idGrupo + " no tiene acta"));
    }

    /**
     * Paso 2 de la saga. Consumidor idempotente en dos capas:
     * 1) revision explicita (findByIdGrupo) para el reintento normal de Kafka,
     * 2) restriccion UNIQUE uk_actas_grupo como respaldo ante una carrera real.
     */
    @Transactional
    public void procesar(GrupoAbiertoEvento grupo) {
        if (actaRepository.findByIdGrupo(grupo.getGrupoId()).isPresent()) {
            log.warn("component=processor grupoId={} status=ignored motivo=\"acta ya procesada (evento duplicado)\"",
                    grupo.getGrupoId());
            return;
        }

        // Regla de negocio: sin docente titular no se abre el registro de notas
        boolean rechazada = grupo.getIdDocenteTitular() == null;
        EstadoActa estadoResultante = rechazada ? EstadoActa.RECHAZADA : EstadoActa.ABIERTA;

        Acta acta;
        try {
            acta = actaRepository.saveAndFlush(Acta.builder()
                    .idGrupo(grupo.getGrupoId())
                    .codigoCurso(grupo.getCodigoCurso())
                    .periodo(grupo.getPeriodo())
                    .idDocente(grupo.getIdDocenteTitular())
                    .creditos(grupo.getCreditos())
                    .estado(estadoResultante)
                    .motivo(rechazada ? MOTIVO_SIN_DOCENTE : null)
                    .fechaRegistro(LocalDateTime.now())
                    .build());
        } catch (DataIntegrityViolationException ex) {
            log.warn("component=processor grupoId={} status=ignored motivo=\"acta ya procesada (condicion de carrera)\"",
                    grupo.getGrupoId());
            return;
        }

        producer.publicarTrasCommit(ActaEvento.builder()
                .tipoEvento(rechazada ? ACTA_RECHAZADA : ACTA_ABIERTA)
                .actaId(acta.getId())
                .grupoId(acta.getIdGrupo())
                .estado(acta.getEstado().name())
                .motivo(acta.getMotivo())
                .origen(nombreServicio)
                .timestamp(Instant.now().toEpochMilli())
                .build());

        log.info("component=processor grupoId={} actaId={} estado={} status=processed",
                acta.getIdGrupo(), acta.getId(), acta.getEstado());
    }

    /**
     * Cierre del acta: nota final de cada estudiante segun CalculoNotasService.
     * Publica acta.cerrada con los resultados; lo consume trayectoria para armar el historial.
     */
    @Transactional
    public Acta cerrar(Long id) {
        Acta acta = obtener(id);
        if (acta.getEstado() != EstadoActa.ABIERTA) {
            throw new BusinessRuleException("El acta " + id + " esta " + acta.getEstado() + "; solo se cierra un acta ABIERTA");
        }
        Map<Long, BigDecimal> finales = calculoNotasService.notasFinales(acta.getIdGrupo());
        if (finales.isEmpty()) {
            throw new BusinessRuleException("El grupo " + acta.getIdGrupo() + " no tiene notas registradas");
        }
        // Nota final ponderada por componente (CRUD 2) y aprobada segun la escala vigente (CRUD 3)
        EscalaCalificacion escala = calculoNotasService.escalaVigente();
        List<ActaEvento.ResultadoNota> resultados = finales.entrySet().stream()
                .map(e -> new ActaEvento.ResultadoNota(e.getKey(), e.getValue(), calculoNotasService.aprobado(e.getValue(), escala)))
                .toList();

        acta.setEstado(EstadoActa.CERRADA);
        acta.setFechaCierre(LocalDateTime.now());

        producer.publicarTrasCommit(ActaEvento.builder()
                .tipoEvento(ACTA_CERRADA)
                .actaId(acta.getId())
                .grupoId(acta.getIdGrupo())
                .estado(acta.getEstado().name())
                .codigoCurso(acta.getCodigoCurso())
                .periodo(acta.getPeriodo())
                .creditos(acta.getCreditos())
                .resultados(resultados)
                .origen(nombreServicio)
                .timestamp(Instant.now().toEpochMilli())
                .build());

        log.info("component=processor actaId={} grupoId={} estudiantes={} estado={} status=processed",
                acta.getId(), acta.getIdGrupo(), resultados.size(), acta.getEstado());
        return acta;
    }
}
