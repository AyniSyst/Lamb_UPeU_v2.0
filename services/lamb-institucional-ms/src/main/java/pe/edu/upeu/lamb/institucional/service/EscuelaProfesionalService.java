package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.EscuelaProfesionalRequest;
import pe.edu.upeu.lamb.institucional.dto.EscuelaProfesionalResponse;
import pe.edu.upeu.lamb.institucional.entity.EscuelaProfesional;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.EscuelaProfesionalMapper;
import pe.edu.upeu.lamb.institucional.repository.EscuelaProfesionalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EscuelaProfesionalService {

    private final EscuelaProfesionalRepository escuelaProfesionalRepository;
    private final EscuelaProfesionalMapper escuelaProfesionalMapper;

    public List<EscuelaProfesionalResponse> listar() {
        return escuelaProfesionalRepository.findAll().stream()
                .map(escuelaProfesionalMapper::toResponse)
                .toList();
    }

    public EscuelaProfesionalResponse obtener(Long id) {
        return escuelaProfesionalMapper.toResponse(buscarOFallar(id));
    }

    public EscuelaProfesionalResponse crear(EscuelaProfesionalRequest request) {
        EscuelaProfesional entity = escuelaProfesionalMapper.toEntity(request);
        return escuelaProfesionalMapper.toResponse(escuelaProfesionalRepository.save(entity));
    }

    private EscuelaProfesional buscarOFallar(Long id) {
        return escuelaProfesionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Escuela profesional no encontrada: " + id));
    }
}
