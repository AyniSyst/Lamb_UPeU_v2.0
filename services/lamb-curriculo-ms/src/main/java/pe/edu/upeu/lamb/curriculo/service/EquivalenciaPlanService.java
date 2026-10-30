package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.EquivalenciaPlanRequest;
import pe.edu.upeu.lamb.curriculo.dto.EquivalenciaPlanResponse;
import pe.edu.upeu.lamb.curriculo.entity.EquivalenciaPlan;
import pe.edu.upeu.lamb.curriculo.entity.PlanCurso;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.EquivalenciaPlanMapper;
import pe.edu.upeu.lamb.curriculo.repository.EquivalenciaPlanRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanCursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquivalenciaPlanService {

    private final EquivalenciaPlanRepository equivalenciaPlanRepository;
    private final PlanCursoRepository planCursoRepository;
    private final EquivalenciaPlanMapper equivalenciaPlanMapper;

    @Transactional(readOnly = true)
    public List<EquivalenciaPlanResponse> listar(Long idPlanCursoOrigen) {
        List<EquivalenciaPlan> lista = idPlanCursoOrigen == null
                ? equivalenciaPlanRepository.findAll()
                : equivalenciaPlanRepository.findByPlanCursoOrigenId(idPlanCursoOrigen);
        return lista.stream().map(equivalenciaPlanMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public EquivalenciaPlanResponse obtener(Long id) {
        return equivalenciaPlanMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EquivalenciaPlanResponse crear(EquivalenciaPlanRequest request) {
        validar(request, null);
        EquivalenciaPlan equivalenciaPlan = equivalenciaPlanMapper.toEntity(request);
        asignarRelaciones(equivalenciaPlan, request);
        return equivalenciaPlanMapper.toResponse(equivalenciaPlanRepository.save(equivalenciaPlan));
    }

    @Transactional
    public EquivalenciaPlanResponse actualizar(Long id, EquivalenciaPlanRequest request) {
        EquivalenciaPlan equivalenciaPlan = buscarOFallar(id);
        validar(request, id);
        equivalenciaPlan.setTipo(request.getTipo());
        equivalenciaPlan.setObservacion(request.getObservacion());
        asignarRelaciones(equivalenciaPlan, request);
        return equivalenciaPlanMapper.toResponse(equivalenciaPlanRepository.save(equivalenciaPlan));
    }

    @Transactional
    public void eliminar(Long id) {
        equivalenciaPlanRepository.delete(buscarOFallar(id));
    }

    EquivalenciaPlan buscarOFallar(Long id) {
        return equivalenciaPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equivalencia entre planes no encontrada: " + id));
    }

    private void validar(EquivalenciaPlanRequest request, Long id) {
        boolean duplicado = id == null
                ? equivalenciaPlanRepository.existsByPlanCursoOrigenIdAndPlanCursoDestinoId(request.getIdPlanCursoOrigen(), request.getIdPlanCursoDestino())
                : equivalenciaPlanRepository.existsByPlanCursoOrigenIdAndPlanCursoDestinoIdAndIdNot(request.getIdPlanCursoOrigen(), request.getIdPlanCursoDestino(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de equivalencia entre planes para esa combinacion de: plan curso origen, plan curso destino");
        }
        PlanCurso origen = planCursoRepository.findById(request.getIdPlanCursoOrigen())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdPlanCursoOrigen()));
        PlanCurso destino = planCursoRepository.findById(request.getIdPlanCursoDestino())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdPlanCursoDestino()));
        if (origen.getPlan().getId().equals(destino.getPlan().getId())) {
            throw new BusinessRuleException("La equivalencia es entre planes distintos; ambos cursos son del plan " + origen.getPlan().getId());
        }
    }

    private void asignarRelaciones(EquivalenciaPlan equivalenciaPlan, EquivalenciaPlanRequest request) {
        equivalenciaPlan.setPlanCursoOrigen(planCursoRepository.findById(request.getIdPlanCursoOrigen())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdPlanCursoOrigen())));
        equivalenciaPlan.setPlanCursoDestino(planCursoRepository.findById(request.getIdPlanCursoDestino())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdPlanCursoDestino())));
    }
}
