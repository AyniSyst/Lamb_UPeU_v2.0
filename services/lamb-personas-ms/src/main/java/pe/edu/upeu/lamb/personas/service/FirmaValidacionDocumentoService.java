package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.FirmaValidacionDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.FirmaValidacionDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.FirmaValidacionDocumento;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.FirmaValidacionDocumentoMapper;
import pe.edu.upeu.lamb.personas.repository.FirmaValidacionDocumentoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FirmaValidacionDocumentoService {

    private final FirmaValidacionDocumentoRepository firmaValidacionDocumentoRepository;
    private final FirmaValidacionDocumentoMapper firmaValidacionDocumentoMapper;

    public List<FirmaValidacionDocumentoResponse> listar() {
        return firmaValidacionDocumentoRepository.findAll().stream()
                .map(firmaValidacionDocumentoMapper::toResponse)
                .toList();
    }

    public FirmaValidacionDocumentoResponse obtener(Long id) {
        return firmaValidacionDocumentoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public FirmaValidacionDocumentoResponse crear(FirmaValidacionDocumentoRequest request) {
        FirmaValidacionDocumento entity = firmaValidacionDocumentoMapper.toEntity(request);
        entity.setFechaFirma(Instant.now());
        return firmaValidacionDocumentoMapper.toResponse(firmaValidacionDocumentoRepository.save(entity));
    }

    private FirmaValidacionDocumento buscarOFallar(Long id) {
        return firmaValidacionDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Firma de validacion de documento no encontrada: " + id));
    }
}
