package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.TipoPeriodoRequest;
import pe.edu.upeu.lamb.planificacion.dto.TipoPeriodoResponse;
import pe.edu.upeu.lamb.planificacion.entity.TipoPeriodo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.TipoPeriodoMapper;
import pe.edu.upeu.lamb.planificacion.repository.TipoPeriodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoPeriodoService {

    private final TipoPeriodoRepository tipoPeriodoRepository;
    private final TipoPeriodoMapper tipoPeriodoMapper;

    @Transactional(readOnly = true)
    public List<TipoPeriodoResponse> listar() {
        return tipoPeriodoRepository.findAll().stream()
                .map(tipoPeriodoMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TipoPeriodoResponse obtener(Long id) {
        return tipoPeriodoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TipoPeriodoResponse crear(TipoPeriodoRequest request) {
        validar(request, null);
        TipoPeriodo tipoPeriodo = tipoPeriodoMapper.toEntity(request);
        return tipoPeriodoMapper.toResponse(tipoPeriodoRepository.save(tipoPeriodo));
    }

    @Transactional
    public TipoPeriodoResponse actualizar(Long id, TipoPeriodoRequest request) {
        TipoPeriodo tipoPeriodo = buscarOFallar(id);
        validar(request, id);
        tipoPeriodo.setCodigo(request.getCodigo().trim());
        tipoPeriodo.setNombre(request.getNombre().trim());
        tipoPeriodo.setDescripcion(request.getDescripcion());
        return tipoPeriodoMapper.toResponse(tipoPeriodoRepository.save(tipoPeriodo));
    }

    @Transactional
    public void eliminar(Long id) {
        tipoPeriodoRepository.delete(buscarOFallar(id));
    }

    TipoPeriodo buscarOFallar(Long id) {
        return tipoPeriodoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de periodo no encontrado: " + id));
    }

    private void validar(TipoPeriodoRequest request, Long id) {
        boolean duplicado = id == null
                ? tipoPeriodoRepository.existsByCodigo(request.getCodigo().trim())
                : tipoPeriodoRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de tipo de periodo para esa combinacion de: codigo");
        }
    }
}
