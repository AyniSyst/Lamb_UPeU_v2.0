package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.SeccionCursoRequest;
import pe.edu.upeu.lms.dto.SeccionCursoResponse;
import pe.edu.upeu.lms.entity.Curso;
import pe.edu.upeu.lms.entity.Docente;
import pe.edu.upeu.lms.entity.PeriodoAcademico;
import pe.edu.upeu.lms.entity.SeccionCurso;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.SeccionCursoMapper;
import pe.edu.upeu.lms.repository.CursoRepository;
import pe.edu.upeu.lms.repository.DocenteRepository;
import pe.edu.upeu.lms.repository.PeriodoAcademicoRepository;
import pe.edu.upeu.lms.repository.SeccionCursoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeccionCursoService {

    private final SeccionCursoRepository seccionCursoRepository;
    private final SeccionCursoMapper seccionCursoMapper;
    private final CursoRepository cursoRepository;
    private final DocenteRepository docenteRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    public List<SeccionCursoResponse> listar() {
        return seccionCursoRepository.findAllConRelaciones().stream()
                .map(seccionCursoMapper::toResponse)
                .toList();
    }

    public SeccionCursoResponse obtener(Long id) {
        return seccionCursoMapper.toResponse(buscarOFallar(id));
    }

    public SeccionCursoResponse crear(SeccionCursoRequest request) {
        SeccionCurso seccionCurso = seccionCursoMapper.toEntity(request);
        seccionCurso.setCurso(buscarCursoOFallar(request.getCursoId()));
        seccionCurso.setDocente(buscarDocenteOFallar(request.getDocenteId()));
        seccionCurso.setPeriodoAcademico(buscarPeriodoOFallar(request.getPeriodoAcademicoId()));
        return seccionCursoMapper.toResponse(seccionCursoRepository.save(seccionCurso));
    }

    public SeccionCursoResponse actualizar(Long id, SeccionCursoRequest request) {
        SeccionCurso seccionCurso = buscarOFallar(id);
        seccionCurso.setNombreSeccion(request.getNombreSeccion());
        seccionCurso.setCupoMaximo(request.getCupoMaximo());
        seccionCurso.setActivo(request.getActivo());
        seccionCurso.setCurso(buscarCursoOFallar(request.getCursoId()));
        seccionCurso.setDocente(buscarDocenteOFallar(request.getDocenteId()));
        seccionCurso.setPeriodoAcademico(buscarPeriodoOFallar(request.getPeriodoAcademicoId()));
        return seccionCursoMapper.toResponse(seccionCursoRepository.save(seccionCurso));
    }

    public void eliminar(Long id) {
        seccionCursoRepository.delete(buscarOFallar(id));
    }

    private SeccionCurso buscarOFallar(Long id) {
        return seccionCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seccion de curso no encontrada: " + id));
    }

    private Curso buscarCursoOFallar(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + cursoId));
    }

    private Docente buscarDocenteOFallar(Long docenteId) {
        return docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado: " + docenteId));
    }

    private PeriodoAcademico buscarPeriodoOFallar(Long periodoId) {
        return periodoAcademicoRepository.findById(periodoId)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + periodoId));
    }
}
