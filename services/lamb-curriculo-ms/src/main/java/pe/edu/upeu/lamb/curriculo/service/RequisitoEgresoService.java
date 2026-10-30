package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoEgresoRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoEgresoResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoEgreso;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.RequisitoEgresoMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.RequisitoEgresoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoEgresoService {

    private final RequisitoEgresoRepository requisitoEgresoRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final RequisitoEgresoMapper requisitoEgresoMapper;

    @Transactional(readOnly = true)
    public List<RequisitoEgresoResponse> listar(Long idPlan) {
        List<RequisitoEgreso> lista = idPlan == null
                ? requisitoEgresoRepository.findAll()
                : requisitoEgresoRepository.findByPlanId(idPlan);
        return lista.stream().map(requisitoEgresoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RequisitoEgresoResponse obtener(Long id) {
        return requisitoEgresoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RequisitoEgresoResponse crear(RequisitoEgresoRequest request) {
        validar(request, null);
        RequisitoEgreso requisitoEgreso = requisitoEgresoMapper.toEntity(request);
        asignarRelaciones(requisitoEgreso, request);
        return requisitoEgresoMapper.toResponse(requisitoEgresoRepository.save(requisitoEgreso));
    }

    @Transactional
    public RequisitoEgresoResponse actualizar(Long id, RequisitoEgresoRequest request) {
        RequisitoEgreso requisitoEgreso = buscarOFallar(id);
        validar(request, id);
        requisitoEgreso.setCreditosObligatorios(request.getCreditosObligatorios());
        requisitoEgreso.setCreditosElectivos(request.getCreditosElectivos());
        requisitoEgreso.setCreditosTotales(request.getCreditosTotales());
        requisitoEgreso.setPromedioMinimo(request.getPromedioMinimo());
        asignarRelaciones(requisitoEgreso, request);
        return requisitoEgresoMapper.toResponse(requisitoEgresoRepository.save(requisitoEgreso));
    }

    @Transactional
    public void eliminar(Long id) {
        requisitoEgresoRepository.delete(buscarOFallar(id));
    }

    RequisitoEgreso buscarOFallar(Long id) {
        return requisitoEgresoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requisito de egreso no encontrado: " + id));
    }

    private void validar(RequisitoEgresoRequest request, Long id) {
        boolean duplicado = id == null
                ? requisitoEgresoRepository.existsByPlanId(request.getIdPlan())
                : requisitoEgresoRepository.existsByPlanIdAndIdNot(request.getIdPlan(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de requisito de egreso para esa combinacion de: plan");
        }
    }

    private void asignarRelaciones(RequisitoEgreso requisitoEgreso, RequisitoEgresoRequest request) {
        requisitoEgreso.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
