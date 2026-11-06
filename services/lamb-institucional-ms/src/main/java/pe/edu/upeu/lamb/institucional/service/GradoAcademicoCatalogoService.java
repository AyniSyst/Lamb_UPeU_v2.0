package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.GradoAcademicoCatalogoRequest;
import pe.edu.upeu.lamb.institucional.dto.GradoAcademicoCatalogoResponse;
import pe.edu.upeu.lamb.institucional.entity.GradoAcademicoCatalogo;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.GradoAcademicoCatalogoMapper;
import pe.edu.upeu.lamb.institucional.repository.GradoAcademicoCatalogoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GradoAcademicoCatalogoService {

    private final GradoAcademicoCatalogoRepository gradoAcademicoCatalogoRepository;
    private final GradoAcademicoCatalogoMapper gradoAcademicoCatalogoMapper;

    public List<GradoAcademicoCatalogoResponse> listar() {
        return gradoAcademicoCatalogoRepository.findAll().stream()
                .map(gradoAcademicoCatalogoMapper::toResponse)
                .toList();
    }

    public GradoAcademicoCatalogoResponse obtener(Long id) {
        return gradoAcademicoCatalogoMapper.toResponse(buscarOFallar(id));
    }

    public GradoAcademicoCatalogoResponse crear(GradoAcademicoCatalogoRequest request) {
        GradoAcademicoCatalogo entity = gradoAcademicoCatalogoMapper.toEntity(request);
        return gradoAcademicoCatalogoMapper.toResponse(gradoAcademicoCatalogoRepository.save(entity));
    }

    public GradoAcademicoCatalogoResponse actualizar(Long id, GradoAcademicoCatalogoRequest request) {
        GradoAcademicoCatalogo existente = buscarOFallar(id);
        GradoAcademicoCatalogo entity = gradoAcademicoCatalogoMapper.toEntity(request);
        return gradoAcademicoCatalogoMapper.toResponse(gradoAcademicoCatalogoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        gradoAcademicoCatalogoRepository.delete(buscarOFallar(id));
    }

    private GradoAcademicoCatalogo buscarOFallar(Long id) {
        return gradoAcademicoCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GradoAcademicoCatalogo no encontrado con id: " + id));
    }
}
