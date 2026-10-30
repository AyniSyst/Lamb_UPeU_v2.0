package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.HistorialEstadoPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.HistorialEstadoPersonaResponse;
import pe.edu.upeu.lamb.personas.entity.HistorialEstadoPersona;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.HistorialEstadoPersonaMapper;
import pe.edu.upeu.lamb.personas.repository.HistorialEstadoPersonaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialEstadoPersonaService {

    private final HistorialEstadoPersonaRepository historialEstadoPersonaRepository;
    private final HistorialEstadoPersonaMapper historialEstadoPersonaMapper;

    public List<HistorialEstadoPersonaResponse> listar() {
        return historialEstadoPersonaRepository.findAll().stream()
                .map(historialEstadoPersonaMapper::toResponse)
                .toList();
    }

    public HistorialEstadoPersonaResponse obtener(Long id) {
        return historialEstadoPersonaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public HistorialEstadoPersonaResponse crear(HistorialEstadoPersonaRequest request) {
        HistorialEstadoPersona entity = historialEstadoPersonaMapper.toEntity(request);
        entity.setFechaCambio(Instant.now());
        return historialEstadoPersonaMapper.toResponse(historialEstadoPersonaRepository.save(entity));
    }

    private HistorialEstadoPersona buscarOFallar(Long id) {
        return historialEstadoPersonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historial de estado de persona no encontrado: " + id));
    }
}
