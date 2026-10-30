package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.InstitucionRequest;
import pe.edu.upeu.lamb.institucional.dto.InstitucionResponse;
import pe.edu.upeu.lamb.institucional.entity.Institucion;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.InstitucionMapper;
import pe.edu.upeu.lamb.institucional.repository.InstitucionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitucionService {

    private final InstitucionRepository institucionRepository;
    private final InstitucionMapper institucionMapper;

    public List<InstitucionResponse> listar() {
        return institucionRepository.findAll().stream()
                .map(institucionMapper::toResponse)
                .toList();
    }

    public InstitucionResponse obtener(Long id) {
        return institucionMapper.toResponse(buscarOFallar(id));
    }

    public InstitucionResponse crear(InstitucionRequest request) {
        Institucion entity = institucionMapper.toEntity(request);
        return institucionMapper.toResponse(institucionRepository.save(entity));
    }

    Institucion buscarOFallar(Long id) {
        return institucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institucion no encontrada: " + id));
    }
}
