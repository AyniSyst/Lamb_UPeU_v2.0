package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.RequisitoDocumentarioRequest;
import pe.edu.upeu.lamb.personas.dto.RequisitoDocumentarioResponse;
import pe.edu.upeu.lamb.personas.entity.RequisitoDocumentario;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.RequisitoDocumentarioMapper;
import pe.edu.upeu.lamb.personas.repository.RequisitoDocumentarioRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoDocumentarioService {

    private final RequisitoDocumentarioRepository requisitoDocumentarioRepository;
    private final RequisitoDocumentarioMapper requisitoDocumentarioMapper;

    public List<RequisitoDocumentarioResponse> listar() {
        return requisitoDocumentarioRepository.findAll().stream()
                .map(requisitoDocumentarioMapper::toResponse)
                .toList();
    }

    public RequisitoDocumentarioResponse obtener(Long id) {
        return requisitoDocumentarioMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RequisitoDocumentarioResponse crear(RequisitoDocumentarioRequest request) {
        RequisitoDocumentario entity = requisitoDocumentarioMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return requisitoDocumentarioMapper.toResponse(requisitoDocumentarioRepository.save(entity));
    }

    public RequisitoDocumentarioResponse actualizar(Long id, RequisitoDocumentarioRequest request) {
        RequisitoDocumentario existente = buscarOFallar(id);
        RequisitoDocumentario entity = requisitoDocumentarioMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return requisitoDocumentarioMapper.toResponse(requisitoDocumentarioRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        requisitoDocumentarioRepository.delete(buscarOFallar(id));
    }

    private RequisitoDocumentario buscarOFallar(Long id) {
        return requisitoDocumentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requisito documentario no encontrado: " + id));
    }
}
