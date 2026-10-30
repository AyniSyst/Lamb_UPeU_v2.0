package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.AutoridadInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.AutoridadInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.entity.AutoridadInstitucional;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.AutoridadInstitucionalMapper;
import pe.edu.upeu.lamb.institucional.repository.AutoridadInstitucionalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoridadInstitucionalService {

    private final AutoridadInstitucionalRepository autoridadInstitucionalRepository;
    private final AutoridadInstitucionalMapper autoridadInstitucionalMapper;

    public List<AutoridadInstitucionalResponse> listar() {
        return autoridadInstitucionalRepository.findAll().stream()
                .map(autoridadInstitucionalMapper::toResponse)
                .toList();
    }

    public AutoridadInstitucionalResponse obtener(Long id) {
        return autoridadInstitucionalMapper.toResponse(buscarOFallar(id));
    }

    public AutoridadInstitucionalResponse crear(AutoridadInstitucionalRequest request) {
        AutoridadInstitucional entity = autoridadInstitucionalMapper.toEntity(request);
        return autoridadInstitucionalMapper.toResponse(autoridadInstitucionalRepository.save(entity));
    }

    private AutoridadInstitucional buscarOFallar(Long id) {
        return autoridadInstitucionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autoridad institucional no encontrada: " + id));
    }
}
