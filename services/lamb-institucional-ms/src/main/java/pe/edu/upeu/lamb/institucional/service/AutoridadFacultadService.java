package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.AutoridadFacultadRequest;
import pe.edu.upeu.lamb.institucional.dto.AutoridadFacultadResponse;
import pe.edu.upeu.lamb.institucional.entity.AutoridadFacultad;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.AutoridadFacultadMapper;
import pe.edu.upeu.lamb.institucional.repository.AutoridadFacultadRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoridadFacultadService {

    private final AutoridadFacultadRepository autoridadFacultadRepository;
    private final AutoridadFacultadMapper autoridadFacultadMapper;

    public List<AutoridadFacultadResponse> listar() {
        return autoridadFacultadRepository.findAll().stream()
                .map(autoridadFacultadMapper::toResponse)
                .toList();
    }

    public AutoridadFacultadResponse obtener(Long id) {
        return autoridadFacultadMapper.toResponse(buscarOFallar(id));
    }

    public AutoridadFacultadResponse crear(AutoridadFacultadRequest request) {
        AutoridadFacultad entity = autoridadFacultadMapper.toEntity(request);
        return autoridadFacultadMapper.toResponse(autoridadFacultadRepository.save(entity));
    }

    private AutoridadFacultad buscarOFallar(Long id) {
        return autoridadFacultadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autoridad de facultad no encontrada: " + id));
    }
}
