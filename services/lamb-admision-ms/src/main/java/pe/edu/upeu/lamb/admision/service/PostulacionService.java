package pe.edu.upeu.lamb.admision.service;

import pe.edu.upeu.lamb.admision.dto.EvaluarPostulacionRequest;
import pe.edu.upeu.lamb.admision.dto.PostulacionRequest;
import pe.edu.upeu.lamb.admision.dto.PostulacionResponse;
import pe.edu.upeu.lamb.admision.entity.EstadoPostulacion;
import pe.edu.upeu.lamb.admision.entity.Postulacion;
import pe.edu.upeu.lamb.admision.event.PostulanteAdmitidoEvento;
import pe.edu.upeu.lamb.admision.exception.BusinessRuleException;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.mapper.PostulacionMapper;
import pe.edu.upeu.lamb.admision.messaging.AdmisionEventosProducer;
import pe.edu.upeu.lamb.admision.repository.PostulacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostulacionService {

    private static final BigDecimal NOTA_MINIMA_ADMISION = BigDecimal.valueOf(11);
    private static final String POSTULANTE_ADMITIDO = "postulante.admitido";

    private final PostulacionRepository postulacionRepository;
    private final PostulacionMapper postulacionMapper;
    private final AdmisionEventosProducer producer;

    @Value("${spring.application.name}")
    private String nombreServicio;

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
        validarEstado(postulacion, EstadoPostulacion.INSCRITO, "evaluar");
        postulacion.setPuntajeExamen(request.getPuntajeExamen());
        postulacion.setEstado(EstadoPostulacion.EVALUADO);
        return postulacionMapper.toResponse(postulacionRepository.save(postulacion));
    }

    /**
     * Solo una postulacion EVALUADA con puntaje >= 11 se admite. Publica postulante.admitido
     * para que trayectoria abra la trayectoria del estudiante.
     */
    @Transactional
    public PostulacionResponse admitir(Long id) {
        Postulacion postulacion = buscarOFallar(id);
        validarEstado(postulacion, EstadoPostulacion.EVALUADO, "admitir");
        if (postulacion.getPuntajeExamen().compareTo(NOTA_MINIMA_ADMISION) < 0) {
            throw new BusinessRuleException("Puntaje " + postulacion.getPuntajeExamen() + " menor a la nota minima de admision (" + NOTA_MINIMA_ADMISION + ")");
        }
        postulacion.setEstado(EstadoPostulacion.ADMITIDO);
        Postulacion guardada = postulacionRepository.save(postulacion);

        producer.publicarTrasCommit(PostulanteAdmitidoEvento.builder()
                .tipoEvento(POSTULANTE_ADMITIDO)
                .postulacionId(guardada.getId())
                .idPersona(guardada.getIdPersona())
                .idPrograma(guardada.getIdPrograma())
                .periodo(guardada.getPeriodo())
                .puntajeExamen(guardada.getPuntajeExamen())
                .origen(nombreServicio)
                .timestamp(Instant.now().toEpochMilli())
                .build());
        log.info("component=processor postulacionId={} idPersona={} estado={} status=processed",
                guardada.getId(), guardada.getIdPersona(), guardada.getEstado());
        return postulacionMapper.toResponse(guardada);
    }

    public PostulacionResponse rechazar(Long id) {
        Postulacion postulacion = buscarOFallar(id);
        validarEstado(postulacion, EstadoPostulacion.EVALUADO, "rechazar");
        postulacion.setEstado(EstadoPostulacion.RECHAZADO);
        return postulacionMapper.toResponse(postulacionRepository.save(postulacion));
    }

    private void validarEstado(Postulacion postulacion, EstadoPostulacion esperado, String accion) {
        if (postulacion.getEstado() != esperado) {
            throw new BusinessRuleException("No se puede " + accion + " la postulacion " + postulacion.getId()
                    + ": esta " + postulacion.getEstado() + " y debe estar " + esperado);
        }
    }

    private Postulacion buscarOFallar(Long id) {
        return postulacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postulacion no encontrada: " + id));
    }
}
