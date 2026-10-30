package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.AmbienteRequest;
import pe.edu.upeu.lamb.institucional.dto.AmbienteResponse;
import pe.edu.upeu.lamb.institucional.entity.Ambiente;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.AmbienteMapper;
import pe.edu.upeu.lamb.institucional.repository.AmbienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmbienteService {

    private final AmbienteRepository ambienteRepository;
    private final AmbienteMapper ambienteMapper;

    public List<AmbienteResponse> listar() {
        return ambienteRepository.findAll().stream()
                .map(ambienteMapper::toResponse)
                .toList();
    }

    public AmbienteResponse obtener(Long id) {
        return ambienteMapper.toResponse(buscarOFallar(id));
    }

    public AmbienteResponse crear(AmbienteRequest request) {
        Ambiente entity = ambienteMapper.toEntity(request);
        return ambienteMapper.toResponse(ambienteRepository.save(entity));
    }

    private Ambiente buscarOFallar(Long id) {
        return ambienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ambiente no encontrado: " + id));
    }
}
