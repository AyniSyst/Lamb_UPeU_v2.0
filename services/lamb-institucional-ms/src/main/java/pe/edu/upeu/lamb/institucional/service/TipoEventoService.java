package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.TipoEventoRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoEventoResponse;
import pe.edu.upeu.lamb.institucional.entity.TipoEvento;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.TipoEventoMapper;
import pe.edu.upeu.lamb.institucional.repository.TipoEventoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TipoEventoService {

    private final TipoEventoRepository tipoEventoRepository;
    private final TipoEventoMapper tipoEventoMapper;

    public List<TipoEventoResponse> listar() {
        return tipoEventoRepository.findAll().stream()
                .map(tipoEventoMapper::toResponse)
                .toList();
    }

    public TipoEventoResponse obtener(Long id) {
        return tipoEventoMapper.toResponse(buscarOFallar(id));
    }

    public TipoEventoResponse crear(TipoEventoRequest request) {
        TipoEvento entity = tipoEventoMapper.toEntity(request);
        return tipoEventoMapper.toResponse(tipoEventoRepository.save(entity));
    }

    public TipoEventoResponse actualizar(Long id, TipoEventoRequest request) {
        TipoEvento existente = buscarOFallar(id);
        TipoEvento entity = tipoEventoMapper.toEntity(request);
        return tipoEventoMapper.toResponse(tipoEventoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        tipoEventoRepository.delete(buscarOFallar(id));
    }

    private TipoEvento buscarOFallar(Long id) {
        return tipoEventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoEvento no encontrado con id " + id));
    }
}
