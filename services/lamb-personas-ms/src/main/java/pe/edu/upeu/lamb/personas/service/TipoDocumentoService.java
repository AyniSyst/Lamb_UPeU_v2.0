package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.TipoDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.TipoDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.TipoDocumento;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.TipoDocumentoMapper;
import pe.edu.upeu.lamb.personas.repository.TipoDocumentoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoDocumentoMapper tipoDocumentoMapper;

    public List<TipoDocumentoResponse> listar() {
        return tipoDocumentoRepository.findAll().stream()
                .map(tipoDocumentoMapper::toResponse)
                .toList();
    }

    public TipoDocumentoResponse obtener(Long id) {
        return tipoDocumentoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TipoDocumentoResponse crear(TipoDocumentoRequest request) {
        TipoDocumento entity = tipoDocumentoMapper.toEntity(request);
        return tipoDocumentoMapper.toResponse(tipoDocumentoRepository.save(entity));
    }

    private TipoDocumento buscarOFallar(Long id) {
        return tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado: " + id));
    }
}
