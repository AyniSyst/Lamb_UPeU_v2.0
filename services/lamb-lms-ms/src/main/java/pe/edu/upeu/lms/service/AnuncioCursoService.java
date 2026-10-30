package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.AnuncioCursoRequest;
import pe.edu.upeu.lms.dto.AnuncioCursoResponse;
import pe.edu.upeu.lms.entity.AnuncioCurso;
import pe.edu.upeu.lms.entity.Curso;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.AnuncioCursoMapper;
import pe.edu.upeu.lms.repository.AnuncioCursoRepository;
import pe.edu.upeu.lms.repository.CursoRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnuncioCursoService {

    private final AnuncioCursoRepository anuncioCursoRepository;
    private final AnuncioCursoMapper anuncioCursoMapper;
    private final CursoRepository cursoRepository;

    public List<AnuncioCursoResponse> listar() {
        return anuncioCursoRepository.findAllConCurso().stream()
                .map(anuncioCursoMapper::toResponse)
                .toList();
    }

    public AnuncioCursoResponse obtener(Long id) {
        return anuncioCursoMapper.toResponse(buscarOFallar(id));
    }

    public AnuncioCursoResponse crear(AnuncioCursoRequest request) {
        AnuncioCurso anuncioCurso = anuncioCursoMapper.toEntity(request);
        anuncioCurso.setCurso(buscarCursoOFallar(request.getCursoId()));
        anuncioCurso.setFechaPublicacion(LocalDateTime.now());
        return anuncioCursoMapper.toResponse(anuncioCursoRepository.save(anuncioCurso));
    }

    public AnuncioCursoResponse actualizar(Long id, AnuncioCursoRequest request) {
        AnuncioCurso anuncioCurso = buscarOFallar(id);
        anuncioCurso.setTitulo(request.getTitulo());
        anuncioCurso.setMensaje(request.getMensaje());
        anuncioCurso.setCurso(buscarCursoOFallar(request.getCursoId()));
        return anuncioCursoMapper.toResponse(anuncioCursoRepository.save(anuncioCurso));
    }

    public void eliminar(Long id) {
        anuncioCursoRepository.delete(buscarOFallar(id));
    }

    private AnuncioCurso buscarOFallar(Long id) {
        return anuncioCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio de curso no encontrado: " + id));
    }

    private Curso buscarCursoOFallar(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + cursoId));
    }
}
