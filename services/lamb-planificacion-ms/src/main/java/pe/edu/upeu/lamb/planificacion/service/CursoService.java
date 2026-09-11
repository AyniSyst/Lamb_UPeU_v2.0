package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.CursoRequest;
import pe.edu.upeu.lamb.planificacion.dto.CursoResponse;
import pe.edu.upeu.lamb.planificacion.entity.Curso;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.CursoMapper;
import pe.edu.upeu.lamb.planificacion.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public List<CursoResponse> listar() {
        return cursoRepository.findAll().stream()
                .map(cursoMapper::toResponse)
                .toList();
    }

    public CursoResponse obtener(Long id) {
        return cursoMapper.toResponse(buscarOFallar(id));
    }

    public CursoResponse crear(CursoRequest request) {
        Curso curso = cursoMapper.toEntity(request);
        return cursoMapper.toResponse(cursoRepository.save(curso));
    }

    public CursoResponse actualizar(Long id, CursoRequest request) {
        Curso curso = buscarOFallar(id);
        curso.setCodigo(request.getCodigo());
        curso.setNombre(request.getNombre());
        curso.setCreditos(request.getCreditos());
        return cursoMapper.toResponse(cursoRepository.save(curso));
    }

    public void eliminar(Long id) {
        cursoRepository.delete(buscarOFallar(id));
    }

    Curso buscarOFallar(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + id));
    }
}
