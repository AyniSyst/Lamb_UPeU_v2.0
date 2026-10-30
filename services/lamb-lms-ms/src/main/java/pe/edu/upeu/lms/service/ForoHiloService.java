package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.ForoHiloRequest;
import pe.edu.upeu.lms.dto.ForoHiloResponse;
import pe.edu.upeu.lms.entity.Curso;
import pe.edu.upeu.lms.entity.ForoHilo;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.ForoHiloMapper;
import pe.edu.upeu.lms.repository.CursoRepository;
import pe.edu.upeu.lms.repository.ForoHiloRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ForoHiloService {

    private final ForoHiloRepository foroHiloRepository;
    private final ForoHiloMapper foroHiloMapper;
    private final CursoRepository cursoRepository;

    public List<ForoHiloResponse> listar() {
        return foroHiloRepository.findAllConCurso().stream()
                .map(foroHiloMapper::toResponse)
                .toList();
    }

    public ForoHiloResponse obtener(Long id) {
        return foroHiloMapper.toResponse(buscarOFallar(id));
    }

    public ForoHiloResponse crear(ForoHiloRequest request) {
        ForoHilo foroHilo = foroHiloMapper.toEntity(request);
        foroHilo.setCurso(buscarCursoOFallar(request.getCursoId()));
        foroHilo.setFechaCreacion(LocalDateTime.now());
        return foroHiloMapper.toResponse(foroHiloRepository.save(foroHilo));
    }

    public ForoHiloResponse actualizar(Long id, ForoHiloRequest request) {
        ForoHilo foroHilo = buscarOFallar(id);
        foroHilo.setTitulo(request.getTitulo());
        foroHilo.setIdEstudianteCreador(request.getIdEstudianteCreador());
        foroHilo.setCurso(buscarCursoOFallar(request.getCursoId()));
        return foroHiloMapper.toResponse(foroHiloRepository.save(foroHilo));
    }

    public void eliminar(Long id) {
        foroHiloRepository.delete(buscarOFallar(id));
    }

    private ForoHilo buscarOFallar(Long id) {
        return foroHiloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Foro no encontrado: " + id));
    }

    private Curso buscarCursoOFallar(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + cursoId));
    }
}
