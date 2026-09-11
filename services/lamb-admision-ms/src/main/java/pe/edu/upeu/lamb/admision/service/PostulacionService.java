package pe.edu.upeu.lamb.admision.service;

import pe.edu.upeu.lamb.admision.dto.EvaluarPostulacionRequest;
import pe.edu.upeu.lamb.admision.dto.PostulacionRequest;
import pe.edu.upeu.lamb.admision.dto.PostulacionResponse;
import pe.edu.upeu.lamb.admision.entity.EstadoPostulacion;
import pe.edu.upeu.lamb.admision.entity.Postulacion;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.mapper.PostulacionMapper;
import pe.edu.upeu.lamb.admision.repository.PostulacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostulacionService {

    private static final BigDecimal NOTA_MINIMA_ADMISION = BigDecimal.valueOf(11);

    private final PostulacionRepository postulacionRepository;
    private final PostulacionMapper postulacionMapper;

    public List<PostulacionResponse> listar() {
        return postulacionRepository.findAll().stream()
                .map(postulacionMapper::toResponse)
                .toList();
    }

    public PostulacionResponse obtener(Long id) {
        return postulacionMapper.toResponse(buscarOFallar(id));
    }

    public PostulacionResponse crear(PostulacionRequest request) {
        Postulacion postulacion = postulacionMapper.toEntity(request);
        return postulacionMapper.toResponse(postulacionRepository.save(postulacion));
    }

    public PostulacionResponse evaluar(Long id, EvaluarPostulacionRequest request) {
        Postulacion postulacion = buscarOFallar(id);
        postulacion.setPuntajeExamen(request.getPuntajeExamen());
        postulacion.setEstado(EstadoPostulacion.EVALUADO);
        return postulacionMapper.toResponse(postulacionRepository.save(postulacion));
    }

    public PostulacionResponse admitir(Long id) {
        Postulacion postulacion = buscarOFallar(id);
        postulacion.setEstado(EstadoPostulacion.ADMITIDO);
        return postulacionMapper.toResponse(postulacionRepository.save(postulacion));
    }

    public PostulacionResponse rechazar(Long id) {
        Postulacion postulacion = buscarOFallar(id);
        postulacion.setEstado(EstadoPostulacion.RECHAZADO);
        return postulacionMapper.toResponse(postulacionRepository.save(postulacion));
    }

    private Postulacion buscarOFallar(Long id) {
        return postulacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postulacion no encontrada: " + id));
    }
}
