package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.RubricaEvaluacionRequest;
import pe.edu.upeu.lms.dto.RubricaEvaluacionResponse;
import pe.edu.upeu.lms.entity.RubricaEvaluacion;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.RubricaEvaluacionMapper;
import pe.edu.upeu.lms.repository.RubricaEvaluacionRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RubricaEvaluacionService {

    private final RubricaEvaluacionRepository rubricaEvaluacionRepository;
    private final RubricaEvaluacionMapper rubricaEvaluacionMapper;

    public List<RubricaEvaluacionResponse> listar() {
        return rubricaEvaluacionRepository.findAll().stream()
                .map(rubricaEvaluacionMapper::toResponse)
                .toList();
    }

    public RubricaEvaluacionResponse obtener(Long id) {
        return rubricaEvaluacionMapper.toResponse(buscarOFallar(id));
    }

    public RubricaEvaluacionResponse crear(RubricaEvaluacionRequest request) {
        RubricaEvaluacion rubricaEvaluacion = rubricaEvaluacionMapper.toEntity(request);
        return rubricaEvaluacionMapper.toResponse(rubricaEvaluacionRepository.save(rubricaEvaluacion));
    }

    public RubricaEvaluacionResponse actualizar(Long id, RubricaEvaluacionRequest request) {
        RubricaEvaluacion rubricaEvaluacion = buscarOFallar(id);
        rubricaEvaluacion.setNombre(request.getNombre());
        rubricaEvaluacion.setDescripcion(request.getDescripcion());
        rubricaEvaluacion.setPuntajeMaximo(request.getPuntajeMaximo());
        rubricaEvaluacion.setActivo(request.getActivo());
        return rubricaEvaluacionMapper.toResponse(rubricaEvaluacionRepository.save(rubricaEvaluacion));
    }

    public void eliminar(Long id) {
        rubricaEvaluacionRepository.delete(buscarOFallar(id));
    }

    private RubricaEvaluacion buscarOFallar(Long id) {
        return rubricaEvaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rubrica de evaluacion no encontrada: " + id));
    }
}
