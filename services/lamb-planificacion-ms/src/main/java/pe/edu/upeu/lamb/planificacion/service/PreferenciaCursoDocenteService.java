package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.PreferenciaCursoDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.PreferenciaCursoDocenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.Curso;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.entity.PreferenciaCursoDocente;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.PreferenciaCursoDocenteMapper;
import pe.edu.upeu.lamb.planificacion.repository.CursoRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import pe.edu.upeu.lamb.planificacion.repository.PreferenciaCursoDocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreferenciaCursoDocenteService {

    private final PreferenciaCursoDocenteRepository preferenciaCursoDocenteRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final CursoRepository cursoRepository;
    private final PreferenciaCursoDocenteMapper preferenciaCursoDocenteMapper;

    @Transactional(readOnly = true)
    public List<PreferenciaCursoDocenteResponse> listar(Long idPeriodo) {
        List<PreferenciaCursoDocente> lista = idPeriodo == null
                ? preferenciaCursoDocenteRepository.findAll()
                : preferenciaCursoDocenteRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(preferenciaCursoDocenteMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PreferenciaCursoDocenteResponse obtener(Long id) {
        return preferenciaCursoDocenteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public PreferenciaCursoDocenteResponse crear(PreferenciaCursoDocenteRequest request) {
        validar(request, null);
        PreferenciaCursoDocente preferenciaCursoDocente = preferenciaCursoDocenteMapper.toEntity(request);
        asignarRelaciones(preferenciaCursoDocente, request);
        return preferenciaCursoDocenteMapper.toResponse(preferenciaCursoDocenteRepository.save(preferenciaCursoDocente));
    }

    @Transactional
    public PreferenciaCursoDocenteResponse actualizar(Long id, PreferenciaCursoDocenteRequest request) {
        PreferenciaCursoDocente preferenciaCursoDocente = buscarOFallar(id);
        validar(request, id);
        preferenciaCursoDocente.setIdDocente(request.getIdDocente());
        preferenciaCursoDocente.setPrioridad(request.getPrioridad());
        asignarRelaciones(preferenciaCursoDocente, request);
        return preferenciaCursoDocenteMapper.toResponse(preferenciaCursoDocenteRepository.save(preferenciaCursoDocente));
    }

    @Transactional
    public void eliminar(Long id) {
        preferenciaCursoDocenteRepository.delete(buscarOFallar(id));
    }

    PreferenciaCursoDocente buscarOFallar(Long id) {
        return preferenciaCursoDocenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preferencia de cursos del docente no encontrada: " + id));
    }

    private void validar(PreferenciaCursoDocenteRequest request, Long id) {
        boolean duplicado = id == null
                ? preferenciaCursoDocenteRepository.existsByIdDocenteAndPeriodoIdAndCursoId(request.getIdDocente(), request.getIdPeriodo(), request.getIdCurso())
                : preferenciaCursoDocenteRepository.existsByIdDocenteAndPeriodoIdAndCursoIdAndIdNot(request.getIdDocente(), request.getIdPeriodo(), request.getIdCurso(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de preferencia de cursos del docente para esa combinacion de: docente, periodo, curso");
        }
    }

    private void asignarRelaciones(PreferenciaCursoDocente preferenciaCursoDocente, PreferenciaCursoDocenteRequest request) {
        preferenciaCursoDocente.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
        preferenciaCursoDocente.setCurso(cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + request.getIdCurso())));
    }
}
