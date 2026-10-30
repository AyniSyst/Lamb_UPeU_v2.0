package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.SedeRequest;
import pe.edu.upeu.lamb.institucional.dto.SedeResponse;
import pe.edu.upeu.lamb.institucional.entity.Sede;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.SedeMapper;
import pe.edu.upeu.lamb.institucional.repository.SedeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SedeService {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    public List<SedeResponse> listar() {
        return sedeRepository.findAll().stream()
                .map(sedeMapper::toResponse)
                .toList();
    }

    public SedeResponse obtener(Long id) {
        return sedeMapper.toResponse(buscarOFallar(id));
    }

    public SedeResponse crear(SedeRequest request) {
        Sede entity = sedeMapper.toEntity(request);
        return sedeMapper.toResponse(sedeRepository.save(entity));
    }

    private Sede buscarOFallar(Long id) {
        return sedeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sede no encontrada: " + id));
    }
}
