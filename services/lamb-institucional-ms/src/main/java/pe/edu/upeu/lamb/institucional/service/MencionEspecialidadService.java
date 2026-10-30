package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.MencionEspecialidadRequest;
import pe.edu.upeu.lamb.institucional.dto.MencionEspecialidadResponse;
import pe.edu.upeu.lamb.institucional.entity.MencionEspecialidad;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.MencionEspecialidadMapper;
import pe.edu.upeu.lamb.institucional.repository.MencionEspecialidadRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MencionEspecialidadService {

    private final MencionEspecialidadRepository mencionEspecialidadRepository;
    private final MencionEspecialidadMapper mencionEspecialidadMapper;

    public List<MencionEspecialidadResponse> listar() {
        return mencionEspecialidadRepository.findAll().stream()
                .map(mencionEspecialidadMapper::toResponse)
                .toList();
    }

    public MencionEspecialidadResponse obtener(Long id) {
        return mencionEspecialidadMapper.toResponse(buscarOFallar(id));
    }

    public MencionEspecialidadResponse crear(MencionEspecialidadRequest request) {
        MencionEspecialidad entity = mencionEspecialidadMapper.toEntity(request);
        return mencionEspecialidadMapper.toResponse(mencionEspecialidadRepository.save(entity));
    }

    private MencionEspecialidad buscarOFallar(Long id) {
        return mencionEspecialidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MencionEspecialidad no encontrada con id: " + id));
    }
}
