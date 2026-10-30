package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudGrupoEquivalenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudGrupoEquivalenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.planificacion.entity.GrupoHabilitadoEquivalencia;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudGrupoEquivalente;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SolicitudGrupoEquivalenteMapper;
import pe.edu.upeu.lamb.planificacion.repository.GrupoHabilitadoEquivalenciaRepository;
import pe.edu.upeu.lamb.planificacion.repository.SolicitudGrupoEquivalenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudGrupoEquivalenteService {

    private final SolicitudGrupoEquivalenteRepository solicitudGrupoEquivalenteRepository;
    private final GrupoHabilitadoEquivalenciaRepository grupoHabilitadoEquivalenciaRepository;
    private final SolicitudGrupoEquivalenteMapper solicitudGrupoEquivalenteMapper;

    @Transactional(readOnly = true)
    public List<SolicitudGrupoEquivalenteResponse> listar(Long idGrupoHabilitado) {
        List<SolicitudGrupoEquivalente> lista = idGrupoHabilitado == null
                ? solicitudGrupoEquivalenteRepository.findAll()
                : solicitudGrupoEquivalenteRepository.findByGrupoHabilitadoId(idGrupoHabilitado);
        return lista.stream().map(solicitudGrupoEquivalenteMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SolicitudGrupoEquivalenteResponse obtener(Long id) {
        return solicitudGrupoEquivalenteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SolicitudGrupoEquivalenteResponse crear(SolicitudGrupoEquivalenteRequest request, Long idSolicitante) {
        validar(request, null);
        if (solicitudGrupoEquivalenteRepository.existsByGrupoHabilitadoIdAndIdSolicitanteAndEstadoNot(
                request.getIdGrupoHabilitado(), idSolicitante, EstadoSolicitud.RECHAZADA)) {
            throw new BusinessRuleException("El estudiante " + idSolicitante + " ya tiene una solicitud para ese grupo habilitado");
        }
        SolicitudGrupoEquivalente solicitudGrupoEquivalente = solicitudGrupoEquivalenteMapper.toEntity(request);
        asignarRelaciones(solicitudGrupoEquivalente, request);
        solicitudGrupoEquivalente.setEstado(EstadoSolicitud.PENDIENTE);
        solicitudGrupoEquivalente.setIdSolicitante(idSolicitante);
        solicitudGrupoEquivalente.setFechaSolicitud(LocalDateTime.now());
        return solicitudGrupoEquivalenteMapper.toResponse(solicitudGrupoEquivalenteRepository.save(solicitudGrupoEquivalente));
    }

    @Transactional
    public SolicitudGrupoEquivalenteResponse actualizar(Long id, SolicitudGrupoEquivalenteRequest request) {
        SolicitudGrupoEquivalente solicitudGrupoEquivalente = buscarOFallar(id);
        validarPendiente(solicitudGrupoEquivalente);
        validar(request, id);
        solicitudGrupoEquivalente.setJustificacion(request.getJustificacion().trim());
        asignarRelaciones(solicitudGrupoEquivalente, request);
        return solicitudGrupoEquivalenteMapper.toResponse(solicitudGrupoEquivalenteRepository.save(solicitudGrupoEquivalente));
    }

    @Transactional
    public void eliminar(Long id) {
        SolicitudGrupoEquivalente solicitudGrupoEquivalente = buscarOFallar(id);
        validarPendiente(solicitudGrupoEquivalente);
        solicitudGrupoEquivalenteRepository.delete(solicitudGrupoEquivalente);
    }

    // Solo una solicitud PENDIENTE se responde; quien responde sale del JWT
    @Transactional
    public SolicitudGrupoEquivalenteResponse responder(Long id, RespuestaSolicitudRequest request, Long idResponsable) {
        SolicitudGrupoEquivalente solicitudGrupoEquivalente = buscarOFallar(id);
        validarPendiente(solicitudGrupoEquivalente);
        if (Boolean.TRUE.equals(request.getAprobada())) {
            aplicarAprobacion(solicitudGrupoEquivalente);
        }
        solicitudGrupoEquivalente.setEstado(Boolean.TRUE.equals(request.getAprobada()) ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        solicitudGrupoEquivalente.setObservacionRespuesta(request.getObservacion());
        solicitudGrupoEquivalente.setIdRespondidoPor(idResponsable);
        solicitudGrupoEquivalente.setFechaRespuesta(LocalDateTime.now());
        return solicitudGrupoEquivalenteMapper.toResponse(solicitudGrupoEquivalenteRepository.save(solicitudGrupoEquivalente));
    }

    SolicitudGrupoEquivalente buscarOFallar(Long id) {
        return solicitudGrupoEquivalenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud para cursar en grupo equivalente no encontrada: " + id));
    }

    private void validar(SolicitudGrupoEquivalenteRequest request, Long id) {
    }

    private void asignarRelaciones(SolicitudGrupoEquivalente solicitudGrupoEquivalente, SolicitudGrupoEquivalenteRequest request) {
        solicitudGrupoEquivalente.setGrupoHabilitado(grupoHabilitadoEquivalenciaRepository.findById(request.getIdGrupoHabilitado())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo habilitado por equivalencia no encontrado: " + request.getIdGrupoHabilitado())));
    }

    private void validarPendiente(SolicitudGrupoEquivalente solicitudGrupoEquivalente) {
        if (solicitudGrupoEquivalente.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + solicitudGrupoEquivalente.getId() + " esta " + solicitudGrupoEquivalente.getEstado() + " y ya no se puede modificar");
        }
    }

    private void aplicarAprobacion(SolicitudGrupoEquivalente solicitudGrupoEquivalente) {
        // Candado sobre el grupo habilitado: dos aprobaciones a la vez no gastan el mismo ultimo cupo
        Long idGrupoHabilitado = solicitudGrupoEquivalente.getGrupoHabilitado().getId();
        GrupoHabilitadoEquivalencia habilitado = grupoHabilitadoEquivalenciaRepository.findByIdParaActualizar(idGrupoHabilitado)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo habilitado por equivalencia no encontrado: " + idGrupoHabilitado));
        if (habilitado.getCuposDisponibles() <= 0) {
            throw new BusinessRuleException("El grupo habilitado " + idGrupoHabilitado + " ya no tiene cupos por equivalencia");
        }
        habilitado.setCuposDisponibles(habilitado.getCuposDisponibles() - 1);
    }
}
