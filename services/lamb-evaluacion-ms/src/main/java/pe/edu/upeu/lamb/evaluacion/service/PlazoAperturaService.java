package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.PlazoAperturaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.PlazoAperturaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.PlazoApertura;
import pe.edu.upeu.lamb.evaluacion.entity.SolicitudAperturaNotas;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.PlazoAperturaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.PlazoAperturaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.SolicitudAperturaNotasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlazoAperturaService {

    private final PlazoAperturaRepository plazoAperturaRepository;
    private final SolicitudAperturaNotasRepository solicitudAperturaNotasRepository;
    private final PlazoAperturaMapper plazoAperturaMapper;

    @Transactional(readOnly = true)
    public List<PlazoAperturaResponse> listar(Long idGrupo) {
        List<PlazoApertura> lista = idGrupo == null
                ? plazoAperturaRepository.findAll()
                : plazoAperturaRepository.findByIdGrupo(idGrupo);
        return lista.stream().map(plazoAperturaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PlazoAperturaResponse obtener(Long id) {
        return plazoAperturaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public PlazoAperturaResponse crear(PlazoAperturaRequest request) {
        validar(request, null);
        PlazoApertura plazoApertura = plazoAperturaMapper.toEntity(request);
        asignarRelaciones(plazoApertura, request);
        return plazoAperturaMapper.toResponse(plazoAperturaRepository.save(plazoApertura));
    }

    @Transactional
    public PlazoAperturaResponse actualizar(Long id, PlazoAperturaRequest request) {
        PlazoApertura plazoApertura = buscarOFallar(id);
        validar(request, id);
        plazoApertura.setIdGrupo(request.getIdGrupo());
        plazoApertura.setIdDocente(request.getIdDocente());
        plazoApertura.setFechaInicio(request.getFechaInicio());
        plazoApertura.setFechaFin(request.getFechaFin());
        plazoApertura.setMotivo(request.getMotivo());
        asignarRelaciones(plazoApertura, request);
        return plazoAperturaMapper.toResponse(plazoAperturaRepository.save(plazoApertura));
    }

    @Transactional
    public void eliminar(Long id) {
        plazoAperturaRepository.delete(buscarOFallar(id));
    }

    PlazoApertura buscarOFallar(Long id) {
        return plazoAperturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plazo de apertura habilitado no encontrado: " + id));
    }

    private void validar(PlazoAperturaRequest request, Long id) {
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha fin no puede ser anterior a fecha inicio");
        }
    }

    private void asignarRelaciones(PlazoApertura plazoApertura, PlazoAperturaRequest request) {
        plazoApertura.setSolicitud(request.getIdSolicitud() == null ? null : solicitudAperturaNotasRepository.findById(request.getIdSolicitud())
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de apertura para notas rezagadas no encontrada: " + request.getIdSolicitud())));
    }
}
