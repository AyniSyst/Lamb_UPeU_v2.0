package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoExtracurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoExtracurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoExtracurricular;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.RequisitoExtracurricularMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.RequisitoExtracurricularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoExtracurricularService {

    private final RequisitoExtracurricularRepository requisitoExtracurricularRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final RequisitoExtracurricularMapper requisitoExtracurricularMapper;

    @Transactional(readOnly = true)
    public List<RequisitoExtracurricularResponse> listar(Long idPlan) {
        List<RequisitoExtracurricular> lista = idPlan == null
                ? requisitoExtracurricularRepository.findAll()
                : requisitoExtracurricularRepository.findByPlanId(idPlan);
        return lista.stream().map(requisitoExtracurricularMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RequisitoExtracurricularResponse obtener(Long id) {
        return requisitoExtracurricularMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RequisitoExtracurricularResponse crear(RequisitoExtracurricularRequest request) {
        validar(request, null);
        RequisitoExtracurricular requisitoExtracurricular = requisitoExtracurricularMapper.toEntity(request);
        asignarRelaciones(requisitoExtracurricular, request);
        return requisitoExtracurricularMapper.toResponse(requisitoExtracurricularRepository.save(requisitoExtracurricular));
    }

    @Transactional
    public RequisitoExtracurricularResponse actualizar(Long id, RequisitoExtracurricularRequest request) {
        RequisitoExtracurricular requisitoExtracurricular = buscarOFallar(id);
        validar(request, id);
        requisitoExtracurricular.setTipo(request.getTipo());
        requisitoExtracurricular.setCreditosExigidos(request.getCreditosExigidos());
        asignarRelaciones(requisitoExtracurricular, request);
        return requisitoExtracurricularMapper.toResponse(requisitoExtracurricularRepository.save(requisitoExtracurricular));
    }

    @Transactional
    public void eliminar(Long id) {
        requisitoExtracurricularRepository.delete(buscarOFallar(id));
    }

    RequisitoExtracurricular buscarOFallar(Long id) {
        return requisitoExtracurricularRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requisito extracurricular no encontrado: " + id));
    }

    private void validar(RequisitoExtracurricularRequest request, Long id) {
        boolean duplicado = id == null
                ? requisitoExtracurricularRepository.existsByPlanIdAndTipo(request.getIdPlan(), request.getTipo())
                : requisitoExtracurricularRepository.existsByPlanIdAndTipoAndIdNot(request.getIdPlan(), request.getTipo(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de requisito extracurricular para esa combinacion de: plan, tipo");
        }
    }

    private void asignarRelaciones(RequisitoExtracurricular requisitoExtracurricular, RequisitoExtracurricularRequest request) {
        requisitoExtracurricular.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
