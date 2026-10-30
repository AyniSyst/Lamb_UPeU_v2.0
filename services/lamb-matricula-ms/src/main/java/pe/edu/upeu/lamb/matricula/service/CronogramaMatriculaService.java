package pe.edu.upeu.lamb.matricula.service;

import pe.edu.upeu.lamb.matricula.dto.CronogramaMatriculaRequest;
import pe.edu.upeu.lamb.matricula.dto.CronogramaMatriculaResponse;
import pe.edu.upeu.lamb.matricula.entity.CronogramaMatricula;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.CronogramaMatriculaMapper;
import pe.edu.upeu.lamb.matricula.repository.CronogramaMatriculaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CronogramaMatriculaService {

    private final CronogramaMatriculaRepository cronogramaMatriculaRepository;
    private final CronogramaMatriculaMapper cronogramaMatriculaMapper;

    public List<CronogramaMatriculaResponse> listar() {
        return cronogramaMatriculaRepository.findAll().stream()
                .map(cronogramaMatriculaMapper::toResponse)
                .toList();
    }

    public CronogramaMatriculaResponse obtener(Long id) {
        return cronogramaMatriculaMapper.toResponse(buscarOFallar(id));
    }

    public CronogramaMatriculaResponse crear(CronogramaMatriculaRequest request) {
        CronogramaMatricula entity = cronogramaMatriculaMapper.toEntity(request);
        return cronogramaMatriculaMapper.toResponse(cronogramaMatriculaRepository.save(entity));
    }

    public CronogramaMatriculaResponse actualizar(Long id, CronogramaMatriculaRequest request) {
        CronogramaMatricula existente = buscarOFallar(id);
        CronogramaMatricula entity = cronogramaMatriculaMapper.toEntity(request);
        return cronogramaMatriculaMapper.toResponse(cronogramaMatriculaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        cronogramaMatriculaRepository.delete(buscarOFallar(id));
    }

    private CronogramaMatricula buscarOFallar(Long id) {
        return cronogramaMatriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cronograma de matricula no encontrado: " + id));
    }
}
