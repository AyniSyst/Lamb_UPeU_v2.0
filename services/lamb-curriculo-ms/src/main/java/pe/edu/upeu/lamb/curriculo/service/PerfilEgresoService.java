package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.PerfilEgresoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PerfilEgresoResponse;
import pe.edu.upeu.lamb.curriculo.entity.PerfilEgreso;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.PerfilEgresoMapper;
import pe.edu.upeu.lamb.curriculo.repository.PerfilEgresoRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilEgresoService {

    private final PerfilEgresoRepository perfilEgresoRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final PerfilEgresoMapper perfilEgresoMapper;

    @Transactional(readOnly = true)
    public List<PerfilEgresoResponse> listar(Long idPlan) {
        List<PerfilEgreso> lista = idPlan == null
                ? perfilEgresoRepository.findAll()
                : perfilEgresoRepository.findByPlanId(idPlan);
        return lista.stream().map(perfilEgresoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PerfilEgresoResponse obtener(Long id) {
        return perfilEgresoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public PerfilEgresoResponse crear(PerfilEgresoRequest request) {
        validar(request, null);
        PerfilEgreso perfilEgreso = perfilEgresoMapper.toEntity(request);
        asignarRelaciones(perfilEgreso, request);
        return perfilEgresoMapper.toResponse(perfilEgresoRepository.save(perfilEgreso));
    }

    @Transactional
    public PerfilEgresoResponse actualizar(Long id, PerfilEgresoRequest request) {
        PerfilEgreso perfilEgreso = buscarOFallar(id);
        validar(request, id);
        perfilEgreso.setDescripcion(request.getDescripcion().trim());
        asignarRelaciones(perfilEgreso, request);
        return perfilEgresoMapper.toResponse(perfilEgresoRepository.save(perfilEgreso));
    }

    @Transactional
    public void eliminar(Long id) {
        perfilEgresoRepository.delete(buscarOFallar(id));
    }

    PerfilEgreso buscarOFallar(Long id) {
        return perfilEgresoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil de egreso no encontrado: " + id));
    }

    private void validar(PerfilEgresoRequest request, Long id) {
        boolean duplicado = id == null
                ? perfilEgresoRepository.existsByPlanId(request.getIdPlan())
                : perfilEgresoRepository.existsByPlanIdAndIdNot(request.getIdPlan(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de perfil de egreso para esa combinacion de: plan");
        }
    }

    private void asignarRelaciones(PerfilEgreso perfilEgreso, PerfilEgresoRequest request) {
        perfilEgreso.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
