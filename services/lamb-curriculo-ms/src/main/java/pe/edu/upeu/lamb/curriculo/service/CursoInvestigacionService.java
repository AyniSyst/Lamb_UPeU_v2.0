package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.CursoInvestigacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.CursoInvestigacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoInvestigacion;
import pe.edu.upeu.lamb.curriculo.entity.LineaInvestigacion;
import pe.edu.upeu.lamb.curriculo.entity.PlanCurso;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.CursoInvestigacionMapper;
import pe.edu.upeu.lamb.curriculo.repository.CursoInvestigacionRepository;
import pe.edu.upeu.lamb.curriculo.repository.LineaInvestigacionRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanCursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoInvestigacionService {

    private final CursoInvestigacionRepository cursoInvestigacionRepository;
    private final LineaInvestigacionRepository lineaInvestigacionRepository;
    private final PlanCursoRepository planCursoRepository;
    private final CursoInvestigacionMapper cursoInvestigacionMapper;

    @Transactional(readOnly = true)
    public List<CursoInvestigacionResponse> listar(Long idLinea) {
        List<CursoInvestigacion> lista = idLinea == null
                ? cursoInvestigacionRepository.findAll()
                : cursoInvestigacionRepository.findByLineaId(idLinea);
        return lista.stream().map(cursoInvestigacionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CursoInvestigacionResponse obtener(Long id) {
        return cursoInvestigacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CursoInvestigacionResponse crear(CursoInvestigacionRequest request) {
        validar(request, null);
        CursoInvestigacion cursoInvestigacion = cursoInvestigacionMapper.toEntity(request);
        asignarRelaciones(cursoInvestigacion, request);
        return cursoInvestigacionMapper.toResponse(cursoInvestigacionRepository.save(cursoInvestigacion));
    }

    @Transactional
    public CursoInvestigacionResponse actualizar(Long id, CursoInvestigacionRequest request) {
        CursoInvestigacion cursoInvestigacion = buscarOFallar(id);
        validar(request, id);
        asignarRelaciones(cursoInvestigacion, request);
        return cursoInvestigacionMapper.toResponse(cursoInvestigacionRepository.save(cursoInvestigacion));
    }

    @Transactional
    public void eliminar(Long id) {
        cursoInvestigacionRepository.delete(buscarOFallar(id));
    }

    CursoInvestigacion buscarOFallar(Long id) {
        return cursoInvestigacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso de investigacion no encontrado: " + id));
    }

    private void validar(CursoInvestigacionRequest request, Long id) {
        boolean duplicado = id == null
                ? cursoInvestigacionRepository.existsByLineaIdAndPlanCursoId(request.getIdLinea(), request.getIdPlanCurso())
                : cursoInvestigacionRepository.existsByLineaIdAndPlanCursoIdAndIdNot(request.getIdLinea(), request.getIdPlanCurso(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de curso de investigacion para esa combinacion de: linea, plan curso");
        }
    }

    private void asignarRelaciones(CursoInvestigacion cursoInvestigacion, CursoInvestigacionRequest request) {
        cursoInvestigacion.setLinea(lineaInvestigacionRepository.findById(request.getIdLinea())
                .orElseThrow(() -> new ResourceNotFoundException("Linea de investigacion no encontrada: " + request.getIdLinea())));
        cursoInvestigacion.setPlanCurso(planCursoRepository.findById(request.getIdPlanCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdPlanCurso())));
    }
}
