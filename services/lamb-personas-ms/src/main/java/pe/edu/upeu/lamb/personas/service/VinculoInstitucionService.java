package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.VinculoInstitucionRequest;
import pe.edu.upeu.lamb.personas.dto.VinculoInstitucionResponse;
import pe.edu.upeu.lamb.personas.entity.VinculoInstitucion;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.VinculoInstitucionMapper;
import pe.edu.upeu.lamb.personas.repository.VinculoInstitucionRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VinculoInstitucionService {

    private final VinculoInstitucionRepository vinculoInstitucionRepository;
    private final VinculoInstitucionMapper vinculoInstitucionMapper;

    public List<VinculoInstitucionResponse> listar() {
        return vinculoInstitucionRepository.findAll().stream()
                .map(vinculoInstitucionMapper::toResponse)
                .toList();
    }

    public VinculoInstitucionResponse obtener(Long id) {
        return vinculoInstitucionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public VinculoInstitucionResponse crear(VinculoInstitucionRequest request) {
        VinculoInstitucion entity = vinculoInstitucionMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return vinculoInstitucionMapper.toResponse(vinculoInstitucionRepository.save(entity));
    }

    public VinculoInstitucionResponse actualizar(Long id, VinculoInstitucionRequest request) {
        VinculoInstitucion existente = buscarOFallar(id);
        VinculoInstitucion entity = vinculoInstitucionMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return vinculoInstitucionMapper.toResponse(vinculoInstitucionRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        vinculoInstitucionRepository.delete(buscarOFallar(id));
    }

    private VinculoInstitucion buscarOFallar(Long id) {
        return vinculoInstitucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vinculo con institucion no encontrado: " + id));
    }
}
