package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.EjeCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.EjeCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.EjeCurricular;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.EjeCurricularMapper;
import pe.edu.upeu.lamb.curriculo.repository.EjeCurricularRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EjeCurricularService {

    private final EjeCurricularRepository ejeCurricularRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final EjeCurricularMapper ejeCurricularMapper;

    @Transactional(readOnly = true)
    public List<EjeCurricularResponse> listar(Long idPlan) {
        List<EjeCurricular> lista = idPlan == null
                ? ejeCurricularRepository.findAll()
                : ejeCurricularRepository.findByPlanId(idPlan);
        return lista.stream().map(ejeCurricularMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public EjeCurricularResponse obtener(Long id) {
        return ejeCurricularMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EjeCurricularResponse crear(EjeCurricularRequest request) {
        validar(request, null);
        EjeCurricular ejeCurricular = ejeCurricularMapper.toEntity(request);
        asignarRelaciones(ejeCurricular, request);
        return ejeCurricularMapper.toResponse(ejeCurricularRepository.save(ejeCurricular));
    }

    @Transactional
    public EjeCurricularResponse actualizar(Long id, EjeCurricularRequest request) {
        EjeCurricular ejeCurricular = buscarOFallar(id);
        validar(request, id);
        ejeCurricular.setNombre(request.getNombre().trim());
        ejeCurricular.setDescripcion(request.getDescripcion());
        asignarRelaciones(ejeCurricular, request);
        return ejeCurricularMapper.toResponse(ejeCurricularRepository.save(ejeCurricular));
    }

    @Transactional
    public void eliminar(Long id) {
        ejeCurricularRepository.delete(buscarOFallar(id));
    }

    EjeCurricular buscarOFallar(Long id) {
        return ejeCurricularRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eje curricular no encontrado: " + id));
    }

    private void validar(EjeCurricularRequest request, Long id) {
        boolean duplicado = id == null
                ? ejeCurricularRepository.existsByPlanIdAndNombre(request.getIdPlan(), request.getNombre().trim())
                : ejeCurricularRepository.existsByPlanIdAndNombreAndIdNot(request.getIdPlan(), request.getNombre().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de eje curricular para esa combinacion de: plan, nombre");
        }
    }

    private void asignarRelaciones(EjeCurricular ejeCurricular, EjeCurricularRequest request) {
        ejeCurricular.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
