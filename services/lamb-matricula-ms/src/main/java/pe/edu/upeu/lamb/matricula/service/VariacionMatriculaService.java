package pe.edu.upeu.lamb.matricula.service;

import pe.edu.upeu.lamb.matricula.dto.VariacionMatriculaRequest;
import pe.edu.upeu.lamb.matricula.dto.VariacionMatriculaResponse;
import pe.edu.upeu.lamb.matricula.entity.VariacionMatricula;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.VariacionMatriculaMapper;
import pe.edu.upeu.lamb.matricula.repository.VariacionMatriculaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VariacionMatriculaService {

    private final VariacionMatriculaRepository variacionMatriculaRepository;
    private final VariacionMatriculaMapper variacionMatriculaMapper;

    public List<VariacionMatriculaResponse> listar() {
        return variacionMatriculaRepository.findAll().stream()
                .map(variacionMatriculaMapper::toResponse)
                .toList();
    }

    public VariacionMatriculaResponse obtener(Long id) {
        return variacionMatriculaMapper.toResponse(buscarOFallar(id));
    }

    public VariacionMatriculaResponse crear(VariacionMatriculaRequest request) {
        VariacionMatricula entity = variacionMatriculaMapper.toEntity(request);
        return variacionMatriculaMapper.toResponse(variacionMatriculaRepository.save(entity));
    }

    public VariacionMatriculaResponse actualizar(Long id, VariacionMatriculaRequest request) {
        VariacionMatricula existente = buscarOFallar(id);
        VariacionMatricula entity = variacionMatriculaMapper.toEntity(request);
        return variacionMatriculaMapper.toResponse(variacionMatriculaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        variacionMatriculaRepository.delete(buscarOFallar(id));
    }

    private VariacionMatricula buscarOFallar(Long id) {
        return variacionMatriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Variacion de matricula no encontrada: " + id));
    }
}
