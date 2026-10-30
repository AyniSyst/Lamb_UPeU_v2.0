package pe.edu.upeu.lamb.matricula.service;

import pe.edu.upeu.lamb.matricula.dto.TipoMatriculaRequest;
import pe.edu.upeu.lamb.matricula.dto.TipoMatriculaResponse;
import pe.edu.upeu.lamb.matricula.entity.TipoMatricula;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.TipoMatriculaMapper;
import pe.edu.upeu.lamb.matricula.repository.TipoMatriculaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TipoMatriculaService {

    private final TipoMatriculaRepository tipoMatriculaRepository;
    private final TipoMatriculaMapper tipoMatriculaMapper;

    public List<TipoMatriculaResponse> listar() {
        return tipoMatriculaRepository.findAll().stream()
                .map(tipoMatriculaMapper::toResponse)
                .toList();
    }

    public TipoMatriculaResponse obtener(Long id) {
        return tipoMatriculaMapper.toResponse(buscarOFallar(id));
    }

    public TipoMatriculaResponse crear(TipoMatriculaRequest request) {
        TipoMatricula entity = tipoMatriculaMapper.toEntity(request);
        return tipoMatriculaMapper.toResponse(tipoMatriculaRepository.save(entity));
    }

    public TipoMatriculaResponse actualizar(Long id, TipoMatriculaRequest request) {
        TipoMatricula existente = buscarOFallar(id);
        TipoMatricula entity = tipoMatriculaMapper.toEntity(request);
        return tipoMatriculaMapper.toResponse(tipoMatriculaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        tipoMatriculaRepository.delete(buscarOFallar(id));
    }

    private TipoMatricula buscarOFallar(Long id) {
        return tipoMatriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de matricula no encontrado: " + id));
    }
}
