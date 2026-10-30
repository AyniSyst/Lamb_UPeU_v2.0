package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.ReprogramacionEventoRequest;
import pe.edu.upeu.lamb.institucional.dto.ReprogramacionEventoResponse;
import pe.edu.upeu.lamb.institucional.entity.ReprogramacionEvento;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ReprogramacionEventoMapper;
import pe.edu.upeu.lamb.institucional.repository.ReprogramacionEventoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReprogramacionEventoService {

    private final ReprogramacionEventoRepository reprogramacionEventoRepository;
    private final ReprogramacionEventoMapper reprogramacionEventoMapper;

    public List<ReprogramacionEventoResponse> listar() {
        return reprogramacionEventoRepository.findAll().stream()
                .map(reprogramacionEventoMapper::toResponse)
                .toList();
    }

    public ReprogramacionEventoResponse obtener(Long id) {
        return reprogramacionEventoMapper.toResponse(buscarOFallar(id));
    }

    public ReprogramacionEventoResponse crear(ReprogramacionEventoRequest request) {
        ReprogramacionEvento entity = reprogramacionEventoMapper.toEntity(request);
        return reprogramacionEventoMapper.toResponse(reprogramacionEventoRepository.save(entity));
    }

    public ReprogramacionEventoResponse actualizar(Long id, ReprogramacionEventoRequest request) {
        ReprogramacionEvento existente = buscarOFallar(id);
        ReprogramacionEvento entity = reprogramacionEventoMapper.toEntity(request);
        return reprogramacionEventoMapper.toResponse(reprogramacionEventoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        reprogramacionEventoRepository.delete(buscarOFallar(id));
    }

    private ReprogramacionEvento buscarOFallar(Long id) {
        return reprogramacionEventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReprogramacionEvento no encontrado con id " + id));
    }
}
