package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.EstadoDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.EstadoDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.EstadoDocumento;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.EstadoDocumentoMapper;
import pe.edu.upeu.lamb.personas.repository.EstadoDocumentoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoDocumentoService {

    private final EstadoDocumentoRepository estadoDocumentoRepository;
    private final EstadoDocumentoMapper estadoDocumentoMapper;

    public List<EstadoDocumentoResponse> listar() {
        return estadoDocumentoRepository.findAll().stream()
                .map(estadoDocumentoMapper::toResponse)
                .toList();
    }

    public EstadoDocumentoResponse obtener(Long id) {
        return estadoDocumentoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EstadoDocumentoResponse crear(EstadoDocumentoRequest request) {
        EstadoDocumento entity = estadoDocumentoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return estadoDocumentoMapper.toResponse(estadoDocumentoRepository.save(entity));
    }

    public EstadoDocumentoResponse actualizar(Long id, EstadoDocumentoRequest request) {
        EstadoDocumento existente = buscarOFallar(id);
        EstadoDocumento entity = estadoDocumentoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return estadoDocumentoMapper.toResponse(estadoDocumentoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        estadoDocumentoRepository.delete(buscarOFallar(id));
    }

    private EstadoDocumento buscarOFallar(Long id) {
        return estadoDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado de documento no encontrado: " + id));
    }
}
