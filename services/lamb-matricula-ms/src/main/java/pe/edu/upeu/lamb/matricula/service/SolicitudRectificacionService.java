package pe.edu.upeu.lamb.matricula.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.matricula.dto.SolicitudRectificacionRequest;
import pe.edu.upeu.lamb.matricula.dto.SolicitudRectificacionResponse;
import pe.edu.upeu.lamb.matricula.entity.SolicitudRectificacion;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.SolicitudRectificacionMapper;
import pe.edu.upeu.lamb.matricula.repository.SolicitudRectificacionRepository;

import java.time.Instant;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SolicitudRectificacionService {

    private final SolicitudRectificacionRepository repository;
    private final SolicitudRectificacionMapper mapper;

    public List<SolicitudRectificacionResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public SolicitudRectificacionResponse obtener(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }

    public SolicitudRectificacionResponse crear(SolicitudRectificacionRequest request) {
        SolicitudRectificacion entity = mapper.toEntity(request);
        entity.setEstado("PENDIENTE");
        entity.setFechaSolicitud(Instant.now());
        return mapper.toResponse(repository.save(entity));
    }

    public SolicitudRectificacionResponse actualizar(Long id, SolicitudRectificacionRequest request) {
        SolicitudRectificacion existente = buscarOFallar(id);
        SolicitudRectificacion entity = mapper.toEntity(request);
        entity.setEstado("PENDIENTE");
        entity.setFechaSolicitud(Instant.now());
        return mapper.toResponse(repository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        repository.delete(buscarOFallar(id));
    }

    private SolicitudRectificacion buscarOFallar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SolicitudRectificacion no encontrada: " + id));
    }
}
