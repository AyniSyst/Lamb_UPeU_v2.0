package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.PreguntaRequest;
import pe.edu.upeu.lms.dto.PreguntaResponse;
import pe.edu.upeu.lms.entity.Evaluacion;
import pe.edu.upeu.lms.entity.Pregunta;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.PreguntaMapper;
import pe.edu.upeu.lms.repository.EvaluacionRepository;
import pe.edu.upeu.lms.repository.PreguntaRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PreguntaService {

    private final PreguntaRepository preguntaRepository;
    private final PreguntaMapper preguntaMapper;
    private final EvaluacionRepository evaluacionRepository;

    public List<PreguntaResponse> listar() {
        return preguntaRepository.findAllConEvaluacion().stream()
                .map(preguntaMapper::toResponse)
                .toList();
    }

    public PreguntaResponse obtener(Long id) {
        return preguntaMapper.toResponse(buscarOFallar(id));
    }

    public PreguntaResponse crear(PreguntaRequest request) {
        Pregunta pregunta = preguntaMapper.toEntity(request);
        pregunta.setEvaluacion(buscarEvaluacionOFallar(request.getEvaluacionId()));
        return preguntaMapper.toResponse(preguntaRepository.save(pregunta));
    }

    public PreguntaResponse actualizar(Long id, PreguntaRequest request) {
        Pregunta pregunta = buscarOFallar(id);
        pregunta.setEnunciado(request.getEnunciado());
        pregunta.setTipo(request.getTipo());
        pregunta.setPuntaje(request.getPuntaje());
        pregunta.setEvaluacion(buscarEvaluacionOFallar(request.getEvaluacionId()));
        return preguntaMapper.toResponse(preguntaRepository.save(pregunta));
    }

    public void eliminar(Long id) {
        preguntaRepository.delete(buscarOFallar(id));
    }

    private Pregunta buscarOFallar(Long id) {
        return preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada: " + id));
    }

    private Evaluacion buscarEvaluacionOFallar(Long evaluacionId) {
        return evaluacionRepository.findById(evaluacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada: " + evaluacionId));
    }
}
