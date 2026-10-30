package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.ResolucionPlanRequest;
import pe.edu.upeu.lamb.curriculo.dto.ResolucionPlanResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.ResolucionPlan;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.ResolucionPlanMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.ResolucionPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolucionPlanService {

    private final ResolucionPlanRepository resolucionPlanRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final ResolucionPlanMapper resolucionPlanMapper;

    @Transactional(readOnly = true)
    public List<ResolucionPlanResponse> listar(Long idPlan) {
        List<ResolucionPlan> lista = idPlan == null
                ? resolucionPlanRepository.findAll()
                : resolucionPlanRepository.findByPlanId(idPlan);
        return lista.stream().map(resolucionPlanMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ResolucionPlanResponse obtener(Long id) {
        return resolucionPlanMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ResolucionPlanResponse crear(ResolucionPlanRequest request) {
        validar(request, null);
        ResolucionPlan resolucionPlan = resolucionPlanMapper.toEntity(request);
        asignarRelaciones(resolucionPlan, request);
        return resolucionPlanMapper.toResponse(resolucionPlanRepository.save(resolucionPlan));
    }

    @Transactional
    public ResolucionPlanResponse actualizar(Long id, ResolucionPlanRequest request) {
        ResolucionPlan resolucionPlan = buscarOFallar(id);
        validar(request, id);
        resolucionPlan.setNumero(request.getNumero().trim());
        resolucionPlan.setFecha(request.getFecha());
        resolucionPlan.setTipo(request.getTipo());
        resolucionPlan.setDescripcion(request.getDescripcion());
        asignarRelaciones(resolucionPlan, request);
        return resolucionPlanMapper.toResponse(resolucionPlanRepository.save(resolucionPlan));
    }

    @Transactional
    public void eliminar(Long id) {
        resolucionPlanRepository.delete(buscarOFallar(id));
    }

    ResolucionPlan buscarOFallar(Long id) {
        return resolucionPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resolucion del plan no encontrada: " + id));
    }

    private void validar(ResolucionPlanRequest request, Long id) {
        boolean duplicado = id == null
                ? resolucionPlanRepository.existsByNumero(request.getNumero().trim())
                : resolucionPlanRepository.existsByNumeroAndIdNot(request.getNumero().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de resolucion del plan para esa combinacion de: numero");
        }
    }

    private void asignarRelaciones(ResolucionPlan resolucionPlan, ResolucionPlanRequest request) {
        resolucionPlan.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
