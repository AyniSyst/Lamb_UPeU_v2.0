package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ObservacionDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.ObservacionDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.ObservacionDocumento;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ObservacionDocumentoMapper;
import pe.edu.upeu.lamb.personas.repository.ObservacionDocumentoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObservacionDocumentoService {

    private final ObservacionDocumentoRepository observacionDocumentoRepository;
    private final ObservacionDocumentoMapper observacionDocumentoMapper;

    public List<ObservacionDocumentoResponse> listar() {
        return observacionDocumentoRepository.findAll().stream()
                .map(observacionDocumentoMapper::toResponse)
                .toList();
    }

    public ObservacionDocumentoResponse obtener(Long id) {
        return observacionDocumentoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ObservacionDocumentoResponse crear(ObservacionDocumentoRequest request) {
        ObservacionDocumento entity = observacionDocumentoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return observacionDocumentoMapper.toResponse(observacionDocumentoRepository.save(entity));
    }

    private ObservacionDocumento buscarOFallar(Long id) {
        return observacionDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Observacion de documento no encontrada: " + id));
    }
}
