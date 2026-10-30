package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.CicloPlanRequest;
import pe.edu.upeu.lamb.curriculo.dto.CicloPlanResponse;
import pe.edu.upeu.lamb.curriculo.entity.CicloPlan;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.CicloPlanMapper;
import pe.edu.upeu.lamb.curriculo.repository.CicloPlanRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CicloPlanService {

    private final CicloPlanRepository cicloPlanRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final CicloPlanMapper cicloPlanMapper;

    @Transactional(readOnly = true)
    public List<CicloPlanResponse> listar(Long idPlan) {
        List<CicloPlan> lista = idPlan == null
                ? cicloPlanRepository.findAll()
                : cicloPlanRepository.findByPlanId(idPlan);
        return lista.stream().map(cicloPlanMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CicloPlanResponse obtener(Long id) {
        return cicloPlanMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CicloPlanResponse crear(CicloPlanRequest request) {
        validar(request, null);
        CicloPlan cicloPlan = cicloPlanMapper.toEntity(request);
        asignarRelaciones(cicloPlan, request);
        return cicloPlanMapper.toResponse(cicloPlanRepository.save(cicloPlan));
    }

    @Transactional
    public CicloPlanResponse actualizar(Long id, CicloPlanRequest request) {
        CicloPlan cicloPlan = buscarOFallar(id);
        validar(request, id);
        cicloPlan.setNumero(request.getNumero());
        cicloPlan.setNombre(request.getNombre().trim());
        asignarRelaciones(cicloPlan, request);
        return cicloPlanMapper.toResponse(cicloPlanRepository.save(cicloPlan));
    }

    @Transactional
    public void eliminar(Long id) {
        cicloPlanRepository.delete(buscarOFallar(id));
    }

    CicloPlan buscarOFallar(Long id) {
        return cicloPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ciclo del plan no encontrado: " + id));
    }

    private void validar(CicloPlanRequest request, Long id) {
        boolean duplicado = id == null
                ? cicloPlanRepository.existsByPlanIdAndNumero(request.getIdPlan(), request.getNumero())
                : cicloPlanRepository.existsByPlanIdAndNumeroAndIdNot(request.getIdPlan(), request.getNumero(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de ciclo del plan para esa combinacion de: plan, numero");
        }
    }

    private void asignarRelaciones(CicloPlan cicloPlan, CicloPlanRequest request) {
        cicloPlan.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
