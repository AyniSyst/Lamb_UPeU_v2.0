package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.TipoParentescoRequest;
import pe.edu.upeu.lamb.personas.dto.TipoParentescoResponse;
import pe.edu.upeu.lamb.personas.entity.TipoParentesco;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.TipoParentescoMapper;
import pe.edu.upeu.lamb.personas.repository.TipoParentescoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoParentescoService {

    private final TipoParentescoRepository tipoParentescoRepository;
    private final TipoParentescoMapper tipoParentescoMapper;

    public List<TipoParentescoResponse> listar() {
        return tipoParentescoRepository.findAll().stream()
                .map(tipoParentescoMapper::toResponse)
                .toList();
    }

    public TipoParentescoResponse obtener(Long id) {
        return tipoParentescoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TipoParentescoResponse crear(TipoParentescoRequest request) {
        TipoParentesco entity = tipoParentescoMapper.toEntity(request);
        return tipoParentescoMapper.toResponse(tipoParentescoRepository.save(entity));
    }

    public TipoParentescoResponse actualizar(Long id, TipoParentescoRequest request) {
        TipoParentesco existente = buscarOFallar(id);
        TipoParentesco entity = tipoParentescoMapper.toEntity(request);
        return tipoParentescoMapper.toResponse(tipoParentescoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        tipoParentescoRepository.delete(buscarOFallar(id));
    }

    private TipoParentesco buscarOFallar(Long id) {
        return tipoParentescoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de parentesco no encontrado: " + id));
    }
}
