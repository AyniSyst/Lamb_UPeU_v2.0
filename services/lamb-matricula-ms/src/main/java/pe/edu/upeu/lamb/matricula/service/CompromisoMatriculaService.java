package pe.edu.upeu.lamb.matricula.service;

import pe.edu.upeu.lamb.matricula.dto.CompromisoMatriculaRequest;
import pe.edu.upeu.lamb.matricula.dto.CompromisoMatriculaResponse;
import pe.edu.upeu.lamb.matricula.entity.CompromisoMatricula;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.CompromisoMatriculaMapper;
import pe.edu.upeu.lamb.matricula.repository.CompromisoMatriculaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CompromisoMatriculaService {

    private final CompromisoMatriculaRepository compromisoMatriculaRepository;
    private final CompromisoMatriculaMapper compromisoMatriculaMapper;

    public List<CompromisoMatriculaResponse> listar() {
        return compromisoMatriculaRepository.findAll().stream()
                .map(compromisoMatriculaMapper::toResponse)
                .toList();
    }

    public CompromisoMatriculaResponse obtener(Long id) {
        return compromisoMatriculaMapper.toResponse(buscarOFallar(id));
    }

    public CompromisoMatriculaResponse crear(CompromisoMatriculaRequest request) {
        CompromisoMatricula entity = compromisoMatriculaMapper.toEntity(request);
        return compromisoMatriculaMapper.toResponse(compromisoMatriculaRepository.save(entity));
    }

    public CompromisoMatriculaResponse actualizar(Long id, CompromisoMatriculaRequest request) {
        CompromisoMatricula existente = buscarOFallar(id);
        CompromisoMatricula entity = compromisoMatriculaMapper.toEntity(request);
        return compromisoMatriculaMapper.toResponse(compromisoMatriculaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        compromisoMatriculaRepository.delete(buscarOFallar(id));
    }

    private CompromisoMatricula buscarOFallar(Long id) {
        return compromisoMatriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compromiso de matricula no encontrado: " + id));
    }
}
