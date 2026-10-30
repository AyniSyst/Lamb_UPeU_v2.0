package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.EstadoCivilRequest;
import pe.edu.upeu.lamb.personas.dto.EstadoCivilResponse;
import pe.edu.upeu.lamb.personas.entity.EstadoCivil;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.EstadoCivilMapper;
import pe.edu.upeu.lamb.personas.repository.EstadoCivilRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoCivilService {

    private final EstadoCivilRepository estadoCivilRepository;
    private final EstadoCivilMapper estadoCivilMapper;

    public List<EstadoCivilResponse> listar() {
        return estadoCivilRepository.findAll().stream()
                .map(estadoCivilMapper::toResponse)
                .toList();
    }

    public EstadoCivilResponse obtener(Long id) {
        return estadoCivilMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EstadoCivilResponse crear(EstadoCivilRequest request) {
        EstadoCivil entity = estadoCivilMapper.toEntity(request);
        return estadoCivilMapper.toResponse(estadoCivilRepository.save(entity));
    }

    private EstadoCivil buscarOFallar(Long id) {
        return estadoCivilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado civil no encontrado: " + id));
    }
}
