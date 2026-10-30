package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.PerfilIngresoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PerfilIngresoResponse;
import pe.edu.upeu.lamb.curriculo.entity.PerfilIngreso;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.PerfilIngresoMapper;
import pe.edu.upeu.lamb.curriculo.repository.PerfilIngresoRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilIngresoService {

    private final PerfilIngresoRepository perfilIngresoRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final PerfilIngresoMapper perfilIngresoMapper;

    @Transactional(readOnly = true)
    public List<PerfilIngresoResponse> listar(Long idPlan) {
        List<PerfilIngreso> lista = idPlan == null
                ? perfilIngresoRepository.findAll()
                : perfilIngresoRepository.findByPlanId(idPlan);
        return lista.stream().map(perfilIngresoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PerfilIngresoResponse obtener(Long id) {
        return perfilIngresoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public PerfilIngresoResponse crear(PerfilIngresoRequest request) {
        validar(request, null);
        PerfilIngreso perfilIngreso = perfilIngresoMapper.toEntity(request);
        asignarRelaciones(perfilIngreso, request);
        return perfilIngresoMapper.toResponse(perfilIngresoRepository.save(perfilIngreso));
    }

    @Transactional
    public PerfilIngresoResponse actualizar(Long id, PerfilIngresoRequest request) {
        PerfilIngreso perfilIngreso = buscarOFallar(id);
        validar(request, id);
        perfilIngreso.setDescripcion(request.getDescripcion().trim());
        perfilIngreso.setConocimientos(request.getConocimientos());
        perfilIngreso.setHabilidades(request.getHabilidades());
        perfilIngreso.setActitudes(request.getActitudes());
        asignarRelaciones(perfilIngreso, request);
        return perfilIngresoMapper.toResponse(perfilIngresoRepository.save(perfilIngreso));
    }

    @Transactional
    public void eliminar(Long id) {
        perfilIngresoRepository.delete(buscarOFallar(id));
    }

    PerfilIngreso buscarOFallar(Long id) {
        return perfilIngresoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil de ingreso no encontrado: " + id));
    }

    private void validar(PerfilIngresoRequest request, Long id) {
        boolean duplicado = id == null
                ? perfilIngresoRepository.existsByPlanId(request.getIdPlan())
                : perfilIngresoRepository.existsByPlanIdAndIdNot(request.getIdPlan(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de perfil de ingreso para esa combinacion de: plan");
        }
    }

    private void asignarRelaciones(PerfilIngreso perfilIngreso, PerfilIngresoRequest request) {
        perfilIngreso.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
