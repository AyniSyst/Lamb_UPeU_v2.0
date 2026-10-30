package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.VigenciaCohorteRequest;
import pe.edu.upeu.lamb.curriculo.dto.VigenciaCohorteResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.VigenciaCohorte;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.VigenciaCohorteMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.VigenciaCohorteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VigenciaCohorteService {

    private final VigenciaCohorteRepository vigenciaCohorteRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final VigenciaCohorteMapper vigenciaCohorteMapper;

    @Transactional(readOnly = true)
    public List<VigenciaCohorteResponse> listar(Long idPlan) {
        List<VigenciaCohorte> lista = idPlan == null
                ? vigenciaCohorteRepository.findAll()
                : vigenciaCohorteRepository.findByPlanId(idPlan);
        return lista.stream().map(vigenciaCohorteMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public VigenciaCohorteResponse obtener(Long id) {
        return vigenciaCohorteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public VigenciaCohorteResponse crear(VigenciaCohorteRequest request) {
        validar(request, null);
        VigenciaCohorte vigenciaCohorte = vigenciaCohorteMapper.toEntity(request);
        asignarRelaciones(vigenciaCohorte, request);
        return vigenciaCohorteMapper.toResponse(vigenciaCohorteRepository.save(vigenciaCohorte));
    }

    @Transactional
    public VigenciaCohorteResponse actualizar(Long id, VigenciaCohorteRequest request) {
        VigenciaCohorte vigenciaCohorte = buscarOFallar(id);
        validar(request, id);
        vigenciaCohorte.setCohorteInicio(request.getCohorteInicio().trim());
        vigenciaCohorte.setCohorteFin(request.getCohorteFin());
        vigenciaCohorte.setObservacion(request.getObservacion());
        asignarRelaciones(vigenciaCohorte, request);
        return vigenciaCohorteMapper.toResponse(vigenciaCohorteRepository.save(vigenciaCohorte));
    }

    @Transactional
    public void eliminar(Long id) {
        vigenciaCohorteRepository.delete(buscarOFallar(id));
    }

    VigenciaCohorte buscarOFallar(Long id) {
        return vigenciaCohorteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vigencia por cohorte no encontrada: " + id));
    }

    private void validar(VigenciaCohorteRequest request, Long id) {
        boolean duplicado = id == null
                ? vigenciaCohorteRepository.existsByPlanIdAndCohorteInicio(request.getIdPlan(), request.getCohorteInicio().trim())
                : vigenciaCohorteRepository.existsByPlanIdAndCohorteInicioAndIdNot(request.getIdPlan(), request.getCohorteInicio().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de vigencia por cohorte para esa combinacion de: plan, cohorte inicio");
        }
    }

    private void asignarRelaciones(VigenciaCohorte vigenciaCohorte, VigenciaCohorteRequest request) {
        vigenciaCohorte.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
