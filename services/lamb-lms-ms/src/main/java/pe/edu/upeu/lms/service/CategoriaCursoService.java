package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.CategoriaCursoRequest;
import pe.edu.upeu.lms.dto.CategoriaCursoResponse;
import pe.edu.upeu.lms.entity.CategoriaCurso;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.CategoriaCursoMapper;
import pe.edu.upeu.lms.repository.CategoriaCursoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaCursoService {

    private final CategoriaCursoRepository categoriaCursoRepository;
    private final CategoriaCursoMapper categoriaCursoMapper;

    public List<CategoriaCursoResponse> listar() {
        return categoriaCursoRepository.findAll().stream()
                .map(categoriaCursoMapper::toResponse)
                .toList();
    }

    public CategoriaCursoResponse obtener(Long id) {
        return categoriaCursoMapper.toResponse(buscarOFallar(id));
    }

    public CategoriaCursoResponse crear(CategoriaCursoRequest request) {
        CategoriaCurso categoriaCurso = categoriaCursoMapper.toEntity(request);
        return categoriaCursoMapper.toResponse(categoriaCursoRepository.save(categoriaCurso));
    }

    public CategoriaCursoResponse actualizar(Long id, CategoriaCursoRequest request) {
        CategoriaCurso categoriaCurso = buscarOFallar(id);
        categoriaCurso.setNombre(request.getNombre());
        categoriaCurso.setDescripcion(request.getDescripcion());
        categoriaCurso.setActivo(request.getActivo());
        return categoriaCursoMapper.toResponse(categoriaCursoRepository.save(categoriaCurso));
    }

    public void eliminar(Long id) {
        categoriaCursoRepository.delete(buscarOFallar(id));
    }

    private CategoriaCurso buscarOFallar(Long id) {
        return categoriaCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de curso no encontrada: " + id));
    }
}
