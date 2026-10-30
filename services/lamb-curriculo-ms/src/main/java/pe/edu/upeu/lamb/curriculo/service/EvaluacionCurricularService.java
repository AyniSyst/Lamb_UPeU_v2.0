package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.EvaluacionCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.EvaluacionCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.ComisionCurricular;
import pe.edu.upeu.lamb.curriculo.entity.EvaluacionCurricular;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.EvaluacionCurricularMapper;
import pe.edu.upeu.lamb.curriculo.repository.ComisionCurricularRepository;
import pe.edu.upeu.lamb.curriculo.repository.EvaluacionCurricularRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluacionCurricularService {

    private final EvaluacionCurricularRepository evaluacionCurricularRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final ComisionCurricularRepository comisionCurricularRepository;
    private final EvaluacionCurricularMapper evaluacionCurricularMapper;

    @Transactional(readOnly = true)
    public List<EvaluacionCurricularResponse> listar(Long idPlan) {
        List<EvaluacionCurricular> lista = idPlan == null
                ? evaluacionCurricularRepository.findAll()
                : evaluacionCurricularRepository.findByPlanId(idPlan);
        return lista.stream().map(evaluacionCurricularMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public EvaluacionCurricularResponse obtener(Long id) {
        return evaluacionCurricularMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EvaluacionCurricularResponse crear(EvaluacionCurricularRequest request) {
        validar(request, null);
        EvaluacionCurricular evaluacionCurricular = evaluacionCurricularMapper.toEntity(request);
        asignarRelaciones(evaluacionCurricular, request);
        return evaluacionCurricularMapper.toResponse(evaluacionCurricularRepository.save(evaluacionCurricular));
    }

    @Transactional
    public EvaluacionCurricularResponse actualizar(Long id, EvaluacionCurricularRequest request) {
        EvaluacionCurricular evaluacionCurricular = buscarOFallar(id);
        validar(request, id);
        evaluacionCurricular.setIdPeriodo(request.getIdPeriodo());
        evaluacionCurricular.setFecha(request.getFecha());
        evaluacionCurricular.setResultado(request.getResultado());
        evaluacionCurricular.setConclusiones(request.getConclusiones());
        asignarRelaciones(evaluacionCurricular, request);
        return evaluacionCurricularMapper.toResponse(evaluacionCurricularRepository.save(evaluacionCurricular));
    }

    @Transactional
    public void eliminar(Long id) {
        evaluacionCurricularRepository.delete(buscarOFallar(id));
    }

    EvaluacionCurricular buscarOFallar(Long id) {
        return evaluacionCurricularRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacion curricular periodica no encontrada: " + id));
    }

    private void validar(EvaluacionCurricularRequest request, Long id) {
    }

    private void asignarRelaciones(EvaluacionCurricular evaluacionCurricular, EvaluacionCurricularRequest request) {
        evaluacionCurricular.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
        evaluacionCurricular.setComision(request.getIdComision() == null ? null : comisionCurricularRepository.findById(request.getIdComision())
                .orElseThrow(() -> new ResourceNotFoundException("Comision curricular no encontrada: " + request.getIdComision())));
    }
}
