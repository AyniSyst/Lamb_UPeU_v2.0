package pe.edu.upeu.lamb.trayectoria.service;

import pe.edu.upeu.lamb.trayectoria.dto.TrayectoriaDataResponse;
import pe.edu.upeu.lamb.trayectoria.dto.TrayectoriaResponse;
import pe.edu.upeu.lamb.trayectoria.entity.EstadoTrayectoria;
import pe.edu.upeu.lamb.trayectoria.entity.HistorialCurso;
import pe.edu.upeu.lamb.trayectoria.entity.Trayectoria;
import pe.edu.upeu.lamb.trayectoria.event.ActaCerradaEvento;
import pe.edu.upeu.lamb.trayectoria.event.PostulanteAdmitidoEvento;
import pe.edu.upeu.lamb.trayectoria.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.trayectoria.mapper.TrayectoriaMapper;
import pe.edu.upeu.lamb.trayectoria.repository.HistorialCursoRepository;
import pe.edu.upeu.lamb.trayectoria.repository.TrayectoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrayectoriaService {

    private final TrayectoriaRepository trayectoriaRepository;
    private final HistorialCursoRepository historialRepository;
    private final TrayectoriaMapper trayectoriaMapper;

    @Transactional(readOnly = true)
    public List<TrayectoriaDataResponse> listar() {
        return trayectoriaRepository.findAll().stream().map(trayectoriaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TrayectoriaResponse obtenerPorPersona(Long idPersona) {
        Trayectoria trayectoria = trayectoriaRepository.findByIdPersona(idPersona)
                .orElseThrow(() -> new ResourceNotFoundException("La persona " + idPersona + " no tiene trayectoria academica"));
        return trayectoriaMapper.toResponse(trayectoria, historialRepository.findByIdPersonaOrderByPeriodoAscCodigoCursoAsc(idPersona));
    }

    /**
     * postulante.admitido: abre la trayectoria. Idempotente en dos capas:
     * findByIdPersona antes de actuar y uk_trayectorias_persona como respaldo.
     */
    @Transactional
    public void registrarIngreso(PostulanteAdmitidoEvento evento) {
        if (trayectoriaRepository.findByIdPersona(evento.getIdPersona()).isPresent()) {
            log.warn("component=processor idPersona={} status=ignored motivo=\"trayectoria ya registrada (evento duplicado)\"",
                    evento.getIdPersona());
            return;
        }
        try {
            Trayectoria t = trayectoriaRepository.saveAndFlush(Trayectoria.builder()
                    .idPersona(evento.getIdPersona())
                    .idPrograma(evento.getIdPrograma())
                    .idPostulacion(evento.getPostulacionId())
                    .periodoIngreso(evento.getPeriodo())
                    .estado(EstadoTrayectoria.ACTIVO)
                    .creditosAprobados(0)
                    .fechaRegistro(LocalDateTime.now())
                    .build());
            log.info("component=processor idPersona={} trayectoriaId={} estado={} status=processed",
                    t.getIdPersona(), t.getId(), t.getEstado());
        } catch (DataIntegrityViolationException ex) {
            log.warn("component=processor idPersona={} status=ignored motivo=\"trayectoria ya registrada (condicion de carrera)\"",
                    evento.getIdPersona());
        }
    }

    /**
     * acta.cerrada: agrega una fila de historial por estudiante y recalcula su resumen.
     * Cada fila se protege con existsByIdPersonaAndIdGrupo y con uk_historial_persona_grupo.
     */
    @Transactional
    public void registrarActa(ActaCerradaEvento acta) {
        if (acta.getResultados() == null || acta.getResultados().isEmpty()) {
            log.warn("component=processor actaId={} status=ignored motivo=\"acta sin resultados\"", acta.getActaId());
            return;
        }
        int nuevos = 0;
        for (ActaCerradaEvento.ResultadoNota r : acta.getResultados()) {
            if (historialRepository.existsByIdPersonaAndIdGrupo(r.getIdPersona(), acta.getGrupoId())) {
                log.warn("component=processor idPersona={} grupoId={} status=ignored motivo=\"nota ya registrada en el historial (evento duplicado)\"",
                        r.getIdPersona(), acta.getGrupoId());
                continue;
            }
            historialRepository.save(HistorialCurso.builder()
                    .idPersona(r.getIdPersona())
                    .idGrupo(acta.getGrupoId())
                    .idActa(acta.getActaId())
                    .codigoCurso(acta.getCodigoCurso())
                    .periodo(acta.getPeriodo())
                    .creditos(acta.getCreditos() == null ? 0 : acta.getCreditos())
                    .notaFinal(r.getNotaFinal())
                    .aprobado(r.isAprobado())
                    .fechaRegistro(LocalDateTime.now())
                    .build());
            recalcularResumen(r.getIdPersona());
            nuevos++;
            log.info("component=processor idPersona={} curso={} notaFinal={} aprobado={} status=processed",
                    r.getIdPersona(), acta.getCodigoCurso(), r.getNotaFinal(), r.isAprobado());
        }
        log.info("component=processor actaId={} registrados={} ignorados={}", acta.getActaId(), nuevos, acta.getResultados().size() - nuevos);
    }

    // Resumen derivado del historial: creditos aprobados y promedio ponderado por creditos
    private void recalcularResumen(Long idPersona) {
        trayectoriaRepository.findByIdPersona(idPersona).ifPresentOrElse(t -> {
            historialRepository.flush();
            List<HistorialCurso> historial = historialRepository.findByIdPersonaOrderByPeriodoAscCodigoCursoAsc(idPersona);
            int creditosAprobados = historial.stream().filter(HistorialCurso::isAprobado).mapToInt(HistorialCurso::getCreditos).sum();
            int creditosTotales = historial.stream().mapToInt(HistorialCurso::getCreditos).sum();
            BigDecimal suma = historial.stream()
                    .map(h -> h.getNotaFinal().multiply(BigDecimal.valueOf(h.getCreditos())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            t.setCreditosAprobados(creditosAprobados);
            t.setPromedioPonderado(creditosTotales == 0 ? null : suma.divide(BigDecimal.valueOf(creditosTotales), 2, RoundingMode.HALF_UP));
        }, () -> log.warn("component=processor idPersona={} status=pending motivo=\"nota registrada sin trayectoria abierta (falta postulante.admitido)\"", idPersona));
    }
}
