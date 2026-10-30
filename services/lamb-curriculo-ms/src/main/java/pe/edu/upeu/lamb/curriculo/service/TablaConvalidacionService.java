package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.TablaConvalidacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.TablaConvalidacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.TablaConvalidacion;
import pe.edu.upeu.lamb.curriculo.entity.UniversidadOrigen;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.TablaConvalidacionMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.TablaConvalidacionRepository;
import pe.edu.upeu.lamb.curriculo.repository.UniversidadOrigenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TablaConvalidacionService {

    private final TablaConvalidacionRepository tablaConvalidacionRepository;
    private final UniversidadOrigenRepository universidadOrigenRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final TablaConvalidacionMapper tablaConvalidacionMapper;

    @Transactional(readOnly = true)
    public List<TablaConvalidacionResponse> listar(Long idUniversidad) {
        List<TablaConvalidacion> lista = idUniversidad == null
                ? tablaConvalidacionRepository.findAll()
                : tablaConvalidacionRepository.findByUniversidadId(idUniversidad);
        return lista.stream().map(tablaConvalidacionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TablaConvalidacionResponse obtener(Long id) {
        return tablaConvalidacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TablaConvalidacionResponse crear(TablaConvalidacionRequest request) {
        validar(request, null);
        TablaConvalidacion tablaConvalidacion = tablaConvalidacionMapper.toEntity(request);
        asignarRelaciones(tablaConvalidacion, request);
        return tablaConvalidacionMapper.toResponse(tablaConvalidacionRepository.save(tablaConvalidacion));
    }

    @Transactional
    public TablaConvalidacionResponse actualizar(Long id, TablaConvalidacionRequest request) {
        TablaConvalidacion tablaConvalidacion = buscarOFallar(id);
        validar(request, id);
        tablaConvalidacion.setNombre(request.getNombre().trim());
        tablaConvalidacion.setResolucion(request.getResolucion());
        tablaConvalidacion.setVigente(request.getVigente());
        asignarRelaciones(tablaConvalidacion, request);
        return tablaConvalidacionMapper.toResponse(tablaConvalidacionRepository.save(tablaConvalidacion));
    }

    @Transactional
    public void eliminar(Long id) {
        tablaConvalidacionRepository.delete(buscarOFallar(id));
    }

    TablaConvalidacion buscarOFallar(Long id) {
        return tablaConvalidacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tabla de convalidacion no encontrada: " + id));
    }

    private void validar(TablaConvalidacionRequest request, Long id) {
        boolean duplicado = id == null
                ? tablaConvalidacionRepository.existsByUniversidadIdAndPlanId(request.getIdUniversidad(), request.getIdPlan())
                : tablaConvalidacionRepository.existsByUniversidadIdAndPlanIdAndIdNot(request.getIdUniversidad(), request.getIdPlan(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de tabla de convalidacion para esa combinacion de: universidad, plan");
        }
    }

    private void asignarRelaciones(TablaConvalidacion tablaConvalidacion, TablaConvalidacionRequest request) {
        tablaConvalidacion.setUniversidad(universidadOrigenRepository.findById(request.getIdUniversidad())
                .orElseThrow(() -> new ResourceNotFoundException("Universidad de origen no encontrada: " + request.getIdUniversidad())));
        tablaConvalidacion.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
