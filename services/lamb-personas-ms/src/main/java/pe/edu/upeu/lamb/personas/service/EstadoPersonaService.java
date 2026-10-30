package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.EstadoPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.EstadoPersonaResponse;
import pe.edu.upeu.lamb.personas.entity.EstadoPersona;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.EstadoPersonaMapper;
import pe.edu.upeu.lamb.personas.repository.EstadoPersonaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoPersonaService {

    private final EstadoPersonaRepository estadoPersonaRepository;
    private final EstadoPersonaMapper estadoPersonaMapper;

    public List<EstadoPersonaResponse> listar() {
        return estadoPersonaRepository.findAll().stream()
                .map(estadoPersonaMapper::toResponse)
                .toList();
    }

    public EstadoPersonaResponse obtener(Long id) {
        return estadoPersonaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EstadoPersonaResponse crear(EstadoPersonaRequest request) {
        EstadoPersona entity = estadoPersonaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return estadoPersonaMapper.toResponse(estadoPersonaRepository.save(entity));
    }

    public EstadoPersonaResponse actualizar(Long id, EstadoPersonaRequest request) {
        EstadoPersona existente = buscarOFallar(id);
        EstadoPersona entity = estadoPersonaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return estadoPersonaMapper.toResponse(estadoPersonaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        estadoPersonaRepository.delete(buscarOFallar(id));
    }

    private EstadoPersona buscarOFallar(Long id) {
        return estadoPersonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado de persona no encontrado: " + id));
    }
}
