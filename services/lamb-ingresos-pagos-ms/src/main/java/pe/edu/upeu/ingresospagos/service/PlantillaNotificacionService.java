package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.PlantillaNotificacionRequest;
import pe.edu.upeu.ingresospagos.dto.PlantillaNotificacionResponse;
import pe.edu.upeu.ingresospagos.entity.PlantillaNotificacion;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.PlantillaNotificacionMapper;
import pe.edu.upeu.ingresospagos.repository.PlantillaNotificacionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantillaNotificacionService {

    private final PlantillaNotificacionRepository plantillaNotificacionRepository;
    private final PlantillaNotificacionMapper plantillaNotificacionMapper;

    public List<PlantillaNotificacionResponse> findAll() {
        return plantillaNotificacionRepository.findAll().stream()
                .map(plantillaNotificacionMapper::toResponse)
                .toList();
    }

    public PlantillaNotificacionResponse findById(Long id) {
        return plantillaNotificacionMapper.toResponse(obtenerPorId(id));
    }

    public PlantillaNotificacionResponse create(PlantillaNotificacionRequest request) {
        PlantillaNotificacion plantillaNotificacion = plantillaNotificacionMapper.toEntity(request);
        return plantillaNotificacionMapper.toResponse(plantillaNotificacionRepository.save(plantillaNotificacion));
    }

    public PlantillaNotificacionResponse update(Long id, PlantillaNotificacionRequest request) {
        PlantillaNotificacion plantillaNotificacion = obtenerPorId(id);
        plantillaNotificacion.setCodigo(request.getCodigo());
        plantillaNotificacion.setAsunto(request.getAsunto());
        plantillaNotificacion.setCuerpo(request.getCuerpo());
        plantillaNotificacion.setCanal(request.getCanal());
        plantillaNotificacion.setActivo(request.getActivo());
        return plantillaNotificacionMapper.toResponse(plantillaNotificacionRepository.save(plantillaNotificacion));
    }

    public void delete(Long id) {
        plantillaNotificacionRepository.delete(obtenerPorId(id));
    }

    private PlantillaNotificacion obtenerPorId(Long id) {
        return plantillaNotificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plantilla de notificacion no encontrada con id: " + id));
    }
}
