package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.VigenciaVencimientoDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.VigenciaVencimientoDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.VigenciaVencimientoDocumento;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.VigenciaVencimientoDocumentoMapper;
import pe.edu.upeu.lamb.personas.repository.VigenciaVencimientoDocumentoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VigenciaVencimientoDocumentoService {

    private final VigenciaVencimientoDocumentoRepository vigenciaVencimientoDocumentoRepository;
    private final VigenciaVencimientoDocumentoMapper vigenciaVencimientoDocumentoMapper;

    public List<VigenciaVencimientoDocumentoResponse> listar() {
        return vigenciaVencimientoDocumentoRepository.findAll().stream()
                .map(vigenciaVencimientoDocumentoMapper::toResponse)
                .toList();
    }

    public VigenciaVencimientoDocumentoResponse obtener(Long id) {
        return vigenciaVencimientoDocumentoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public VigenciaVencimientoDocumentoResponse crear(VigenciaVencimientoDocumentoRequest request) {
        VigenciaVencimientoDocumento entity = vigenciaVencimientoDocumentoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return vigenciaVencimientoDocumentoMapper.toResponse(vigenciaVencimientoDocumentoRepository.save(entity));
    }

    public VigenciaVencimientoDocumentoResponse actualizar(Long id, VigenciaVencimientoDocumentoRequest request) {
        VigenciaVencimientoDocumento existente = buscarOFallar(id);
        VigenciaVencimientoDocumento entity = vigenciaVencimientoDocumentoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return vigenciaVencimientoDocumentoMapper.toResponse(vigenciaVencimientoDocumentoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        vigenciaVencimientoDocumentoRepository.delete(buscarOFallar(id));
    }

    private VigenciaVencimientoDocumento buscarOFallar(Long id) {
        return vigenciaVencimientoDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vigencia/vencimiento de documento no encontrada: " + id));
    }
}
