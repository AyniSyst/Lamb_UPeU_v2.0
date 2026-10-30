package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.CursoNivelacionRequest;
import pe.edu.upeu.lamb.planificacion.dto.CursoNivelacionResponse;
import pe.edu.upeu.lamb.planificacion.entity.Curso;
import pe.edu.upeu.lamb.planificacion.entity.CursoNivelacion;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.CursoNivelacionMapper;
import pe.edu.upeu.lamb.planificacion.repository.CursoNivelacionRepository;
import pe.edu.upeu.lamb.planificacion.repository.CursoRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoNivelacionService {

    private final CursoNivelacionRepository cursoNivelacionRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final CursoRepository cursoRepository;
    private final CursoNivelacionMapper cursoNivelacionMapper;

    @Transactional(readOnly = true)
    public List<CursoNivelacionResponse> listar(Long idPeriodo) {
        List<CursoNivelacion> lista = idPeriodo == null
                ? cursoNivelacionRepository.findAll()
                : cursoNivelacionRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(cursoNivelacionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CursoNivelacionResponse obtener(Long id) {
        return cursoNivelacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CursoNivelacionResponse crear(CursoNivelacionRequest request) {
        validar(request, null);
        CursoNivelacion cursoNivelacion = cursoNivelacionMapper.toEntity(request);
        asignarRelaciones(cursoNivelacion, request);
        return cursoNivelacionMapper.toResponse(cursoNivelacionRepository.save(cursoNivelacion));
    }

    @Transactional
    public CursoNivelacionResponse actualizar(Long id, CursoNivelacionRequest request) {
        CursoNivelacion cursoNivelacion = buscarOFallar(id);
        validar(request, id);
        cursoNivelacion.setTipo(request.getTipo());
        cursoNivelacion.setCupoMaximo(request.getCupoMaximo());
        cursoNivelacion.setFechaInicio(request.getFechaInicio());
        cursoNivelacion.setFechaFin(request.getFechaFin());
        asignarRelaciones(cursoNivelacion, request);
        return cursoNivelacionMapper.toResponse(cursoNivelacionRepository.save(cursoNivelacion));
    }

    @Transactional
    public void eliminar(Long id) {
        cursoNivelacionRepository.delete(buscarOFallar(id));
    }

    CursoNivelacion buscarOFallar(Long id) {
        return cursoNivelacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso de nivelacion / verano no encontrado: " + id));
    }

    private void validar(CursoNivelacionRequest request, Long id) {
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha fin no puede ser anterior a fecha inicio");
        }
        boolean duplicado = id == null
                ? cursoNivelacionRepository.existsByPeriodoIdAndCursoIdAndTipo(request.getIdPeriodo(), request.getIdCurso(), request.getTipo())
                : cursoNivelacionRepository.existsByPeriodoIdAndCursoIdAndTipoAndIdNot(request.getIdPeriodo(), request.getIdCurso(), request.getTipo(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de curso de nivelacion / verano para esa combinacion de: periodo, curso, tipo");
        }
    }

    private void asignarRelaciones(CursoNivelacion cursoNivelacion, CursoNivelacionRequest request) {
        cursoNivelacion.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
        cursoNivelacion.setCurso(cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + request.getIdCurso())));
    }
}
