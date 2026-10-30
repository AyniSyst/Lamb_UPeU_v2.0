package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.CalificacionRequest;
import pe.edu.upeu.lms.dto.CalificacionResponse;
import pe.edu.upeu.lms.entity.Calificacion;
import pe.edu.upeu.lms.entity.Evaluacion;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.CalificacionMapper;
import pe.edu.upeu.lms.repository.CalificacionRepository;
import pe.edu.upeu.lms.repository.EvaluacionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalificacionService {

    private final CalificacionRepository calificacionRepository;
    private final CalificacionMapper calificacionMapper;
    private final EvaluacionRepository evaluacionRepository;

    public List<CalificacionResponse> listar() {
        return calificacionRepository.findAllConEvaluacion().stream()
                .map(calificacionMapper::toResponse)
                .toList();
    }

    public CalificacionResponse obtener(Long id) {
        return calificacionMapper.toResponse(buscarOFallar(id));
    }

    public CalificacionResponse crear(CalificacionRequest request) {
        Calificacion calificacion = calificacionMapper.toEntity(request);
        calificacion.setEvaluacion(buscarEvaluacionOFallar(request.getEvaluacionId()));
        return calificacionMapper.toResponse(calificacionRepository.save(calificacion));
    }

    public CalificacionResponse actualizar(Long id, CalificacionRequest request) {
        Calificacion calificacion = buscarOFallar(id);
        calificacion.setIdEstudiante(request.getIdEstudiante());
        calificacion.setNota(request.getNota());
        calificacion.setEvaluacion(buscarEvaluacionOFallar(request.getEvaluacionId()));
        return calificacionMapper.toResponse(calificacionRepository.save(calificacion));
    }

    public void eliminar(Long id) {
        calificacionRepository.delete(buscarOFallar(id));
    }

    private Calificacion buscarOFallar(Long id) {
        return calificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificacion no encontrada: " + id));
    }

    private Evaluacion buscarEvaluacionOFallar(Long evaluacionId) {
        return evaluacionRepository.findById(evaluacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada: " + evaluacionId));
    }
}
