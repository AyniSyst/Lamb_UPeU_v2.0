package pe.edu.upeu.lamb.matricula.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.matricula.dto.RetiroInasistenciaRequest;
import pe.edu.upeu.lamb.matricula.dto.RetiroInasistenciaResponse;
import pe.edu.upeu.lamb.matricula.entity.RetiroInasistencia;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.RetiroInasistenciaMapper;
import pe.edu.upeu.lamb.matricula.repository.RetiroInasistenciaRepository;

import java.time.Instant;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/** CRUD opcional: depende de la universidad. */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RetiroInasistenciaService {

    private final RetiroInasistenciaRepository repository;
    private final RetiroInasistenciaMapper mapper;

    public List<RetiroInasistenciaResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public RetiroInasistenciaResponse obtener(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }

    public RetiroInasistenciaResponse crear(RetiroInasistenciaRequest request) {
        RetiroInasistencia entity = mapper.toEntity(request);
        entity.setFechaRetiro(Instant.now());
        return mapper.toResponse(repository.save(entity));
    }

    public RetiroInasistenciaResponse actualizar(Long id, RetiroInasistenciaRequest request) {
        RetiroInasistencia existente = buscarOFallar(id);
        RetiroInasistencia entity = mapper.toEntity(request);
        entity.setFechaRetiro(Instant.now());
        return mapper.toResponse(repository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        repository.delete(buscarOFallar(id));
    }

    private RetiroInasistencia buscarOFallar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RetiroInasistencia no encontrado: " + id));
    }
}
