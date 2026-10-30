package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.PrerequisitoCursoRequest;
import pe.edu.upeu.lms.dto.PrerequisitoCursoResponse;
import pe.edu.upeu.lms.entity.Curso;
import pe.edu.upeu.lms.entity.PrerequisitoCurso;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.PrerequisitoCursoMapper;
import pe.edu.upeu.lms.repository.CursoRepository;
import pe.edu.upeu.lms.repository.PrerequisitoCursoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrerequisitoCursoService {

    private final PrerequisitoCursoRepository prerequisitoCursoRepository;
    private final PrerequisitoCursoMapper prerequisitoCursoMapper;
    private final CursoRepository cursoRepository;

    public List<PrerequisitoCursoResponse> listar() {
        return prerequisitoCursoRepository.findAllConCursos().stream()
                .map(prerequisitoCursoMapper::toResponse)
                .toList();
    }

    public PrerequisitoCursoResponse obtener(Long id) {
        return prerequisitoCursoMapper.toResponse(buscarOFallar(id));
    }

    public PrerequisitoCursoResponse crear(PrerequisitoCursoRequest request) {
        PrerequisitoCurso prerequisitoCurso = prerequisitoCursoMapper.toEntity(request);
        prerequisitoCurso.setCurso(buscarCursoOFallar(request.getCursoId()));
        prerequisitoCurso.setCursoPrerequisito(buscarCursoOFallar(request.getCursoPrerequisitoId()));
        return prerequisitoCursoMapper.toResponse(prerequisitoCursoRepository.save(prerequisitoCurso));
    }

    public PrerequisitoCursoResponse actualizar(Long id, PrerequisitoCursoRequest request) {
        PrerequisitoCurso prerequisitoCurso = buscarOFallar(id);
        prerequisitoCurso.setCurso(buscarCursoOFallar(request.getCursoId()));
        prerequisitoCurso.setCursoPrerequisito(buscarCursoOFallar(request.getCursoPrerequisitoId()));
        return prerequisitoCursoMapper.toResponse(prerequisitoCursoRepository.save(prerequisitoCurso));
    }

    public void eliminar(Long id) {
        prerequisitoCursoRepository.delete(buscarOFallar(id));
    }

    private PrerequisitoCurso buscarOFallar(Long id) {
        return prerequisitoCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prerequisito de curso no encontrado: " + id));
    }

    private Curso buscarCursoOFallar(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + cursoId));
    }
}
