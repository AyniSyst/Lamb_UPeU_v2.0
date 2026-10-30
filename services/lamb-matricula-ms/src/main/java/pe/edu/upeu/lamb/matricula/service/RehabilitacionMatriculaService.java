package pe.edu.upeu.lamb.matricula.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.matricula.dto.RehabilitacionMatriculaRequest;
import pe.edu.upeu.lamb.matricula.dto.RehabilitacionMatriculaResponse;
import pe.edu.upeu.lamb.matricula.entity.RehabilitacionMatricula;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.RehabilitacionMatriculaMapper;
import pe.edu.upeu.lamb.matricula.repository.RehabilitacionMatriculaRepository;

import java.time.Instant;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/** CRUD opcional: depende de la universidad. */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RehabilitacionMatriculaService {

    private final RehabilitacionMatriculaRepository repository;
    private final RehabilitacionMatriculaMapper mapper;

    public List<RehabilitacionMatriculaResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public RehabilitacionMatriculaResponse obtener(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }

    public RehabilitacionMatriculaResponse crear(RehabilitacionMatriculaRequest request) {
        RehabilitacionMatricula entity = mapper.toEntity(request);
        entity.setFechaRehabilitacion(Instant.now());
        return mapper.toResponse(repository.save(entity));
    }

    public RehabilitacionMatriculaResponse actualizar(Long id, RehabilitacionMatriculaRequest request) {
        RehabilitacionMatricula existente = buscarOFallar(id);
        RehabilitacionMatricula entity = mapper.toEntity(request);
        entity.setFechaRehabilitacion(Instant.now());
        return mapper.toResponse(repository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        repository.delete(buscarOFallar(id));
    }

    private RehabilitacionMatricula buscarOFallar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RehabilitacionMatricula no encontrada: " + id));
    }
}
