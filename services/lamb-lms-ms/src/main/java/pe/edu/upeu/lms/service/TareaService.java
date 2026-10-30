package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.TareaRequest;
import pe.edu.upeu.lms.dto.TareaResponse;
import pe.edu.upeu.lms.entity.Curso;
import pe.edu.upeu.lms.entity.Tarea;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.TareaMapper;
import pe.edu.upeu.lms.repository.CursoRepository;
import pe.edu.upeu.lms.repository.TareaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TareaService {

    private final TareaRepository tareaRepository;
    private final TareaMapper tareaMapper;
    private final CursoRepository cursoRepository;

    public List<TareaResponse> listar() {
        return tareaRepository.findAllConCurso().stream()
                .map(tareaMapper::toResponse)
                .toList();
    }

    public TareaResponse obtener(Long id) {
        return tareaMapper.toResponse(buscarOFallar(id));
    }

    public TareaResponse crear(TareaRequest request) {
        Tarea tarea = tareaMapper.toEntity(request);
        tarea.setCurso(buscarCursoOFallar(request.getCursoId()));
        return tareaMapper.toResponse(tareaRepository.save(tarea));
    }

    public TareaResponse actualizar(Long id, TareaRequest request) {
        Tarea tarea = buscarOFallar(id);
        tarea.setTitulo(request.getTitulo());
        tarea.setDescripcion(request.getDescripcion());
        tarea.setFechaLimite(request.getFechaLimite());
        tarea.setPuntajeMaximo(request.getPuntajeMaximo());
        tarea.setActivo(request.getActivo());
        tarea.setCurso(buscarCursoOFallar(request.getCursoId()));
        return tareaMapper.toResponse(tareaRepository.save(tarea));
    }

    public void eliminar(Long id) {
        tareaRepository.delete(buscarOFallar(id));
    }

    private Tarea buscarOFallar(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada: " + id));
    }

    private Curso buscarCursoOFallar(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + cursoId));
    }
}
