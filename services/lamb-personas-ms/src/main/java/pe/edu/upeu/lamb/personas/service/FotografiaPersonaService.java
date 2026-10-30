package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.FotografiaPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.FotografiaPersonaResponse;
import pe.edu.upeu.lamb.personas.entity.FotografiaPersona;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.FotografiaPersonaMapper;
import pe.edu.upeu.lamb.personas.repository.FotografiaPersonaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FotografiaPersonaService {

    private final FotografiaPersonaRepository fotografiaPersonaRepository;
    private final FotografiaPersonaMapper fotografiaPersonaMapper;

    public List<FotografiaPersonaResponse> listar() {
        return fotografiaPersonaRepository.findAll().stream()
                .map(fotografiaPersonaMapper::toResponse)
                .toList();
    }

    public FotografiaPersonaResponse obtener(Long id) {
        return fotografiaPersonaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public FotografiaPersonaResponse crear(FotografiaPersonaRequest request) {
        FotografiaPersona entity = fotografiaPersonaMapper.toEntity(request);
        entity.setFechaCarga(Instant.now());
        return fotografiaPersonaMapper.toResponse(fotografiaPersonaRepository.save(entity));
    }

    private FotografiaPersona buscarOFallar(Long id) {
        return fotografiaPersonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fotografia de persona no encontrada: " + id));
    }
}
