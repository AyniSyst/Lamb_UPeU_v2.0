package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.PublicacionCalendarioRequest;
import pe.edu.upeu.lamb.institucional.dto.PublicacionCalendarioResponse;
import pe.edu.upeu.lamb.institucional.entity.PublicacionCalendario;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.PublicacionCalendarioMapper;
import pe.edu.upeu.lamb.institucional.repository.PublicacionCalendarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicacionCalendarioService {

    private final PublicacionCalendarioRepository publicacionCalendarioRepository;
    private final PublicacionCalendarioMapper publicacionCalendarioMapper;

    public List<PublicacionCalendarioResponse> listar() {
        return publicacionCalendarioRepository.findAll().stream()
                .map(publicacionCalendarioMapper::toResponse)
                .toList();
    }

    public PublicacionCalendarioResponse obtener(Long id) {
        return publicacionCalendarioMapper.toResponse(buscarOFallar(id));
    }

    public PublicacionCalendarioResponse crear(PublicacionCalendarioRequest request) {
        PublicacionCalendario entity = publicacionCalendarioMapper.toEntity(request);
        return publicacionCalendarioMapper.toResponse(publicacionCalendarioRepository.save(entity));
    }

    private PublicacionCalendario buscarOFallar(Long id) {
        return publicacionCalendarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PublicacionCalendario no encontrado con id " + id));
    }
}
