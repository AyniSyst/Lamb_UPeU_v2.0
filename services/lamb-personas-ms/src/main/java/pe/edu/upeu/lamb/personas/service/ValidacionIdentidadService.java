package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ValidacionIdentidadRequest;
import pe.edu.upeu.lamb.personas.dto.ValidacionIdentidadResponse;
import pe.edu.upeu.lamb.personas.entity.ValidacionIdentidad;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ValidacionIdentidadMapper;
import pe.edu.upeu.lamb.personas.repository.ValidacionIdentidadRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidacionIdentidadService {

    private final ValidacionIdentidadRepository validacionIdentidadRepository;
    private final ValidacionIdentidadMapper validacionIdentidadMapper;

    public List<ValidacionIdentidadResponse> listar() {
        return validacionIdentidadRepository.findAll().stream()
                .map(validacionIdentidadMapper::toResponse)
                .toList();
    }

    public ValidacionIdentidadResponse obtener(Long id) {
        return validacionIdentidadMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ValidacionIdentidadResponse crear(ValidacionIdentidadRequest request) {
        ValidacionIdentidad entity = validacionIdentidadMapper.toEntity(request);
        entity.setFechaValidacion(Instant.now());
        return validacionIdentidadMapper.toResponse(validacionIdentidadRepository.save(entity));
    }

    public ValidacionIdentidadResponse actualizar(Long id, ValidacionIdentidadRequest request) {
        ValidacionIdentidad existente = buscarOFallar(id);
        ValidacionIdentidad entity = validacionIdentidadMapper.toEntity(request);
        entity.setFechaValidacion(Instant.now());
        return validacionIdentidadMapper.toResponse(validacionIdentidadRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        validacionIdentidadRepository.delete(buscarOFallar(id));
    }

    private ValidacionIdentidad buscarOFallar(Long id) {
        return validacionIdentidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Validacion de identidad no encontrada: " + id));
    }
}
