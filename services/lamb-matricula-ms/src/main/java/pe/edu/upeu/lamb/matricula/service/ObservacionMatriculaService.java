package pe.edu.upeu.lamb.matricula.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.matricula.dto.ObservacionMatriculaRequest;
import pe.edu.upeu.lamb.matricula.dto.ObservacionMatriculaResponse;
import pe.edu.upeu.lamb.matricula.entity.ObservacionMatricula;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.ObservacionMatriculaMapper;
import pe.edu.upeu.lamb.matricula.repository.ObservacionMatriculaRepository;

import java.time.Instant;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ObservacionMatriculaService {

    private final ObservacionMatriculaRepository observacionMatriculaRepository;
    private final ObservacionMatriculaMapper observacionMatriculaMapper;

    public List<ObservacionMatriculaResponse> listar() {
        return observacionMatriculaRepository.findAll().stream()
                .map(observacionMatriculaMapper::toResponse)
                .toList();
    }

    public ObservacionMatriculaResponse obtener(Long id) {
        return observacionMatriculaMapper.toResponse(buscarOFallar(id));
    }

    public ObservacionMatriculaResponse crear(ObservacionMatriculaRequest request) {
        ObservacionMatricula entity = observacionMatriculaMapper.toEntity(request);
        entity.setFechaObservacion(Instant.now());
        return observacionMatriculaMapper.toResponse(observacionMatriculaRepository.save(entity));
    }

    public ObservacionMatriculaResponse actualizar(Long id, ObservacionMatriculaRequest request) {
        ObservacionMatricula existente = buscarOFallar(id);
        ObservacionMatricula entity = observacionMatriculaMapper.toEntity(request);
        entity.setFechaObservacion(Instant.now());
        return observacionMatriculaMapper.toResponse(observacionMatriculaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        observacionMatriculaRepository.delete(buscarOFallar(id));
    }

    private ObservacionMatricula buscarOFallar(Long id) {
        return observacionMatriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Observacion de matricula no encontrada: " + id));
    }
}
