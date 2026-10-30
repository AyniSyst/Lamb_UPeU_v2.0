package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ReemplazoVersionDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.ReemplazoVersionDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.ReemplazoVersionDocumento;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ReemplazoVersionDocumentoMapper;
import pe.edu.upeu.lamb.personas.repository.ReemplazoVersionDocumentoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReemplazoVersionDocumentoService {

    private final ReemplazoVersionDocumentoRepository reemplazoVersionDocumentoRepository;
    private final ReemplazoVersionDocumentoMapper reemplazoVersionDocumentoMapper;

    public List<ReemplazoVersionDocumentoResponse> listar() {
        return reemplazoVersionDocumentoRepository.findAll().stream()
                .map(reemplazoVersionDocumentoMapper::toResponse)
                .toList();
    }

    public ReemplazoVersionDocumentoResponse obtener(Long id) {
        return reemplazoVersionDocumentoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ReemplazoVersionDocumentoResponse crear(ReemplazoVersionDocumentoRequest request) {
        ReemplazoVersionDocumento entity = reemplazoVersionDocumentoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return reemplazoVersionDocumentoMapper.toResponse(reemplazoVersionDocumentoRepository.save(entity));
    }

    public ReemplazoVersionDocumentoResponse actualizar(Long id, ReemplazoVersionDocumentoRequest request) {
        ReemplazoVersionDocumento existente = buscarOFallar(id);
        ReemplazoVersionDocumento entity = reemplazoVersionDocumentoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return reemplazoVersionDocumentoMapper.toResponse(reemplazoVersionDocumentoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        reemplazoVersionDocumentoRepository.delete(buscarOFallar(id));
    }

    private ReemplazoVersionDocumento buscarOFallar(Long id) {
        return reemplazoVersionDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reemplazo de version de documento no encontrado: " + id));
    }
}
