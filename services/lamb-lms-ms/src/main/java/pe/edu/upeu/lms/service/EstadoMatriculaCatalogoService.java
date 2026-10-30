package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.EstadoMatriculaCatalogoRequest;
import pe.edu.upeu.lms.dto.EstadoMatriculaCatalogoResponse;
import pe.edu.upeu.lms.entity.EstadoMatriculaCatalogo;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.EstadoMatriculaCatalogoMapper;
import pe.edu.upeu.lms.repository.EstadoMatriculaCatalogoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoMatriculaCatalogoService {

    private final EstadoMatriculaCatalogoRepository estadoMatriculaCatalogoRepository;
    private final EstadoMatriculaCatalogoMapper estadoMatriculaCatalogoMapper;

    public List<EstadoMatriculaCatalogoResponse> listar() {
        return estadoMatriculaCatalogoRepository.findAll().stream()
                .map(estadoMatriculaCatalogoMapper::toResponse)
                .toList();
    }

    public EstadoMatriculaCatalogoResponse obtener(Long id) {
        return estadoMatriculaCatalogoMapper.toResponse(buscarOFallar(id));
    }

    public EstadoMatriculaCatalogoResponse crear(EstadoMatriculaCatalogoRequest request) {
        EstadoMatriculaCatalogo estadoMatriculaCatalogo = estadoMatriculaCatalogoMapper.toEntity(request);
        return estadoMatriculaCatalogoMapper.toResponse(estadoMatriculaCatalogoRepository.save(estadoMatriculaCatalogo));
    }

    public EstadoMatriculaCatalogoResponse actualizar(Long id, EstadoMatriculaCatalogoRequest request) {
        EstadoMatriculaCatalogo estadoMatriculaCatalogo = buscarOFallar(id);
        estadoMatriculaCatalogo.setCodigo(request.getCodigo());
        estadoMatriculaCatalogo.setNombre(request.getNombre());
        estadoMatriculaCatalogo.setDescripcion(request.getDescripcion());
        estadoMatriculaCatalogo.setActivo(request.getActivo());
        return estadoMatriculaCatalogoMapper.toResponse(estadoMatriculaCatalogoRepository.save(estadoMatriculaCatalogo));
    }

    public void eliminar(Long id) {
        estadoMatriculaCatalogoRepository.delete(buscarOFallar(id));
    }

    private EstadoMatriculaCatalogo buscarOFallar(Long id) {
        return estadoMatriculaCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado de matricula no encontrado: " + id));
    }
}
