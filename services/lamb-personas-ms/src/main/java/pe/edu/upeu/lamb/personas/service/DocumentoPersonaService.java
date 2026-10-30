package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.DocumentoPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.DocumentoPersonaResponse;
import pe.edu.upeu.lamb.personas.entity.DocumentoPersona;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.DocumentoPersonaMapper;
import pe.edu.upeu.lamb.personas.repository.DocumentoPersonaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoPersonaService {

    private final DocumentoPersonaRepository documentoPersonaRepository;
    private final DocumentoPersonaMapper documentoPersonaMapper;

    public List<DocumentoPersonaResponse> listar() {
        return documentoPersonaRepository.findAll().stream()
                .map(documentoPersonaMapper::toResponse)
                .toList();
    }

    public DocumentoPersonaResponse obtener(Long id) {
        return documentoPersonaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DocumentoPersonaResponse crear(DocumentoPersonaRequest request) {
        DocumentoPersona entity = documentoPersonaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return documentoPersonaMapper.toResponse(documentoPersonaRepository.save(entity));
    }

    private DocumentoPersona buscarOFallar(Long id) {
        return documentoPersonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento de persona no encontrado: " + id));
    }
}
