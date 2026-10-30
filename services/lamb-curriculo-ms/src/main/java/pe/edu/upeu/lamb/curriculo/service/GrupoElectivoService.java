package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.GrupoElectivoRequest;
import pe.edu.upeu.lamb.curriculo.dto.GrupoElectivoResponse;
import pe.edu.upeu.lamb.curriculo.entity.GrupoElectivo;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.GrupoElectivoMapper;
import pe.edu.upeu.lamb.curriculo.repository.GrupoElectivoRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanCursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GrupoElectivoService {

    private final GrupoElectivoRepository grupoElectivoRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final GrupoElectivoMapper grupoElectivoMapper;
    private final PlanCursoRepository planCursoRepository;

    @Transactional(readOnly = true)
    public List<GrupoElectivoResponse> listar(Long idPlan) {
        List<GrupoElectivo> lista = idPlan == null
                ? grupoElectivoRepository.findAll()
                : grupoElectivoRepository.findByPlanId(idPlan);
        return lista.stream().map(grupoElectivoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public GrupoElectivoResponse obtener(Long id) {
        return grupoElectivoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public GrupoElectivoResponse crear(GrupoElectivoRequest request) {
        validar(request, null);
        GrupoElectivo grupoElectivo = grupoElectivoMapper.toEntity(request);
        asignarRelaciones(grupoElectivo, request);
        return grupoElectivoMapper.toResponse(grupoElectivoRepository.save(grupoElectivo));
    }

    @Transactional
    public GrupoElectivoResponse actualizar(Long id, GrupoElectivoRequest request) {
        GrupoElectivo grupoElectivo = buscarOFallar(id);
        validar(request, id);
        grupoElectivo.setNombre(request.getNombre().trim());
        grupoElectivo.setCantidadElegir(request.getCantidadElegir());
        grupoElectivo.getIdPlanCursos().clear();
        grupoElectivo.getIdPlanCursos().addAll(request.getIdPlanCursos());
        asignarRelaciones(grupoElectivo, request);
        return grupoElectivoMapper.toResponse(grupoElectivoRepository.save(grupoElectivo));
    }

    @Transactional
    public void eliminar(Long id) {
        grupoElectivoRepository.delete(buscarOFallar(id));
    }

    GrupoElectivo buscarOFallar(Long id) {
        return grupoElectivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo de electivos no encontrado: " + id));
    }

    private void validar(GrupoElectivoRequest request, Long id) {
        Set<Long> distintos = new HashSet<>(request.getIdPlanCursos());
        if (distintos.size() != request.getIdPlanCursos().size()) {
            throw new BusinessRuleException("Un curso esta repetido en el grupo de electivos");
        }
        if (request.getCantidadElegir() > distintos.size()) {
            throw new BusinessRuleException("No se puede pedir elegir " + request.getCantidadElegir()
                    + " cursos de un grupo que solo tiene " + distintos.size());
        }
        for (Long idPlanCurso : distintos) {
            planCursoRepository.findByIdAndPlanId(idPlanCurso, request.getIdPlan())
                    .orElseThrow(() -> new BusinessRuleException("El curso " + idPlanCurso + " no pertenece al plan " + request.getIdPlan()));
        }
    }

    private void asignarRelaciones(GrupoElectivo grupoElectivo, GrupoElectivoRequest request) {
        grupoElectivo.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
