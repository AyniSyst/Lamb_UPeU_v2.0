package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudAperturaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudAperturaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.RespuestaSolicitudApertura;
import pe.edu.upeu.lamb.evaluacion.entity.SolicitudAperturaNotas;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.RespuestaSolicitudAperturaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.RespuestaSolicitudAperturaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.SolicitudAperturaNotasRepository;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.evaluacion.entity.PlazoApertura;
import pe.edu.upeu.lamb.evaluacion.repository.PlazoAperturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RespuestaSolicitudAperturaService {

    private final RespuestaSolicitudAperturaRepository respuestaSolicitudAperturaRepository;
    private final SolicitudAperturaNotasRepository solicitudAperturaNotasRepository;
    private final RespuestaSolicitudAperturaMapper respuestaSolicitudAperturaMapper;
    private final SolicitudAperturaNotasService solicitudAperturaNotasService;
    private final PlazoAperturaRepository plazoAperturaRepository;

    @Transactional(readOnly = true)
    public List<RespuestaSolicitudAperturaResponse> listar(Long idSolicitud) {
        List<RespuestaSolicitudApertura> lista = idSolicitud == null
                ? respuestaSolicitudAperturaRepository.findAll()
                : respuestaSolicitudAperturaRepository.findBySolicitudId(idSolicitud);
        return lista.stream().map(respuestaSolicitudAperturaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RespuestaSolicitudAperturaResponse obtener(Long id) {
        return respuestaSolicitudAperturaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RespuestaSolicitudAperturaResponse crear(RespuestaSolicitudAperturaRequest request, Long idResponsable) {
        validar(request, null);
        RespuestaSolicitudApertura respuestaSolicitudApertura = respuestaSolicitudAperturaMapper.toEntity(request);
        asignarRelaciones(respuestaSolicitudApertura, request);
        respuestaSolicitudApertura.setIdResponsable(idResponsable);
        respuestaSolicitudApertura.setFecha(LocalDateTime.now());

        SolicitudAperturaNotas solicitud = respuestaSolicitudApertura.getSolicitud();
        solicitudAperturaNotasService.validarPendiente(solicitud);
        boolean aprobada = Boolean.TRUE.equals(request.getAprobada());
        solicitud.setEstado(aprobada ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        solicitud.setObservacionRespuesta(request.getObservacion());
        solicitud.setIdRespondidoPor(idResponsable);
        solicitud.setFechaRespuesta(respuestaSolicitudApertura.getFecha());
        if (aprobada) {
            plazoAperturaRepository.save(PlazoApertura.builder()
                    .idGrupo(solicitud.getIdGrupo())
                    .idDocente(solicitud.getIdSolicitante())
                    .solicitud(solicitud)
                    .fechaInicio(solicitud.getFechaInicioPropuesta())
                    .fechaFin(solicitud.getFechaFinPropuesta())
                    .motivo(solicitud.getMotivo())
                    .build());
        }
        return respuestaSolicitudAperturaMapper.toResponse(respuestaSolicitudAperturaRepository.save(respuestaSolicitudApertura));
    }

    @Transactional
    public RespuestaSolicitudAperturaResponse actualizar(Long id, RespuestaSolicitudAperturaRequest request) {
        RespuestaSolicitudApertura respuestaSolicitudApertura = buscarOFallar(id);
        if (!respuestaSolicitudApertura.getAprobada().equals(request.getAprobada())
                || !respuestaSolicitudApertura.getSolicitud().getId().equals(request.getIdSolicitud())) {
            throw new BusinessRuleException("Una respuesta registrada no cambia de decision ni de solicitud; solo se corrige la observacion");
        }
        respuestaSolicitudApertura.setObservacion(request.getObservacion());
        return respuestaSolicitudAperturaMapper.toResponse(respuestaSolicitudAperturaRepository.save(respuestaSolicitudApertura));
    }

    @Transactional
    public void eliminar(Long id) {
        buscarOFallar(id);
        throw new BusinessRuleException("La respuesta " + id + " ya resolvio su solicitud y queda como registro: no se elimina");
    }

    RespuestaSolicitudApertura buscarOFallar(Long id) {
        return respuestaSolicitudAperturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta a la solicitud no encontrada: " + id));
    }

    private void validar(RespuestaSolicitudAperturaRequest request, Long id) {
        boolean duplicado = id == null
                ? respuestaSolicitudAperturaRepository.existsBySolicitudId(request.getIdSolicitud())
                : respuestaSolicitudAperturaRepository.existsBySolicitudIdAndIdNot(request.getIdSolicitud(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de respuesta a la solicitud para esa combinacion de: solicitud");
        }
    }

    private void asignarRelaciones(RespuestaSolicitudApertura respuestaSolicitudApertura, RespuestaSolicitudAperturaRequest request) {
        respuestaSolicitudApertura.setSolicitud(solicitudAperturaNotasRepository.findById(request.getIdSolicitud())
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de apertura para notas rezagadas no encontrada: " + request.getIdSolicitud())));
    }
}
