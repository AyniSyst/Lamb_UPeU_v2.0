package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.SeccionRequest;
import pe.edu.upeu.lamb.planificacion.dto.SeccionResponse;
import pe.edu.upeu.lamb.planificacion.dto.VigenciaCursoDto;
import pe.edu.upeu.lamb.planificacion.entity.EstadoSeccion;
import pe.edu.upeu.lamb.planificacion.entity.Seccion;
import pe.edu.upeu.lamb.planificacion.event.SeccionAbiertaEvento;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SeccionMapper;
import pe.edu.upeu.lamb.planificacion.messaging.PlanificacionEventosProducer;
import pe.edu.upeu.lamb.planificacion.repository.SeccionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeccionService {

    private static final String SECCION_ABIERTA = "seccion.abierta";

    private final SeccionRepository seccionRepository;
    private final SeccionMapper seccionMapper;
    private final CursoService cursoService;
    private final CurriculoConsultaService curriculoConsultaService;
    private final PlanificacionEventosProducer producer;

    @Value("${spring.application.name}")
    private String nombreServicio;

    public List<SeccionResponse> listar() {
        return seccionRepository.findAllConCurso().stream()
                .map(seccionMapper::toResponse)
                .toList();
    }

    public SeccionResponse obtener(Long id) {
        return seccionMapper.toResponse(buscarOFallar(id));
    }

    /**
     * Paso 1 de la saga: valida el curso contra curriculo (Feign + Circuit Breaker) y,
     * si esta en un plan vigente, publica seccion.abierta para que evaluacion abra el acta.
     */
    @Transactional
    public SeccionResponse crear(SeccionRequest request) {
        Seccion seccion = seccionMapper.toEntity(request);
        seccion.setCurso(cursoService.buscarOFallar(request.getCursoId()));

        VigenciaCursoDto vigencia = curriculoConsultaService.consultarVigencia(seccion.getCurso().getCodigo());
        if (vigencia != null && !vigencia.isEnPlanVigente()) {
            throw new BusinessRuleException("El curso " + seccion.getCurso().getCodigo() + " no esta en ningun plan de estudios vigente");
        }

        if (vigencia == null) {
            seccion.setEstado(EstadoSeccion.PENDIENTE_VALIDACION);
            seccion.setMotivo("curriculo no disponible: falta validar el curso contra el plan vigente");
            Seccion guardada = seccionRepository.save(seccion);
            log.warn("component=processor seccionId={} estado={} status=processed", guardada.getId(), guardada.getEstado());
            return seccionMapper.toResponse(guardada);
        }

        seccion.setEstado(EstadoSeccion.PENDIENTE_ACTA);
        Seccion guardada = seccionRepository.save(seccion);
        publicarSeccionAbierta(guardada);
        log.info("component=processor seccionId={} estado={} status=processed", guardada.getId(), guardada.getEstado());
        return seccionMapper.toResponse(guardada);
    }

    // Reintento manual de una seccion que quedo PENDIENTE_VALIDACION mientras curriculo estaba caido
    @Transactional
    public SeccionResponse revalidar(Long id) {
        Seccion seccion = buscarOFallar(id);
        if (seccion.getEstado() != EstadoSeccion.PENDIENTE_VALIDACION) {
            throw new BusinessRuleException("La seccion " + id + " esta " + seccion.getEstado() + " y no necesita revalidarse");
        }
        VigenciaCursoDto vigencia = curriculoConsultaService.consultarVigencia(seccion.getCurso().getCodigo());
        if (vigencia == null) {
            throw new BusinessRuleException("curriculo sigue sin responder; la seccion " + id + " continua PENDIENTE_VALIDACION");
        }
        if (!vigencia.isEnPlanVigente()) {
            throw new BusinessRuleException("El curso " + seccion.getCurso().getCodigo() + " no esta en ningun plan de estudios vigente");
        }
        pasarAPendienteActa(seccion);
        return seccionMapper.toResponse(seccion);
    }

    // plan.publicado: las secciones pendientes cuyo curso entro en el plan pasan a PENDIENTE_ACTA
    @Transactional
    public void revalidarPendientes(List<String> codigosCursos) {
        if (codigosCursos == null || codigosCursos.isEmpty()) {
            return;
        }
        List<Seccion> pendientes = seccionRepository.findByEstadoAndCursoCodigoIn(EstadoSeccion.PENDIENTE_VALIDACION, codigosCursos);
        if (pendientes.isEmpty()) {
            log.info("component=processor status=ignored motivo=\"no hay secciones PENDIENTE_VALIDACION para el plan publicado\"");
        }
        pendientes.forEach(this::pasarAPendienteActa);
    }

    /**
     * acta.abierta: PENDIENTE_ACTA -> ABIERTA. La guardia de estado hace que un evento repetido no tenga efecto.
     */
    @Transactional
    public void marcarAbierta(Long seccionId) {
        Seccion seccion = seccionRepository.findById(seccionId).orElse(null);
        if (seccion == null || seccion.getEstado() != EstadoSeccion.PENDIENTE_ACTA) {
            log.warn("component=processor seccionId={} status=ignored motivo=\"la seccion no existe o ya no esta pendiente de acta\"", seccionId);
            return;
        }
        seccion.setEstado(EstadoSeccion.ABIERTA);
        seccion.setMotivo(null);
        log.info("component=processor seccionId={} estado={} status=processed", seccionId, seccion.getEstado());
    }

    /**
     * Compensacion (acta.rechazada): PENDIENTE_ACTA -> ANULADA. No borra la seccion: deja rastro de que existio y por que fallo.
     * Misma guardia que marcarAbierta: un acta.rechazada repetido se ignora.
     */
    @Transactional
    public void compensar(Long seccionId, String motivo) {
        Seccion seccion = seccionRepository.findById(seccionId).orElse(null);
        if (seccion == null || seccion.getEstado() != EstadoSeccion.PENDIENTE_ACTA) {
            log.warn("component=processor seccionId={} status=ignored motivo=\"la seccion no existe o ya no esta pendiente de acta\"", seccionId);
            return;
        }
        seccion.setEstado(EstadoSeccion.ANULADA);
        seccion.setMotivo(motivo);
        log.info("component=processor seccionId={} estado={} status=compensated", seccionId, seccion.getEstado());
    }

    public SeccionResponse actualizar(Long id, SeccionRequest request) {
        Seccion seccion = buscarOFallar(id);
        seccion.setPeriodo(request.getPeriodo());
        seccion.setDocente(request.getDocente());
        seccion.setCupoMaximo(request.getCupoMaximo());
        seccion.setHorario(request.getHorario());
        seccion.setCurso(cursoService.buscarOFallar(request.getCursoId()));
        return seccionMapper.toResponse(seccionRepository.save(seccion));
    }

    public void eliminar(Long id) {
        seccionRepository.delete(buscarOFallar(id));
    }

    Seccion buscarOFallar(Long id) {
        return seccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seccion no encontrada: " + id));
    }

    private void pasarAPendienteActa(Seccion seccion) {
        seccion.setEstado(EstadoSeccion.PENDIENTE_ACTA);
        seccion.setMotivo(null);
        publicarSeccionAbierta(seccion);
        log.info("component=processor seccionId={} estado={} status=revalidated", seccion.getId(), seccion.getEstado());
    }

    private void publicarSeccionAbierta(Seccion seccion) {
        producer.publicarTrasCommit(SeccionAbiertaEvento.builder()
                .tipoEvento(SECCION_ABIERTA)
                .seccionId(seccion.getId())
                .codigoCurso(seccion.getCurso().getCodigo())
                .periodo(seccion.getPeriodo())
                .docente(seccion.getDocente())
                .cupoMaximo(seccion.getCupoMaximo())
                .creditos(seccion.getCurso().getCreditos())
                .origen(nombreServicio)
                .timestamp(Instant.now().toEpochMilli())
                .build());
    }
}
