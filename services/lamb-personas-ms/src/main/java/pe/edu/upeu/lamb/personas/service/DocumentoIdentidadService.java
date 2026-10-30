package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.DocumentoIdentidadRequest;
import pe.edu.upeu.lamb.personas.dto.DocumentoIdentidadResponse;
import pe.edu.upeu.lamb.personas.entity.DocumentoIdentidad;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.DocumentoIdentidadMapper;
import pe.edu.upeu.lamb.personas.repository.DocumentoIdentidadRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoIdentidadService {

    private final DocumentoIdentidadRepository documentoIdentidadRepository;
    private final DocumentoIdentidadMapper documentoIdentidadMapper;

    public List<DocumentoIdentidadResponse> listar() {
        return documentoIdentidadRepository.findAll().stream()
                .map(documentoIdentidadMapper::toResponse)
                .toList();
    }

    public DocumentoIdentidadResponse obtener(Long id) {
        return documentoIdentidadMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DocumentoIdentidadResponse crear(DocumentoIdentidadRequest request) {
        DocumentoIdentidad entity = documentoIdentidadMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return documentoIdentidadMapper.toResponse(documentoIdentidadRepository.save(entity));
    }

    private DocumentoIdentidad buscarOFallar(Long id) {
        return documentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento de identidad no encontrado: " + id));
    }
}
