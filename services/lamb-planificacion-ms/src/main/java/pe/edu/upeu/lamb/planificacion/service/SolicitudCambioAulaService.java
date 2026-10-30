package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioAulaRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioAulaResponse;
import pe.edu.upeu.lamb.planificacion.entity.Ambiente;
import pe.edu.upeu.lamb.planificacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.planificacion.entity.HorarioGrupo;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudCambioAula;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SolicitudCambioAulaMapper;
import pe.edu.upeu.lamb.planificacion.repository.AmbienteRepository;
import pe.edu.upeu.lamb.planificacion.repository.HorarioGrupoRepository;
import pe.edu.upeu.lamb.planificacion.repository.SolicitudCambioAulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudCambioAulaService {

    private final SolicitudCambioAulaRepository solicitudCambioAulaRepository;
    private final HorarioGrupoRepository horarioGrupoRepository;
    private final AmbienteRepository ambienteRepository;
    private final SolicitudCambioAulaMapper solicitudCambioAulaMapper;
    private final HorarioGrupoService horarioGrupoService;

    @Transactional(readOnly = true)
    public List<SolicitudCambioAulaResponse> listar(Long idHorario) {
        List<SolicitudCambioAula> lista = idHorario == null
                ? solicitudCambioAulaRepository.findAll()
                : solicitudCambioAulaRepository.findByHorarioId(idHorario);
        return lista.stream().map(solicitudCambioAulaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SolicitudCambioAulaResponse obtener(Long id) {
        return solicitudCambioAulaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SolicitudCambioAulaResponse crear(SolicitudCambioAulaRequest request, Long idSolicitante) {
        validar(request, null);
        SolicitudCambioAula solicitudCambioAula = solicitudCambioAulaMapper.toEntity(request);
        asignarRelaciones(solicitudCambioAula, request);
        solicitudCambioAula.setEstado(EstadoSolicitud.PENDIENTE);
        solicitudCambioAula.setIdSolicitante(idSolicitante);
        solicitudCambioAula.setFechaSolicitud(LocalDateTime.now());
        return solicitudCambioAulaMapper.toResponse(solicitudCambioAulaRepository.save(solicitudCambioAula));
    }

    @Transactional
    public SolicitudCambioAulaResponse actualizar(Long id, SolicitudCambioAulaRequest request) {
        SolicitudCambioAula solicitudCambioAula = buscarOFallar(id);
        validarPendiente(solicitudCambioAula);
        validar(request, id);
        solicitudCambioAula.setJustificacion(request.getJustificacion().trim());
        asignarRelaciones(solicitudCambioAula, request);
        return solicitudCambioAulaMapper.toResponse(solicitudCambioAulaRepository.save(solicitudCambioAula));
    }

    @Transactional
    public void eliminar(Long id) {
        SolicitudCambioAula solicitudCambioAula = buscarOFallar(id);
        validarPendiente(solicitudCambioAula);
        solicitudCambioAulaRepository.delete(solicitudCambioAula);
    }

    // Solo una solicitud PENDIENTE se responde; quien responde sale del JWT
    @Transactional
    public SolicitudCambioAulaResponse responder(Long id, RespuestaSolicitudRequest request, Long idResponsable) {
        SolicitudCambioAula solicitudCambioAula = buscarOFallar(id);
        validarPendiente(solicitudCambioAula);
        if (Boolean.TRUE.equals(request.getAprobada())) {
            aplicarAprobacion(solicitudCambioAula);
        }
        solicitudCambioAula.setEstado(Boolean.TRUE.equals(request.getAprobada()) ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        solicitudCambioAula.setObservacionRespuesta(request.getObservacion());
        solicitudCambioAula.setIdRespondidoPor(idResponsable);
        solicitudCambioAula.setFechaRespuesta(LocalDateTime.now());
        return solicitudCambioAulaMapper.toResponse(solicitudCambioAulaRepository.save(solicitudCambioAula));
    }

    SolicitudCambioAula buscarOFallar(Long id) {
        return solicitudCambioAulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de cambio de aula no encontrada: " + id));
    }

    private void validar(SolicitudCambioAulaRequest request, Long id) {
    }

    private void asignarRelaciones(SolicitudCambioAula solicitudCambioAula, SolicitudCambioAulaRequest request) {
        solicitudCambioAula.setHorario(horarioGrupoRepository.findById(request.getIdHorario())
                .orElseThrow(() -> new ResourceNotFoundException("Horario de grupo no encontrado: " + request.getIdHorario())));
        solicitudCambioAula.setAmbienteNuevo(ambienteRepository.findById(request.getIdAmbienteNuevo())
                .orElseThrow(() -> new ResourceNotFoundException("Ambiente no encontrado: " + request.getIdAmbienteNuevo())));
    }

    private void validarPendiente(SolicitudCambioAula solicitudCambioAula) {
        if (solicitudCambioAula.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + solicitudCambioAula.getId() + " esta " + solicitudCambioAula.getEstado() + " y ya no se puede modificar");
        }
    }

    private void aplicarAprobacion(SolicitudCambioAula solicitudCambioAula) {
        // Misma validacion de cruces, aforo y candados que la apertura del grupo
        HorarioGrupo horario = solicitudCambioAula.getHorario();
        horarioGrupoService.reprogramar(horario, solicitudCambioAula.getAmbienteNuevo().getId(), horario.getDiaSemana(),
                horario.getHoraInicio(), horario.getHoraFin());
    }
}
