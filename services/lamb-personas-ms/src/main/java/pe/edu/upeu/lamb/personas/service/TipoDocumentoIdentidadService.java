package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.TipoDocumentoIdentidadRequest;
import pe.edu.upeu.lamb.personas.dto.TipoDocumentoIdentidadResponse;
import pe.edu.upeu.lamb.personas.entity.TipoDocumentoIdentidad;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.TipoDocumentoIdentidadMapper;
import pe.edu.upeu.lamb.personas.repository.TipoDocumentoIdentidadRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDocumentoIdentidadService {

    private final TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;
    private final TipoDocumentoIdentidadMapper tipoDocumentoIdentidadMapper;

    public List<TipoDocumentoIdentidadResponse> listar() {
        return tipoDocumentoIdentidadRepository.findAll().stream()
                .map(tipoDocumentoIdentidadMapper::toResponse)
                .toList();
    }

    public TipoDocumentoIdentidadResponse obtener(Long id) {
        return tipoDocumentoIdentidadMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TipoDocumentoIdentidadResponse crear(TipoDocumentoIdentidadRequest request) {
        TipoDocumentoIdentidad entity = tipoDocumentoIdentidadMapper.toEntity(request);
        return tipoDocumentoIdentidadMapper.toResponse(tipoDocumentoIdentidadRepository.save(entity));
    }

    public TipoDocumentoIdentidadResponse actualizar(Long id, TipoDocumentoIdentidadRequest request) {
        TipoDocumentoIdentidad existente = buscarOFallar(id);
        TipoDocumentoIdentidad entity = tipoDocumentoIdentidadMapper.toEntity(request);
        return tipoDocumentoIdentidadMapper.toResponse(tipoDocumentoIdentidadRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        tipoDocumentoIdentidadRepository.delete(buscarOFallar(id));
    }

    private TipoDocumentoIdentidad buscarOFallar(Long id) {
        return tipoDocumentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento de identidad no encontrado: " + id));
    }
}
