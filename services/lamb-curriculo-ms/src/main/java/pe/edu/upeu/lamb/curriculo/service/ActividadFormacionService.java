package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.ActividadFormacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.ActividadFormacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.ActividadFormacion;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.ActividadFormacionMapper;
import pe.edu.upeu.lamb.curriculo.repository.ActividadFormacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActividadFormacionService {

    private final ActividadFormacionRepository actividadFormacionRepository;
    private final ActividadFormacionMapper actividadFormacionMapper;

    @Transactional(readOnly = true)
    public List<ActividadFormacionResponse> listar() {
        return actividadFormacionRepository.findAll().stream()
                .map(actividadFormacionMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ActividadFormacionResponse obtener(Long id) {
        return actividadFormacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ActividadFormacionResponse crear(ActividadFormacionRequest request) {
        validar(request, null);
        ActividadFormacion actividadFormacion = actividadFormacionMapper.toEntity(request);
        return actividadFormacionMapper.toResponse(actividadFormacionRepository.save(actividadFormacion));
    }

    @Transactional
    public ActividadFormacionResponse actualizar(Long id, ActividadFormacionRequest request) {
        ActividadFormacion actividadFormacion = buscarOFallar(id);
        validar(request, id);
        actividadFormacion.setCodigo(request.getCodigo().trim());
        actividadFormacion.setNombre(request.getNombre().trim());
        actividadFormacion.setTipo(request.getTipo());
        actividadFormacion.setCreditos(request.getCreditos());
        actividadFormacion.setHoras(request.getHoras());
        actividadFormacion.setDescripcion(request.getDescripcion());
        return actividadFormacionMapper.toResponse(actividadFormacionRepository.save(actividadFormacion));
    }

    @Transactional
    public void eliminar(Long id) {
        actividadFormacionRepository.delete(buscarOFallar(id));
    }

    ActividadFormacion buscarOFallar(Long id) {
        return actividadFormacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad de formacion integral no encontrada: " + id));
    }

    private void validar(ActividadFormacionRequest request, Long id) {
        boolean duplicado = id == null
                ? actividadFormacionRepository.existsByCodigo(request.getCodigo().trim())
                : actividadFormacionRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de actividad de formacion integral para esa combinacion de: codigo");
        }
    }
}
