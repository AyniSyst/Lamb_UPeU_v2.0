package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.JustificacionInasistenciaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.JustificacionInasistenciaResponse;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.evaluacion.entity.JustificacionInasistencia;
import pe.edu.upeu.lamb.evaluacion.entity.RegistroAsistencia;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.JustificacionInasistenciaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.JustificacionInasistenciaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.RegistroAsistenciaRepository;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoAsistencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JustificacionInasistenciaService {

    private final JustificacionInasistenciaRepository justificacionInasistenciaRepository;
    private final RegistroAsistenciaRepository registroAsistenciaRepository;
    private final JustificacionInasistenciaMapper justificacionInasistenciaMapper;

    @Transactional(readOnly = true)
    public List<JustificacionInasistenciaResponse> listar(Long idRegistro) {
        List<JustificacionInasistencia> lista = idRegistro == null
                ? justificacionInasistenciaRepository.findAll()
                : justificacionInasistenciaRepository.findByRegistroId(idRegistro);
        return lista.stream().map(justificacionInasistenciaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public JustificacionInasistenciaResponse obtener(Long id) {
        return justificacionInasistenciaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public JustificacionInasistenciaResponse crear(JustificacionInasistenciaRequest request, Long idSolicitante) {
        validar(request, null);
        JustificacionInasistencia justificacionInasistencia = justificacionInasistenciaMapper.toEntity(request);
        asignarRelaciones(justificacionInasistencia, request);
        justificacionInasistencia.setEstado(EstadoSolicitud.PENDIENTE);
        justificacionInasistencia.setIdSolicitante(idSolicitante);
        justificacionInasistencia.setFechaSolicitud(LocalDateTime.now());
        return justificacionInasistenciaMapper.toResponse(justificacionInasistenciaRepository.save(justificacionInasistencia));
    }

    @Transactional
    public JustificacionInasistenciaResponse actualizar(Long id, JustificacionInasistenciaRequest request) {
        JustificacionInasistencia justificacionInasistencia = buscarOFallar(id);
        validarPendiente(justificacionInasistencia);
        validar(request, id);
        justificacionInasistencia.setMotivo(request.getMotivo().trim());
        justificacionInasistencia.setDocumentoUrl(request.getDocumentoUrl());
        asignarRelaciones(justificacionInasistencia, request);
        return justificacionInasistenciaMapper.toResponse(justificacionInasistenciaRepository.save(justificacionInasistencia));
    }

    @Transactional
    public void eliminar(Long id) {
        JustificacionInasistencia justificacionInasistencia = buscarOFallar(id);
        validarPendiente(justificacionInasistencia);
        justificacionInasistenciaRepository.delete(justificacionInasistencia);
    }

    // Solo una solicitud PENDIENTE se responde; quien responde sale del JWT
    @Transactional
    public JustificacionInasistenciaResponse responder(Long id, RespuestaSolicitudRequest request, Long idResponsable) {
        JustificacionInasistencia justificacionInasistencia = buscarOFallar(id);
        validarPendiente(justificacionInasistencia);
        if (Boolean.TRUE.equals(request.getAprobada())) {
            aplicarAprobacion(justificacionInasistencia);
        }
        justificacionInasistencia.setEstado(Boolean.TRUE.equals(request.getAprobada()) ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        justificacionInasistencia.setObservacionRespuesta(request.getObservacion());
        justificacionInasistencia.setIdRespondidoPor(idResponsable);
        justificacionInasistencia.setFechaRespuesta(LocalDateTime.now());
        return justificacionInasistenciaMapper.toResponse(justificacionInasistenciaRepository.save(justificacionInasistencia));
    }

    JustificacionInasistencia buscarOFallar(Long id) {
        return justificacionInasistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Justificacion de inasistencia no encontrada: " + id));
    }

    private void validar(JustificacionInasistenciaRequest request, Long id) {
    }

    private void asignarRelaciones(JustificacionInasistencia justificacionInasistencia, JustificacionInasistenciaRequest request) {
        justificacionInasistencia.setRegistro(registroAsistenciaRepository.findById(request.getIdRegistro())
                .orElseThrow(() -> new ResourceNotFoundException("Registro de asistencia no encontrado: " + request.getIdRegistro())));
        RegistroAsistencia registro = registroAsistenciaRepository.findById(request.getIdRegistro())
                .orElseThrow(() -> new ResourceNotFoundException("Registro de asistencia no encontrado: " + request.getIdRegistro()));
        if (registro.getEstado() != EstadoAsistencia.FALTA) {
            throw new BusinessRuleException("Solo se justifica una FALTA; el registro " + registro.getId() + " esta " + registro.getEstado());
        }
    }

    private void validarPendiente(JustificacionInasistencia justificacionInasistencia) {
        if (justificacionInasistencia.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + justificacionInasistencia.getId() + " esta " + justificacionInasistencia.getEstado() + " y ya no se puede modificar");
        }
    }

    private void aplicarAprobacion(JustificacionInasistencia justificacionInasistencia) {
        justificacionInasistencia.getRegistro().setEstado(EstadoAsistencia.JUSTIFICADA);
    }
}
