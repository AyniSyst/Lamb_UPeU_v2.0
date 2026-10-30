package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.TipoSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoSedeResponse;
import pe.edu.upeu.lamb.institucional.entity.TipoSede;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.TipoSedeMapper;
import pe.edu.upeu.lamb.institucional.repository.TipoSedeRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TipoSedeService {

    private final TipoSedeRepository tipoSedeRepository;
    private final TipoSedeMapper tipoSedeMapper;

    public List<TipoSedeResponse> listar() {
        return tipoSedeRepository.findAll().stream()
                .map(tipoSedeMapper::toResponse)
                .toList();
    }

    public TipoSedeResponse obtener(Long id) {
        return tipoSedeMapper.toResponse(buscarOFallar(id));
    }

    public TipoSedeResponse crear(TipoSedeRequest request) {
        TipoSede entity = tipoSedeMapper.toEntity(request);
        return tipoSedeMapper.toResponse(tipoSedeRepository.save(entity));
    }

    public TipoSedeResponse actualizar(Long id, TipoSedeRequest request) {
        TipoSede existente = buscarOFallar(id);
        TipoSede entity = tipoSedeMapper.toEntity(request);
        return tipoSedeMapper.toResponse(tipoSedeRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        tipoSedeRepository.delete(buscarOFallar(id));
    }

    private TipoSede buscarOFallar(Long id) {
        return tipoSedeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de sede no encontrado: " + id));
    }
}
