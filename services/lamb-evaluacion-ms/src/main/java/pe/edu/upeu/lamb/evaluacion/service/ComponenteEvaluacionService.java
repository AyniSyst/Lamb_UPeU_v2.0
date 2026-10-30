package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.ComponenteEvaluacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ComponenteEvaluacionResponse;
import pe.edu.upeu.lamb.evaluacion.entity.ComponenteEvaluacion;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.ComponenteEvaluacionMapper;
import pe.edu.upeu.lamb.evaluacion.repository.ComponenteEvaluacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComponenteEvaluacionService {

    private final ComponenteEvaluacionRepository componenteEvaluacionRepository;
    private final ComponenteEvaluacionMapper componenteEvaluacionMapper;

    @Transactional(readOnly = true)
    public List<ComponenteEvaluacionResponse> listar() {
        return componenteEvaluacionRepository.findAll().stream()
                .map(componenteEvaluacionMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ComponenteEvaluacionResponse obtener(Long id) {
        return componenteEvaluacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ComponenteEvaluacionResponse crear(ComponenteEvaluacionRequest request) {
        validar(request, null);
        ComponenteEvaluacion componenteEvaluacion = componenteEvaluacionMapper.toEntity(request);
        return componenteEvaluacionMapper.toResponse(componenteEvaluacionRepository.save(componenteEvaluacion));
    }

    @Transactional
    public ComponenteEvaluacionResponse actualizar(Long id, ComponenteEvaluacionRequest request) {
        ComponenteEvaluacion componenteEvaluacion = buscarOFallar(id);
        validar(request, id);
        componenteEvaluacion.setCodigo(request.getCodigo().trim());
        componenteEvaluacion.setNombre(request.getNombre().trim());
        componenteEvaluacion.setTipo(request.getTipo());
        componenteEvaluacion.setDescripcion(request.getDescripcion());
        return componenteEvaluacionMapper.toResponse(componenteEvaluacionRepository.save(componenteEvaluacion));
    }

    @Transactional
    public void eliminar(Long id) {
        componenteEvaluacionRepository.delete(buscarOFallar(id));
    }

    ComponenteEvaluacion buscarOFallar(Long id) {
        return componenteEvaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Componente de evaluacion no encontrado: " + id));
    }

    private void validar(ComponenteEvaluacionRequest request, Long id) {
        boolean duplicado = id == null
                ? componenteEvaluacionRepository.existsByCodigo(request.getCodigo().trim())
                : componenteEvaluacionRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de componente de evaluacion para esa combinacion de: codigo");
        }
    }
}
