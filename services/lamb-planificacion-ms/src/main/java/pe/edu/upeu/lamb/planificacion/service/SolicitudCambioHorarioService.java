package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioHorarioRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioHorarioResponse;
import pe.edu.upeu.lamb.planificacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.planificacion.entity.HorarioGrupo;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudCambioHorario;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SolicitudCambioHorarioMapper;
import pe.edu.upeu.lamb.planificacion.repository.HorarioGrupoRepository;
import pe.edu.upeu.lamb.planificacion.repository.SolicitudCambioHorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudCambioHorarioService {

    private final SolicitudCambioHorarioRepository solicitudCambioHorarioRepository;
    private final HorarioGrupoRepository horarioGrupoRepository;
    private final SolicitudCambioHorarioMapper solicitudCambioHorarioMapper;
    private final HorarioGrupoService horarioGrupoService;

    @Transactional(readOnly = true)
    public List<SolicitudCambioHorarioResponse> listar(Long idHorario) {
        List<SolicitudCambioHorario> lista = idHorario == null
                ? solicitudCambioHorarioRepository.findAll()
                : solicitudCambioHorarioRepository.findByHorarioId(idHorario);
        return lista.stream().map(solicitudCambioHorarioMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SolicitudCambioHorarioResponse obtener(Long id) {
        return solicitudCambioHorarioMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SolicitudCambioHorarioResponse crear(SolicitudCambioHorarioRequest request, Long idSolicitante) {
        validar(request, null);
        SolicitudCambioHorario solicitudCambioHorario = solicitudCambioHorarioMapper.toEntity(request);
        asignarRelaciones(solicitudCambioHorario, request);
        solicitudCambioHorario.setEstado(EstadoSolicitud.PENDIENTE);
        solicitudCambioHorario.setIdSolicitante(idSolicitante);
        solicitudCambioHorario.setFechaSolicitud(LocalDateTime.now());
        return solicitudCambioHorarioMapper.toResponse(solicitudCambioHorarioRepository.save(solicitudCambioHorario));
    }

    @Transactional
    public SolicitudCambioHorarioResponse actualizar(Long id, SolicitudCambioHorarioRequest request) {
        SolicitudCambioHorario solicitudCambioHorario = buscarOFallar(id);
        validarPendiente(solicitudCambioHorario);
        validar(request, id);
        solicitudCambioHorario.setDiaSemana(request.getDiaSemana());
        solicitudCambioHorario.setHoraInicio(request.getHoraInicio());
        solicitudCambioHorario.setHoraFin(request.getHoraFin());
        solicitudCambioHorario.setJustificacion(request.getJustificacion().trim());
        asignarRelaciones(solicitudCambioHorario, request);
        return solicitudCambioHorarioMapper.toResponse(solicitudCambioHorarioRepository.save(solicitudCambioHorario));
    }

    @Transactional
    public void eliminar(Long id) {
        SolicitudCambioHorario solicitudCambioHorario = buscarOFallar(id);
        validarPendiente(solicitudCambioHorario);
        solicitudCambioHorarioRepository.delete(solicitudCambioHorario);
    }

    // Solo una solicitud PENDIENTE se responde; quien responde sale del JWT
    @Transactional
    public SolicitudCambioHorarioResponse responder(Long id, RespuestaSolicitudRequest request, Long idResponsable) {
        SolicitudCambioHorario solicitudCambioHorario = buscarOFallar(id);
        validarPendiente(solicitudCambioHorario);
        if (Boolean.TRUE.equals(request.getAprobada())) {
            aplicarAprobacion(solicitudCambioHorario);
        }
        solicitudCambioHorario.setEstado(Boolean.TRUE.equals(request.getAprobada()) ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        solicitudCambioHorario.setObservacionRespuesta(request.getObservacion());
        solicitudCambioHorario.setIdRespondidoPor(idResponsable);
        solicitudCambioHorario.setFechaRespuesta(LocalDateTime.now());
        return solicitudCambioHorarioMapper.toResponse(solicitudCambioHorarioRepository.save(solicitudCambioHorario));
    }

    SolicitudCambioHorario buscarOFallar(Long id) {
        return solicitudCambioHorarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de cambio de horario no encontrada: " + id));
    }

    private void validar(SolicitudCambioHorarioRequest request, Long id) {
        if (!request.getHoraFin().isAfter(request.getHoraInicio())) {
            throw new BusinessRuleException("Hora fin debe ser posterior a hora inicio");
        }
    }

    private void asignarRelaciones(SolicitudCambioHorario solicitudCambioHorario, SolicitudCambioHorarioRequest request) {
        solicitudCambioHorario.setHorario(horarioGrupoRepository.findById(request.getIdHorario())
                .orElseThrow(() -> new ResourceNotFoundException("Horario de grupo no encontrado: " + request.getIdHorario())));
    }

    private void validarPendiente(SolicitudCambioHorario solicitudCambioHorario) {
        if (solicitudCambioHorario.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + solicitudCambioHorario.getId() + " esta " + solicitudCambioHorario.getEstado() + " y ya no se puede modificar");
        }
    }

    private void aplicarAprobacion(SolicitudCambioHorario solicitudCambioHorario) {
        // Misma validacion de cruces y candados que la apertura del grupo
        HorarioGrupo horario = solicitudCambioHorario.getHorario();
        horarioGrupoService.reprogramar(horario, horario.getAmbiente().getId(), solicitudCambioHorario.getDiaSemana(),
                solicitudCambioHorario.getHoraInicio(), solicitudCambioHorario.getHoraFin());
    }
}
