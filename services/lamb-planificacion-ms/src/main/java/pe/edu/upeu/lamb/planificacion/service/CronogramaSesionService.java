package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.CronogramaSesionRequest;
import pe.edu.upeu.lamb.planificacion.dto.CronogramaSesionResponse;
import pe.edu.upeu.lamb.planificacion.entity.CronogramaSesion;
import pe.edu.upeu.lamb.planificacion.entity.SesionClase;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.CronogramaSesionMapper;
import pe.edu.upeu.lamb.planificacion.repository.CronogramaSesionRepository;
import pe.edu.upeu.lamb.planificacion.repository.SesionClaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CronogramaSesionService {

    private final CronogramaSesionRepository cronogramaSesionRepository;
    private final SesionClaseRepository sesionClaseRepository;
    private final CronogramaSesionMapper cronogramaSesionMapper;

    @Transactional(readOnly = true)
    public List<CronogramaSesionResponse> listar(Long idSesion) {
        List<CronogramaSesion> lista = idSesion == null
                ? cronogramaSesionRepository.findAll()
                : cronogramaSesionRepository.findBySesionId(idSesion);
        return lista.stream().map(cronogramaSesionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CronogramaSesionResponse obtener(Long id) {
        return cronogramaSesionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CronogramaSesionResponse crear(CronogramaSesionRequest request) {
        validar(request, null);
        CronogramaSesion cronogramaSesion = cronogramaSesionMapper.toEntity(request);
        asignarRelaciones(cronogramaSesion, request);
        return cronogramaSesionMapper.toResponse(cronogramaSesionRepository.save(cronogramaSesion));
    }

    @Transactional
    public CronogramaSesionResponse actualizar(Long id, CronogramaSesionRequest request) {
        CronogramaSesion cronogramaSesion = buscarOFallar(id);
        validar(request, id);
        cronogramaSesion.setFechaProgramada(request.getFechaProgramada());
        cronogramaSesion.setFechaEjecutada(request.getFechaEjecutada());
        cronogramaSesion.setEstado(request.getEstado());
        cronogramaSesion.setObservacion(request.getObservacion());
        asignarRelaciones(cronogramaSesion, request);
        return cronogramaSesionMapper.toResponse(cronogramaSesionRepository.save(cronogramaSesion));
    }

    @Transactional
    public void eliminar(Long id) {
        cronogramaSesionRepository.delete(buscarOFallar(id));
    }

    CronogramaSesion buscarOFallar(Long id) {
        return cronogramaSesionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cronograma de sesion no encontrado: " + id));
    }

    private void validar(CronogramaSesionRequest request, Long id) {
    }

    private void asignarRelaciones(CronogramaSesion cronogramaSesion, CronogramaSesionRequest request) {
        cronogramaSesion.setSesion(sesionClaseRepository.findById(request.getIdSesion())
                .orElseThrow(() -> new ResourceNotFoundException("Sesion de clase no encontrada: " + request.getIdSesion())));
    }
}
