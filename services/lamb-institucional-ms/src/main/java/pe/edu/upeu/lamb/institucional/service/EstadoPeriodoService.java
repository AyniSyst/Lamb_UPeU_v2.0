package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.EstadoPeriodoRequest;
import pe.edu.upeu.lamb.institucional.dto.EstadoPeriodoResponse;
import pe.edu.upeu.lamb.institucional.entity.EstadoPeriodo;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.EstadoPeriodoMapper;
import pe.edu.upeu.lamb.institucional.repository.EstadoPeriodoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EstadoPeriodoService {

    private final EstadoPeriodoRepository estadoPeriodoRepository;
    private final EstadoPeriodoMapper estadoPeriodoMapper;

    public List<EstadoPeriodoResponse> listar() {
        return estadoPeriodoRepository.findAll().stream()
                .map(estadoPeriodoMapper::toResponse)
                .toList();
    }

    public EstadoPeriodoResponse obtener(Long id) {
        return estadoPeriodoMapper.toResponse(buscarOFallar(id));
    }

    public EstadoPeriodoResponse crear(EstadoPeriodoRequest request) {
        EstadoPeriodo entity = estadoPeriodoMapper.toEntity(request);
        return estadoPeriodoMapper.toResponse(estadoPeriodoRepository.save(entity));
    }

    public EstadoPeriodoResponse actualizar(Long id, EstadoPeriodoRequest request) {
        EstadoPeriodo existente = buscarOFallar(id);
        EstadoPeriodo entity = estadoPeriodoMapper.toEntity(request);
        return estadoPeriodoMapper.toResponse(estadoPeriodoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        estadoPeriodoRepository.delete(buscarOFallar(id));
    }

    private EstadoPeriodo buscarOFallar(Long id) {
        return estadoPeriodoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoPeriodo no encontrado con id: " + id));
    }
}
