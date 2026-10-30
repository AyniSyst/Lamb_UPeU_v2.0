package pe.edu.upeu.lamb.matricula.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.matricula.dto.IntentoCursoRequest;
import pe.edu.upeu.lamb.matricula.dto.IntentoCursoResponse;
import pe.edu.upeu.lamb.matricula.entity.IntentoCurso;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.IntentoCursoMapper;
import pe.edu.upeu.lamb.matricula.repository.IntentoCursoRepository;

import java.time.Instant;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class IntentoCursoService {

    private final IntentoCursoRepository intentoCursoRepository;
    private final IntentoCursoMapper intentoCursoMapper;

    public List<IntentoCursoResponse> listar() {
        return intentoCursoRepository.findAll().stream()
                .map(intentoCursoMapper::toResponse)
                .toList();
    }

    public IntentoCursoResponse obtener(Long id) {
        return intentoCursoMapper.toResponse(buscarOFallar(id));
    }

    public IntentoCursoResponse crear(IntentoCursoRequest request) {
        IntentoCurso intentoCurso = intentoCursoMapper.toEntity(request);
        intentoCurso.setFechaRegistro(Instant.now());
        return intentoCursoMapper.toResponse(intentoCursoRepository.save(intentoCurso));
    }

    public IntentoCursoResponse actualizar(Long id, IntentoCursoRequest request) {
        IntentoCurso existente = buscarOFallar(id);
        IntentoCurso intentoCurso = intentoCursoMapper.toEntity(request);
        intentoCurso.setFechaRegistro(Instant.now());
        return intentoCursoMapper.toResponse(intentoCursoRepository.save(Actualizacion.copiar(intentoCurso, existente)));
    }

    public void eliminar(Long id) {
        intentoCursoRepository.delete(buscarOFallar(id));
    }

    private IntentoCurso buscarOFallar(Long id) {
        return intentoCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IntentoCurso no encontrado: " + id));
    }
}
