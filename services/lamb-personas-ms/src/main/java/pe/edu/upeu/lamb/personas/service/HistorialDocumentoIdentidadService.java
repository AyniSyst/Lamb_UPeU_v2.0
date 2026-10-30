package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.HistorialDocumentoIdentidadRequest;
import pe.edu.upeu.lamb.personas.dto.HistorialDocumentoIdentidadResponse;
import pe.edu.upeu.lamb.personas.entity.HistorialDocumentoIdentidad;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.HistorialDocumentoIdentidadMapper;
import pe.edu.upeu.lamb.personas.repository.HistorialDocumentoIdentidadRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialDocumentoIdentidadService {

    private final HistorialDocumentoIdentidadRepository historialDocumentoIdentidadRepository;
    private final HistorialDocumentoIdentidadMapper historialDocumentoIdentidadMapper;

    public List<HistorialDocumentoIdentidadResponse> listar() {
        return historialDocumentoIdentidadRepository.findAll().stream()
                .map(historialDocumentoIdentidadMapper::toResponse)
                .toList();
    }

    public HistorialDocumentoIdentidadResponse obtener(Long id) {
        return historialDocumentoIdentidadMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public HistorialDocumentoIdentidadResponse crear(HistorialDocumentoIdentidadRequest request) {
        HistorialDocumentoIdentidad entity = historialDocumentoIdentidadMapper.toEntity(request);
        entity.setFechaCambio(Instant.now());
        return historialDocumentoIdentidadMapper.toResponse(historialDocumentoIdentidadRepository.save(entity));
    }

    public HistorialDocumentoIdentidadResponse actualizar(Long id, HistorialDocumentoIdentidadRequest request) {
        HistorialDocumentoIdentidad existente = buscarOFallar(id);
        HistorialDocumentoIdentidad entity = historialDocumentoIdentidadMapper.toEntity(request);
        entity.setFechaCambio(Instant.now());
        return historialDocumentoIdentidadMapper.toResponse(historialDocumentoIdentidadRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        historialDocumentoIdentidadRepository.delete(buscarOFallar(id));
    }

    private HistorialDocumentoIdentidad buscarOFallar(Long id) {
        return historialDocumentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historial de documento de identidad no encontrado: " + id));
    }
}
