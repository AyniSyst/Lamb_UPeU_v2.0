package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.FusionPersonasRequest;
import pe.edu.upeu.lamb.personas.dto.FusionPersonasResponse;
import pe.edu.upeu.lamb.personas.entity.FusionPersonas;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.FusionPersonasMapper;
import pe.edu.upeu.lamb.personas.repository.FusionPersonasRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FusionPersonasService {

    private final FusionPersonasRepository fusionPersonasRepository;
    private final FusionPersonasMapper fusionPersonasMapper;

    public List<FusionPersonasResponse> listar() {
        return fusionPersonasRepository.findAll().stream()
                .map(fusionPersonasMapper::toResponse)
                .toList();
    }

    public FusionPersonasResponse obtener(Long id) {
        return fusionPersonasMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public FusionPersonasResponse crear(FusionPersonasRequest request) {
        FusionPersonas entity = fusionPersonasMapper.toEntity(request);
        entity.setFechaFusion(Instant.now());
        return fusionPersonasMapper.toResponse(fusionPersonasRepository.save(entity));
    }

    private FusionPersonas buscarOFallar(Long id) {
        return fusionPersonasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fusion de personas no encontrada: " + id));
    }
}
