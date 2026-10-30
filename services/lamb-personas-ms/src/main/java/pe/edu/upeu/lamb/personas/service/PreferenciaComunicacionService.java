package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.PreferenciaComunicacionRequest;
import pe.edu.upeu.lamb.personas.dto.PreferenciaComunicacionResponse;
import pe.edu.upeu.lamb.personas.entity.PreferenciaComunicacion;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.PreferenciaComunicacionMapper;
import pe.edu.upeu.lamb.personas.repository.PreferenciaComunicacionRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PreferenciaComunicacionService {

    private final PreferenciaComunicacionRepository preferenciaComunicacionRepository;
    private final PreferenciaComunicacionMapper preferenciaComunicacionMapper;

    public List<PreferenciaComunicacionResponse> listar() {
        return preferenciaComunicacionRepository.findAll().stream()
                .map(preferenciaComunicacionMapper::toResponse)
                .toList();
    }

    public PreferenciaComunicacionResponse obtener(Long id) {
        return preferenciaComunicacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public PreferenciaComunicacionResponse crear(PreferenciaComunicacionRequest request) {
        PreferenciaComunicacion entity = preferenciaComunicacionMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return preferenciaComunicacionMapper.toResponse(preferenciaComunicacionRepository.save(entity));
    }

    public PreferenciaComunicacionResponse actualizar(Long id, PreferenciaComunicacionRequest request) {
        PreferenciaComunicacion existente = buscarOFallar(id);
        PreferenciaComunicacion entity = preferenciaComunicacionMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return preferenciaComunicacionMapper.toResponse(preferenciaComunicacionRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        preferenciaComunicacionRepository.delete(buscarOFallar(id));
    }

    private PreferenciaComunicacion buscarOFallar(Long id) {
        return preferenciaComunicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preferencia de comunicacion no encontrada: " + id));
    }
}
