package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.RelacionFamiliarRequest;
import pe.edu.upeu.lamb.personas.dto.RelacionFamiliarResponse;
import pe.edu.upeu.lamb.personas.entity.RelacionFamiliar;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.RelacionFamiliarMapper;
import pe.edu.upeu.lamb.personas.repository.RelacionFamiliarRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelacionFamiliarService {

    private final RelacionFamiliarRepository relacionFamiliarRepository;
    private final RelacionFamiliarMapper relacionFamiliarMapper;

    public List<RelacionFamiliarResponse> listar() {
        return relacionFamiliarRepository.findAll().stream()
                .map(relacionFamiliarMapper::toResponse)
                .toList();
    }

    public RelacionFamiliarResponse obtener(Long id) {
        return relacionFamiliarMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RelacionFamiliarResponse crear(RelacionFamiliarRequest request) {
        RelacionFamiliar entity = relacionFamiliarMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return relacionFamiliarMapper.toResponse(relacionFamiliarRepository.save(entity));
    }

    public RelacionFamiliarResponse actualizar(Long id, RelacionFamiliarRequest request) {
        RelacionFamiliar existente = buscarOFallar(id);
        RelacionFamiliar entity = relacionFamiliarMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return relacionFamiliarMapper.toResponse(relacionFamiliarRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        relacionFamiliarRepository.delete(buscarOFallar(id));
    }

    private RelacionFamiliar buscarOFallar(Long id) {
        return relacionFamiliarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relacion familiar no encontrada: " + id));
    }
}
