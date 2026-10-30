package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.RecursoComplementarioRequest;
import pe.edu.upeu.lms.dto.RecursoComplementarioResponse;
import pe.edu.upeu.lms.entity.Curso;
import pe.edu.upeu.lms.entity.RecursoComplementario;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.RecursoComplementarioMapper;
import pe.edu.upeu.lms.repository.CursoRepository;
import pe.edu.upeu.lms.repository.RecursoComplementarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecursoComplementarioService {

    private final RecursoComplementarioRepository recursoComplementarioRepository;
    private final RecursoComplementarioMapper recursoComplementarioMapper;
    private final CursoRepository cursoRepository;

    public List<RecursoComplementarioResponse> listar() {
        return recursoComplementarioRepository.findAllConCurso().stream()
                .map(recursoComplementarioMapper::toResponse)
                .toList();
    }

    public RecursoComplementarioResponse obtener(Long id) {
        return recursoComplementarioMapper.toResponse(buscarOFallar(id));
    }

    public RecursoComplementarioResponse crear(RecursoComplementarioRequest request) {
        RecursoComplementario recursoComplementario = recursoComplementarioMapper.toEntity(request);
        recursoComplementario.setCurso(buscarCursoOFallar(request.getCursoId()));
        return recursoComplementarioMapper.toResponse(recursoComplementarioRepository.save(recursoComplementario));
    }

    public RecursoComplementarioResponse actualizar(Long id, RecursoComplementarioRequest request) {
        RecursoComplementario recursoComplementario = buscarOFallar(id);
        recursoComplementario.setNombre(request.getNombre());
        recursoComplementario.setTipo(request.getTipo());
        recursoComplementario.setUrl(request.getUrl());
        recursoComplementario.setActivo(request.getActivo());
        recursoComplementario.setCurso(buscarCursoOFallar(request.getCursoId()));
        return recursoComplementarioMapper.toResponse(recursoComplementarioRepository.save(recursoComplementario));
    }

    public void eliminar(Long id) {
        recursoComplementarioRepository.delete(buscarOFallar(id));
    }

    private RecursoComplementario buscarOFallar(Long id) {
        return recursoComplementarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso complementario no encontrado: " + id));
    }

    private Curso buscarCursoOFallar(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + cursoId));
    }
}
