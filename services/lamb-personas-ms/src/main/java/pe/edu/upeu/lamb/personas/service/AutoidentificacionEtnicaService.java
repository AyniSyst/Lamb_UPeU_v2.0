package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.AutoidentificacionEtnicaRequest;
import pe.edu.upeu.lamb.personas.dto.AutoidentificacionEtnicaResponse;
import pe.edu.upeu.lamb.personas.entity.AutoidentificacionEtnica;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.AutoidentificacionEtnicaMapper;
import pe.edu.upeu.lamb.personas.repository.AutoidentificacionEtnicaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoidentificacionEtnicaService {

    private final AutoidentificacionEtnicaRepository autoidentificacionEtnicaRepository;
    private final AutoidentificacionEtnicaMapper autoidentificacionEtnicaMapper;

    public List<AutoidentificacionEtnicaResponse> listar() {
        return autoidentificacionEtnicaRepository.findAll().stream()
                .map(autoidentificacionEtnicaMapper::toResponse)
                .toList();
    }

    public AutoidentificacionEtnicaResponse obtener(Long id) {
        return autoidentificacionEtnicaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public AutoidentificacionEtnicaResponse crear(AutoidentificacionEtnicaRequest request) {
        AutoidentificacionEtnica entity = autoidentificacionEtnicaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return autoidentificacionEtnicaMapper.toResponse(autoidentificacionEtnicaRepository.save(entity));
    }

    public AutoidentificacionEtnicaResponse actualizar(Long id, AutoidentificacionEtnicaRequest request) {
        AutoidentificacionEtnica existente = buscarOFallar(id);
        AutoidentificacionEtnica entity = autoidentificacionEtnicaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return autoidentificacionEtnicaMapper.toResponse(autoidentificacionEtnicaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        autoidentificacionEtnicaRepository.delete(buscarOFallar(id));
    }

    private AutoidentificacionEtnica buscarOFallar(Long id) {
        return autoidentificacionEtnicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autoidentificacion etnica no encontrada: " + id));
    }
}
