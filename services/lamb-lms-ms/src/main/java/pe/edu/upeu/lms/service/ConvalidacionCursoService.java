package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.ConvalidacionCursoRequest;
import pe.edu.upeu.lms.dto.ConvalidacionCursoResponse;
import pe.edu.upeu.lms.entity.ConvalidacionCurso;
import pe.edu.upeu.lms.entity.Curso;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.ConvalidacionCursoMapper;
import pe.edu.upeu.lms.repository.ConvalidacionCursoRepository;
import pe.edu.upeu.lms.repository.CursoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvalidacionCursoService {

    private final ConvalidacionCursoRepository convalidacionCursoRepository;
    private final ConvalidacionCursoMapper convalidacionCursoMapper;
    private final CursoRepository cursoRepository;

    public List<ConvalidacionCursoResponse> listar() {
        return convalidacionCursoRepository.findAllConCurso().stream()
                .map(convalidacionCursoMapper::toResponse)
                .toList();
    }

    public ConvalidacionCursoResponse obtener(Long id) {
        return convalidacionCursoMapper.toResponse(buscarOFallar(id));
    }

    public ConvalidacionCursoResponse crear(ConvalidacionCursoRequest request) {
        ConvalidacionCurso convalidacionCurso = convalidacionCursoMapper.toEntity(request);
        convalidacionCurso.setCurso(buscarCursoOFallar(request.getCursoId()));
        return convalidacionCursoMapper.toResponse(convalidacionCursoRepository.save(convalidacionCurso));
    }

    public ConvalidacionCursoResponse actualizar(Long id, ConvalidacionCursoRequest request) {
        ConvalidacionCurso convalidacionCurso = buscarOFallar(id);
        convalidacionCurso.setIdEstudiante(request.getIdEstudiante());
        convalidacionCurso.setInstitucionOrigen(request.getInstitucionOrigen());
        convalidacionCurso.setCursoOrigen(request.getCursoOrigen());
        convalidacionCurso.setFechaConvalidacion(request.getFechaConvalidacion());
        convalidacionCurso.setCurso(buscarCursoOFallar(request.getCursoId()));
        return convalidacionCursoMapper.toResponse(convalidacionCursoRepository.save(convalidacionCurso));
    }

    public void eliminar(Long id) {
        convalidacionCursoRepository.delete(buscarOFallar(id));
    }

    private ConvalidacionCurso buscarOFallar(Long id) {
        return convalidacionCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convalidacion de curso no encontrada: " + id));
    }

    private Curso buscarCursoOFallar(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + cursoId));
    }
}
