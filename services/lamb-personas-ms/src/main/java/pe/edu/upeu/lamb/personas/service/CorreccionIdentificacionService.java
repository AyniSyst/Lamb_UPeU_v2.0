package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.CorreccionIdentificacionRequest;
import pe.edu.upeu.lamb.personas.dto.CorreccionIdentificacionResponse;
import pe.edu.upeu.lamb.personas.entity.CorreccionIdentificacion;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.CorreccionIdentificacionMapper;
import pe.edu.upeu.lamb.personas.repository.CorreccionIdentificacionRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CorreccionIdentificacionService {

    private final CorreccionIdentificacionRepository correccionIdentificacionRepository;
    private final CorreccionIdentificacionMapper correccionIdentificacionMapper;

    public List<CorreccionIdentificacionResponse> listar() {
        return correccionIdentificacionRepository.findAll().stream()
                .map(correccionIdentificacionMapper::toResponse)
                .toList();
    }

    public CorreccionIdentificacionResponse obtener(Long id) {
        return correccionIdentificacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CorreccionIdentificacionResponse crear(CorreccionIdentificacionRequest request) {
        CorreccionIdentificacion entity = correccionIdentificacionMapper.toEntity(request);
        entity.setFechaCorreccion(Instant.now());
        return correccionIdentificacionMapper.toResponse(correccionIdentificacionRepository.save(entity));
    }

    public CorreccionIdentificacionResponse actualizar(Long id, CorreccionIdentificacionRequest request) {
        CorreccionIdentificacion existente = buscarOFallar(id);
        CorreccionIdentificacion entity = correccionIdentificacionMapper.toEntity(request);
        entity.setFechaCorreccion(Instant.now());
        return correccionIdentificacionMapper.toResponse(correccionIdentificacionRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        correccionIdentificacionRepository.delete(buscarOFallar(id));
    }

    private CorreccionIdentificacion buscarOFallar(Long id) {
        return correccionIdentificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Correccion de identificacion no encontrada: " + id));
    }
}
