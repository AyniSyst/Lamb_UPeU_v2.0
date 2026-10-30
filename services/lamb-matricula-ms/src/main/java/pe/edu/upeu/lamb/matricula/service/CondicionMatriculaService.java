package pe.edu.upeu.lamb.matricula.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.matricula.dto.CondicionMatriculaRequest;
import pe.edu.upeu.lamb.matricula.dto.CondicionMatriculaResponse;
import pe.edu.upeu.lamb.matricula.entity.CondicionMatricula;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.CondicionMatriculaMapper;
import pe.edu.upeu.lamb.matricula.repository.CondicionMatriculaRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CondicionMatriculaService {

    private final CondicionMatriculaRepository repository;
    private final CondicionMatriculaMapper mapper;

    public List<CondicionMatriculaResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public CondicionMatriculaResponse obtener(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }

    public CondicionMatriculaResponse crear(CondicionMatriculaRequest request) {
        CondicionMatricula entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public CondicionMatriculaResponse actualizar(Long id, CondicionMatriculaRequest request) {
        CondicionMatricula existente = buscarOFallar(id);
        CondicionMatricula entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        repository.delete(buscarOFallar(id));
    }

    CondicionMatricula buscarOFallar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CondicionMatricula no encontrada: " + id));
    }
}
