package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.TipoPeriodoRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoPeriodoResponse;
import pe.edu.upeu.lamb.institucional.entity.TipoPeriodo;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.TipoPeriodoMapper;
import pe.edu.upeu.lamb.institucional.repository.TipoPeriodoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoPeriodoService {

    private final TipoPeriodoRepository tipoPeriodoRepository;
    private final TipoPeriodoMapper tipoPeriodoMapper;

    public List<TipoPeriodoResponse> listar() {
        return tipoPeriodoRepository.findAll().stream()
                .map(tipoPeriodoMapper::toResponse)
                .toList();
    }

    public TipoPeriodoResponse obtener(Long id) {
        return tipoPeriodoMapper.toResponse(buscarOFallar(id));
    }

    public TipoPeriodoResponse crear(TipoPeriodoRequest request) {
        TipoPeriodo entity = tipoPeriodoMapper.toEntity(request);
        return tipoPeriodoMapper.toResponse(tipoPeriodoRepository.save(entity));
    }

    private TipoPeriodo buscarOFallar(Long id) {
        return tipoPeriodoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoPeriodo no encontrado con id: " + id));
    }
}
