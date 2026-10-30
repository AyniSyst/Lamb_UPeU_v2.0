package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.HistorialRevisionDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.HistorialRevisionDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.HistorialRevisionDocumento;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.HistorialRevisionDocumentoMapper;
import pe.edu.upeu.lamb.personas.repository.HistorialRevisionDocumentoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialRevisionDocumentoService {

    private final HistorialRevisionDocumentoRepository historialRevisionDocumentoRepository;
    private final HistorialRevisionDocumentoMapper historialRevisionDocumentoMapper;

    public List<HistorialRevisionDocumentoResponse> listar() {
        return historialRevisionDocumentoRepository.findAll().stream()
                .map(historialRevisionDocumentoMapper::toResponse)
                .toList();
    }

    public HistorialRevisionDocumentoResponse obtener(Long id) {
        return historialRevisionDocumentoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public HistorialRevisionDocumentoResponse crear(HistorialRevisionDocumentoRequest request) {
        HistorialRevisionDocumento entity = historialRevisionDocumentoMapper.toEntity(request);
        entity.setFechaRevision(Instant.now());
        return historialRevisionDocumentoMapper.toResponse(historialRevisionDocumentoRepository.save(entity));
    }

    private HistorialRevisionDocumento buscarOFallar(Long id) {
        return historialRevisionDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historial de revision de documento no encontrado: " + id));
    }
}
