package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.CorreoInstitucionalRequest;
import pe.edu.upeu.lamb.personas.dto.CorreoInstitucionalResponse;
import pe.edu.upeu.lamb.personas.entity.CorreoInstitucional;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.CorreoInstitucionalMapper;
import pe.edu.upeu.lamb.personas.repository.CorreoInstitucionalRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CorreoInstitucionalService {

    private final CorreoInstitucionalRepository correoInstitucionalRepository;
    private final CorreoInstitucionalMapper correoInstitucionalMapper;

    public List<CorreoInstitucionalResponse> listar() {
        return correoInstitucionalRepository.findAll().stream()
                .map(correoInstitucionalMapper::toResponse)
                .toList();
    }

    public CorreoInstitucionalResponse obtener(Long id) {
        return correoInstitucionalMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CorreoInstitucionalResponse crear(CorreoInstitucionalRequest request) {
        CorreoInstitucional entity = correoInstitucionalMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return correoInstitucionalMapper.toResponse(correoInstitucionalRepository.save(entity));
    }

    public CorreoInstitucionalResponse actualizar(Long id, CorreoInstitucionalRequest request) {
        CorreoInstitucional existente = buscarOFallar(id);
        CorreoInstitucional entity = correoInstitucionalMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return correoInstitucionalMapper.toResponse(correoInstitucionalRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        correoInstitucionalRepository.delete(buscarOFallar(id));
    }

    private CorreoInstitucional buscarOFallar(Long id) {
        return correoInstitucionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Correo institucional no encontrado: " + id));
    }
}
