package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ConfesionReligiosaRequest;
import pe.edu.upeu.lamb.personas.dto.ConfesionReligiosaResponse;
import pe.edu.upeu.lamb.personas.entity.ConfesionReligiosa;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ConfesionReligiosaMapper;
import pe.edu.upeu.lamb.personas.repository.ConfesionReligiosaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfesionReligiosaService {

    private final ConfesionReligiosaRepository confesionReligiosaRepository;
    private final ConfesionReligiosaMapper confesionReligiosaMapper;

    public List<ConfesionReligiosaResponse> listar() {
        return confesionReligiosaRepository.findAll().stream()
                .map(confesionReligiosaMapper::toResponse)
                .toList();
    }

    public ConfesionReligiosaResponse obtener(Long id) {
        return confesionReligiosaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ConfesionReligiosaResponse crear(ConfesionReligiosaRequest request) {
        ConfesionReligiosa entity = confesionReligiosaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return confesionReligiosaMapper.toResponse(confesionReligiosaRepository.save(entity));
    }

    private ConfesionReligiosa buscarOFallar(Long id) {
        return confesionReligiosaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Confesion religiosa no encontrada: " + id));
    }
}
