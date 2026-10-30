package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.PlanAdecuacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanAdecuacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanAdecuacion;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.PlanAdecuacionMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanAdecuacionRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanAdecuacionService {

    private final PlanAdecuacionRepository planAdecuacionRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final PlanAdecuacionMapper planAdecuacionMapper;

    @Transactional(readOnly = true)
    public List<PlanAdecuacionResponse> listar(Long idPlanOrigen) {
        List<PlanAdecuacion> lista = idPlanOrigen == null
                ? planAdecuacionRepository.findAll()
                : planAdecuacionRepository.findByPlanOrigenId(idPlanOrigen);
        return lista.stream().map(planAdecuacionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PlanAdecuacionResponse obtener(Long id) {
        return planAdecuacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public PlanAdecuacionResponse crear(PlanAdecuacionRequest request) {
        validar(request, null);
        PlanAdecuacion planAdecuacion = planAdecuacionMapper.toEntity(request);
        asignarRelaciones(planAdecuacion, request);
        return planAdecuacionMapper.toResponse(planAdecuacionRepository.save(planAdecuacion));
    }

    @Transactional
    public PlanAdecuacionResponse actualizar(Long id, PlanAdecuacionRequest request) {
        PlanAdecuacion planAdecuacion = buscarOFallar(id);
        validar(request, id);
        planAdecuacion.setNombre(request.getNombre().trim());
        planAdecuacion.setResolucion(request.getResolucion());
        planAdecuacion.setFechaInicio(request.getFechaInicio());
        planAdecuacion.setFechaFin(request.getFechaFin());
        planAdecuacion.setDescripcion(request.getDescripcion());
        asignarRelaciones(planAdecuacion, request);
        return planAdecuacionMapper.toResponse(planAdecuacionRepository.save(planAdecuacion));
    }

    @Transactional
    public void eliminar(Long id) {
        planAdecuacionRepository.delete(buscarOFallar(id));
    }

    PlanAdecuacion buscarOFallar(Long id) {
        return planAdecuacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de adecuacion no encontrado: " + id));
    }

    private void validar(PlanAdecuacionRequest request, Long id) {
        if (request.getIdPlanOrigen().equals(request.getIdPlanDestino())) {
            throw new BusinessRuleException("El plan de adecuacion va de un plan a otro distinto");
        }
        if (request.getFechaFin() != null && request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha fin no puede ser anterior a fecha inicio");
        }
        boolean duplicado = id == null
                ? planAdecuacionRepository.existsByPlanOrigenIdAndPlanDestinoId(request.getIdPlanOrigen(), request.getIdPlanDestino())
                : planAdecuacionRepository.existsByPlanOrigenIdAndPlanDestinoIdAndIdNot(request.getIdPlanOrigen(), request.getIdPlanDestino(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de plan de adecuacion para esa combinacion de: plan origen, plan destino");
        }
    }

    private void asignarRelaciones(PlanAdecuacion planAdecuacion, PlanAdecuacionRequest request) {
        planAdecuacion.setPlanOrigen(planEstudioRepository.findById(request.getIdPlanOrigen())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlanOrigen())));
        planAdecuacion.setPlanDestino(planEstudioRepository.findById(request.getIdPlanDestino())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlanDestino())));
    }
}
