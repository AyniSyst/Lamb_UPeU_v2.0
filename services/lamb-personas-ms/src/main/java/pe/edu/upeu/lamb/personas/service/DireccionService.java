package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.DireccionRequest;
import pe.edu.upeu.lamb.personas.dto.DireccionResponse;
import pe.edu.upeu.lamb.personas.entity.Direccion;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.DireccionMapper;
import pe.edu.upeu.lamb.personas.repository.DireccionRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionService {

    private final DireccionRepository direccionRepository;
    private final DireccionMapper direccionMapper;

    public List<DireccionResponse> listar() {
        return direccionRepository.findAll().stream()
                .map(direccionMapper::toResponse)
                .toList();
    }

    public DireccionResponse obtener(Long id) {
        return direccionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DireccionResponse crear(DireccionRequest request) {
        Direccion entity = direccionMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return direccionMapper.toResponse(direccionRepository.save(entity));
    }

    private Direccion buscarOFallar(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direccion no encontrada: " + id));
    }
}
