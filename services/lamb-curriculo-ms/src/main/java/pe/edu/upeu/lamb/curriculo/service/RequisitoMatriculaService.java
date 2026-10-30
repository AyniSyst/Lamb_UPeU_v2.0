package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoMatriculaRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoMatriculaResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanCurso;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoMatricula;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.RequisitoMatriculaMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanCursoRepository;
import pe.edu.upeu.lamb.curriculo.repository.RequisitoMatriculaRepository;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoMatriculaService {

    private final RequisitoMatriculaRepository requisitoMatriculaRepository;
    private final PlanCursoRepository planCursoRepository;
    private final RequisitoMatriculaMapper requisitoMatriculaMapper;

    @Transactional(readOnly = true)
    public List<RequisitoMatriculaResponse> listar(Long idPlanCurso) {
        List<RequisitoMatricula> lista = idPlanCurso == null
                ? requisitoMatriculaRepository.findAll()
                : requisitoMatriculaRepository.findByPlanCursoId(idPlanCurso);
        return lista.stream().map(requisitoMatriculaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RequisitoMatriculaResponse obtener(Long id) {
        return requisitoMatriculaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RequisitoMatriculaResponse crear(RequisitoMatriculaRequest request) {
        validar(request, null);
        RequisitoMatricula requisitoMatricula = requisitoMatriculaMapper.toEntity(request);
        asignarRelaciones(requisitoMatricula, request);
        return requisitoMatriculaMapper.toResponse(requisitoMatriculaRepository.save(requisitoMatricula));
    }

    @Transactional
    public RequisitoMatriculaResponse actualizar(Long id, RequisitoMatriculaRequest request) {
        RequisitoMatricula requisitoMatricula = buscarOFallar(id);
        validar(request, id);
        requisitoMatricula.setTipo(request.getTipo());
        requisitoMatricula.setCreditosMinimos(request.getCreditosMinimos());
        requisitoMatricula.setCicloMinimo(request.getCicloMinimo());
        requisitoMatricula.setDescripcion(request.getDescripcion());
        asignarRelaciones(requisitoMatricula, request);
        return requisitoMatriculaMapper.toResponse(requisitoMatriculaRepository.save(requisitoMatricula));
    }

    @Transactional
    public void eliminar(Long id) {
        requisitoMatriculaRepository.delete(buscarOFallar(id));
    }

    RequisitoMatricula buscarOFallar(Long id) {
        return requisitoMatriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requisito de matricula no encontrado: " + id));
    }

    private void validar(RequisitoMatriculaRequest request, Long id) {
        switch (request.getTipo()) {
            case CURSO_APROBADO -> {
                if (request.getIdCursoRequisito() == null) {
                    throw new BusinessRuleException("Un requisito CURSO_APROBADO necesita el curso requisito");
                }
                if (request.getIdCursoRequisito().equals(request.getIdPlanCurso())) {
                    throw new BusinessRuleException("Un curso no puede ser requisito de si mismo");
                }
                PlanCurso curso = planCursoRepository.findById(request.getIdPlanCurso())
                        .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdPlanCurso()));
                PlanCurso requisito = planCursoRepository.findById(request.getIdCursoRequisito())
                        .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdCursoRequisito()));
                if (!curso.getPlan().getId().equals(requisito.getPlan().getId())) {
                    throw new BusinessRuleException("El curso requisito debe ser del mismo plan de estudios");
                }
            }
            case CREDITOS_APROBADOS -> {
                if (request.getCreditosMinimos() == null) {
                    throw new BusinessRuleException("Un requisito CREDITOS_APROBADOS necesita los creditos minimos");
                }
            }
            case CICLO_MINIMO -> {
                if (request.getCicloMinimo() == null) {
                    throw new BusinessRuleException("Un requisito CICLO_MINIMO necesita el ciclo minimo");
                }
            }
        }
    }

    private void asignarRelaciones(RequisitoMatricula requisitoMatricula, RequisitoMatriculaRequest request) {
        requisitoMatricula.setPlanCurso(planCursoRepository.findById(request.getIdPlanCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdPlanCurso())));
        requisitoMatricula.setCursoRequisito(request.getIdCursoRequisito() == null ? null : planCursoRepository.findById(request.getIdCursoRequisito())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdCursoRequisito())));
    }
}
