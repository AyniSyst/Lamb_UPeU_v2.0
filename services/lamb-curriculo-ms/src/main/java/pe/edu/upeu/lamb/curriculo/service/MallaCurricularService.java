package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.MallaCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.MallaCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.MallaCurricular;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.MallaCurricularMapper;
import pe.edu.upeu.lamb.curriculo.repository.MallaCurricularRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MallaCurricularService {

    private final MallaCurricularRepository mallaCurricularRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final MallaCurricularMapper mallaCurricularMapper;

    @Transactional(readOnly = true)
    public List<MallaCurricularResponse> listar(Long idPlan) {
        List<MallaCurricular> lista = idPlan == null
                ? mallaCurricularRepository.findAll()
                : mallaCurricularRepository.findByPlanId(idPlan);
        return lista.stream().map(mallaCurricularMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public MallaCurricularResponse obtener(Long id) {
        return mallaCurricularMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public MallaCurricularResponse crear(MallaCurricularRequest request) {
        validar(request, null);
        MallaCurricular mallaCurricular = mallaCurricularMapper.toEntity(request);
        asignarRelaciones(mallaCurricular, request);
        return mallaCurricularMapper.toResponse(mallaCurricularRepository.save(mallaCurricular));
    }

    @Transactional
    public MallaCurricularResponse actualizar(Long id, MallaCurricularRequest request) {
        MallaCurricular mallaCurricular = buscarOFallar(id);
        validar(request, id);
        mallaCurricular.setNombreArchivo(request.getNombreArchivo().trim());
        mallaCurricular.setUrl(request.getUrl().trim());
        mallaCurricular.setVersion(request.getVersion());
        mallaCurricular.setFechaCarga(request.getFechaCarga());
        asignarRelaciones(mallaCurricular, request);
        return mallaCurricularMapper.toResponse(mallaCurricularRepository.save(mallaCurricular));
    }

    @Transactional
    public void eliminar(Long id) {
        mallaCurricularRepository.delete(buscarOFallar(id));
    }

    MallaCurricular buscarOFallar(Long id) {
        return mallaCurricularRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Malla curricular no encontrada: " + id));
    }

    private void validar(MallaCurricularRequest request, Long id) {
        boolean duplicado = id == null
                ? mallaCurricularRepository.existsByPlanIdAndVersion(request.getIdPlan(), request.getVersion())
                : mallaCurricularRepository.existsByPlanIdAndVersionAndIdNot(request.getIdPlan(), request.getVersion(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de malla curricular para esa combinacion de: plan, version");
        }
    }

    private void asignarRelaciones(MallaCurricular mallaCurricular, MallaCurricularRequest request) {
        mallaCurricular.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
