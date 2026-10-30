package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoInvestigacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoInvestigacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoInvestigacion;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.RequisitoInvestigacionMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.RequisitoInvestigacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoInvestigacionService {

    private final RequisitoInvestigacionRepository requisitoInvestigacionRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final RequisitoInvestigacionMapper requisitoInvestigacionMapper;

    @Transactional(readOnly = true)
    public List<RequisitoInvestigacionResponse> listar(Long idPlan) {
        List<RequisitoInvestigacion> lista = idPlan == null
                ? requisitoInvestigacionRepository.findAll()
                : requisitoInvestigacionRepository.findByPlanId(idPlan);
        return lista.stream().map(requisitoInvestigacionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RequisitoInvestigacionResponse obtener(Long id) {
        return requisitoInvestigacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RequisitoInvestigacionResponse crear(RequisitoInvestigacionRequest request) {
        validar(request, null);
        RequisitoInvestigacion requisitoInvestigacion = requisitoInvestigacionMapper.toEntity(request);
        asignarRelaciones(requisitoInvestigacion, request);
        return requisitoInvestigacionMapper.toResponse(requisitoInvestigacionRepository.save(requisitoInvestigacion));
    }

    @Transactional
    public RequisitoInvestigacionResponse actualizar(Long id, RequisitoInvestigacionRequest request) {
        RequisitoInvestigacion requisitoInvestigacion = buscarOFallar(id);
        validar(request, id);
        requisitoInvestigacion.setTipoTrabajo(request.getTipoTrabajo().trim());
        requisitoInvestigacion.setCreditos(request.getCreditos());
        requisitoInvestigacion.setDescripcion(request.getDescripcion());
        asignarRelaciones(requisitoInvestigacion, request);
        return requisitoInvestigacionMapper.toResponse(requisitoInvestigacionRepository.save(requisitoInvestigacion));
    }

    @Transactional
    public void eliminar(Long id) {
        requisitoInvestigacionRepository.delete(buscarOFallar(id));
    }

    RequisitoInvestigacion buscarOFallar(Long id) {
        return requisitoInvestigacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requisito de investigacion no encontrado: " + id));
    }

    private void validar(RequisitoInvestigacionRequest request, Long id) {
        boolean duplicado = id == null
                ? requisitoInvestigacionRepository.existsByPlanId(request.getIdPlan())
                : requisitoInvestigacionRepository.existsByPlanIdAndIdNot(request.getIdPlan(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de requisito de investigacion para esa combinacion de: plan");
        }
    }

    private void asignarRelaciones(RequisitoInvestigacion requisitoInvestigacion, RequisitoInvestigacionRequest request) {
        requisitoInvestigacion.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
