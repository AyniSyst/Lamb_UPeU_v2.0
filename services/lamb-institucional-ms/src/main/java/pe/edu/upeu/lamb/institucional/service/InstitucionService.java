package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.InstitucionRequest;
import pe.edu.upeu.lamb.institucional.dto.InstitucionResponse;
import pe.edu.upeu.lamb.institucional.entity.Institucion;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.InstitucionMapper;
import pe.edu.upeu.lamb.institucional.repository.InstitucionRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InstitucionService {

    private final InstitucionRepository institucionRepository;
    private final InstitucionMapper institucionMapper;

    public List<InstitucionResponse> listar() {
        return institucionRepository.findAll().stream()
                .map(institucionMapper::toResponse)
                .toList();
    }

    public InstitucionResponse obtener(Long id) {
        return institucionMapper.toResponse(buscarOFallar(id));
    }

    public InstitucionResponse crear(InstitucionRequest request) {
        Institucion entity = institucionMapper.toEntity(request);
        return institucionMapper.toResponse(institucionRepository.save(entity));
    }

    public InstitucionResponse actualizar(Long id, InstitucionRequest request) {
        Institucion existente = buscarOFallar(id);
        Institucion entity = institucionMapper.toEntity(request);
        return institucionMapper.toResponse(institucionRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        institucionRepository.delete(buscarOFallar(id));
    }

    Institucion buscarOFallar(Long id) {
        return institucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institucion no encontrada: " + id));
    }
}
