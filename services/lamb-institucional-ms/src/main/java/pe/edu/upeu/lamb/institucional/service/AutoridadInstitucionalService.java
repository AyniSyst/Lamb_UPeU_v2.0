package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.AutoridadInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.AutoridadInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.entity.AutoridadInstitucional;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.AutoridadInstitucionalMapper;
import pe.edu.upeu.lamb.institucional.repository.AutoridadInstitucionalRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AutoridadInstitucionalService {

    private final AutoridadInstitucionalRepository autoridadInstitucionalRepository;
    private final AutoridadInstitucionalMapper autoridadInstitucionalMapper;

    public List<AutoridadInstitucionalResponse> listar() {
        return autoridadInstitucionalRepository.findAll().stream()
                .map(autoridadInstitucionalMapper::toResponse)
                .toList();
    }

    public AutoridadInstitucionalResponse obtener(Long id) {
        return autoridadInstitucionalMapper.toResponse(buscarOFallar(id));
    }

    public AutoridadInstitucionalResponse crear(AutoridadInstitucionalRequest request) {
        AutoridadInstitucional entity = autoridadInstitucionalMapper.toEntity(request);
        return autoridadInstitucionalMapper.toResponse(autoridadInstitucionalRepository.save(entity));
    }

    public AutoridadInstitucionalResponse actualizar(Long id, AutoridadInstitucionalRequest request) {
        AutoridadInstitucional existente = buscarOFallar(id);
        AutoridadInstitucional entity = autoridadInstitucionalMapper.toEntity(request);
        return autoridadInstitucionalMapper.toResponse(autoridadInstitucionalRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        autoridadInstitucionalRepository.delete(buscarOFallar(id));
    }

    private AutoridadInstitucional buscarOFallar(Long id) {
        return autoridadInstitucionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autoridad institucional no encontrada: " + id));
    }
}
