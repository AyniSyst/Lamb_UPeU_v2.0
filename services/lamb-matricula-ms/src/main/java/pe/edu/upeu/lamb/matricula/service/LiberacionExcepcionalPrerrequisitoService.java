package pe.edu.upeu.lamb.matricula.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.matricula.dto.LiberacionExcepcionalPrerrequisitoRequest;
import pe.edu.upeu.lamb.matricula.dto.LiberacionExcepcionalPrerrequisitoResponse;
import pe.edu.upeu.lamb.matricula.entity.LiberacionExcepcionalPrerrequisito;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.LiberacionExcepcionalPrerrequisitoMapper;
import pe.edu.upeu.lamb.matricula.repository.LiberacionExcepcionalPrerrequisitoRepository;

import java.time.Instant;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LiberacionExcepcionalPrerrequisitoService {

    private final LiberacionExcepcionalPrerrequisitoRepository liberacionExcepcionalPrerrequisitoRepository;
    private final LiberacionExcepcionalPrerrequisitoMapper liberacionExcepcionalPrerrequisitoMapper;

    public List<LiberacionExcepcionalPrerrequisitoResponse> listar() {
        return liberacionExcepcionalPrerrequisitoRepository.findAll().stream()
                .map(liberacionExcepcionalPrerrequisitoMapper::toResponse)
                .toList();
    }

    public LiberacionExcepcionalPrerrequisitoResponse obtener(Long id) {
        return liberacionExcepcionalPrerrequisitoMapper.toResponse(buscarOFallar(id));
    }

    public LiberacionExcepcionalPrerrequisitoResponse crear(LiberacionExcepcionalPrerrequisitoRequest request) {
        LiberacionExcepcionalPrerrequisito liberacionExcepcionalPrerrequisito = liberacionExcepcionalPrerrequisitoMapper.toEntity(request);
        liberacionExcepcionalPrerrequisito.setFechaAutorizacion(Instant.now());
        return liberacionExcepcionalPrerrequisitoMapper.toResponse(liberacionExcepcionalPrerrequisitoRepository.save(liberacionExcepcionalPrerrequisito));
    }

    public LiberacionExcepcionalPrerrequisitoResponse actualizar(Long id, LiberacionExcepcionalPrerrequisitoRequest request) {
        LiberacionExcepcionalPrerrequisito existente = buscarOFallar(id);
        LiberacionExcepcionalPrerrequisito liberacionExcepcionalPrerrequisito = liberacionExcepcionalPrerrequisitoMapper.toEntity(request);
        liberacionExcepcionalPrerrequisito.setFechaAutorizacion(Instant.now());
        return liberacionExcepcionalPrerrequisitoMapper.toResponse(liberacionExcepcionalPrerrequisitoRepository.save(Actualizacion.copiar(liberacionExcepcionalPrerrequisito, existente)));
    }

    public void eliminar(Long id) {
        liberacionExcepcionalPrerrequisitoRepository.delete(buscarOFallar(id));
    }

    private LiberacionExcepcionalPrerrequisito buscarOFallar(Long id) {
        return liberacionExcepcionalPrerrequisitoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LiberacionExcepcionalPrerrequisito no encontrada: " + id));
    }
}
