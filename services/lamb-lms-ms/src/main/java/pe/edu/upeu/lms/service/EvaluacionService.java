package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.EvaluacionRequest;
import pe.edu.upeu.lms.dto.EvaluacionResponse;
import pe.edu.upeu.lms.entity.Curso;
import pe.edu.upeu.lms.entity.Evaluacion;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.EvaluacionMapper;
import pe.edu.upeu.lms.repository.CursoRepository;
import pe.edu.upeu.lms.repository.EvaluacionRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final EvaluacionMapper evaluacionMapper;
    private final CursoRepository cursoRepository;

    public List<EvaluacionResponse> listar() {
        return evaluacionRepository.findAllConCurso().stream()
                .map(evaluacionMapper::toResponse)
                .toList();
    }

    public EvaluacionResponse obtener(Long id) {
        return evaluacionMapper.toResponse(buscarOFallar(id));
    }

    public EvaluacionResponse crear(EvaluacionRequest request) {
        Evaluacion evaluacion = evaluacionMapper.toEntity(request);
        evaluacion.setCurso(buscarCursoOFallar(request.getCursoId()));
        return evaluacionMapper.toResponse(evaluacionRepository.save(evaluacion));
    }

    public EvaluacionResponse actualizar(Long id, EvaluacionRequest request) {
        Evaluacion evaluacion = buscarOFallar(id);
        evaluacion.setTitulo(request.getTitulo());
        evaluacion.setDescripcion(request.getDescripcion());
        evaluacion.setFechaEvaluacion(request.getFechaEvaluacion());
        evaluacion.setPuntajeMaximo(request.getPuntajeMaximo());
        evaluacion.setActivo(request.getActivo());
        evaluacion.setCurso(buscarCursoOFallar(request.getCursoId()));
        return evaluacionMapper.toResponse(evaluacionRepository.save(evaluacion));
    }

    public void eliminar(Long id) {
        evaluacionRepository.delete(buscarOFallar(id));
    }

    private Evaluacion buscarOFallar(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada: " + id));
    }

    private Curso buscarCursoOFallar(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + cursoId));
    }
}
