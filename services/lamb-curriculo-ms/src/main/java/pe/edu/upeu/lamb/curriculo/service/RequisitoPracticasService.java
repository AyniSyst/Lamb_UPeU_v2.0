package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoPracticasRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoPracticasResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoPracticas;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.RequisitoPracticasMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.RequisitoPracticasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoPracticasService {

    private final RequisitoPracticasRepository requisitoPracticasRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final RequisitoPracticasMapper requisitoPracticasMapper;

    @Transactional(readOnly = true)
    public List<RequisitoPracticasResponse> listar(Long idPlan) {
        List<RequisitoPracticas> lista = idPlan == null
                ? requisitoPracticasRepository.findAll()
                : requisitoPracticasRepository.findByPlanId(idPlan);
        return lista.stream().map(requisitoPracticasMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RequisitoPracticasResponse obtener(Long id) {
        return requisitoPracticasMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RequisitoPracticasResponse crear(RequisitoPracticasRequest request) {
        validar(request, null);
        RequisitoPracticas requisitoPracticas = requisitoPracticasMapper.toEntity(request);
        asignarRelaciones(requisitoPracticas, request);
        return requisitoPracticasMapper.toResponse(requisitoPracticasRepository.save(requisitoPracticas));
    }

    @Transactional
    public RequisitoPracticasResponse actualizar(Long id, RequisitoPracticasRequest request) {
        RequisitoPracticas requisitoPracticas = buscarOFallar(id);
        validar(request, id);
        requisitoPracticas.setHorasMinimas(request.getHorasMinimas());
        requisitoPracticas.setCicloDesde(request.getCicloDesde());
        requisitoPracticas.setDescripcion(request.getDescripcion());
        asignarRelaciones(requisitoPracticas, request);
        return requisitoPracticasMapper.toResponse(requisitoPracticasRepository.save(requisitoPracticas));
    }

    @Transactional
    public void eliminar(Long id) {
        requisitoPracticasRepository.delete(buscarOFallar(id));
    }

    RequisitoPracticas buscarOFallar(Long id) {
        return requisitoPracticasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requisito de practicas no encontrado: " + id));
    }

    private void validar(RequisitoPracticasRequest request, Long id) {
        boolean duplicado = id == null
                ? requisitoPracticasRepository.existsByPlanId(request.getIdPlan())
                : requisitoPracticasRepository.existsByPlanIdAndIdNot(request.getIdPlan(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de requisito de practicas para esa combinacion de: plan");
        }
    }

    private void asignarRelaciones(RequisitoPracticas requisitoPracticas, RequisitoPracticasRequest request) {
        requisitoPracticas.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
