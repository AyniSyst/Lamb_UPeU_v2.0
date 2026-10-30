package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.GradoTituloRequest;
import pe.edu.upeu.lamb.institucional.dto.GradoTituloResponse;
import pe.edu.upeu.lamb.institucional.entity.GradoTitulo;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.GradoTituloMapper;
import pe.edu.upeu.lamb.institucional.repository.GradoTituloRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradoTituloService {

    private final GradoTituloRepository gradoTituloRepository;
    private final GradoTituloMapper gradoTituloMapper;

    public List<GradoTituloResponse> listar() {
        return gradoTituloRepository.findAll().stream()
                .map(gradoTituloMapper::toResponse)
                .toList();
    }

    public GradoTituloResponse obtener(Long id) {
        return gradoTituloMapper.toResponse(buscarOFallar(id));
    }

    public GradoTituloResponse crear(GradoTituloRequest request) {
        GradoTitulo entity = gradoTituloMapper.toEntity(request);
        return gradoTituloMapper.toResponse(gradoTituloRepository.save(entity));
    }

    private GradoTitulo buscarOFallar(Long id) {
        return gradoTituloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GradoTitulo no encontrado con id: " + id));
    }
}
