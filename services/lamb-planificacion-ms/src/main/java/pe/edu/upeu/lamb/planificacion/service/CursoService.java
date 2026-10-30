package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.CursoResponse;
import pe.edu.upeu.lamb.planificacion.entity.Curso;
import pe.edu.upeu.lamb.planificacion.event.PlanPublicadoEvento;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.CursoMapper;
import pe.edu.upeu.lamb.planificacion.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public List<CursoResponse> listar() {
        return cursoRepository.findAll().stream()
                .map(cursoMapper::toResponse)
                .toList();
    }

    public CursoResponse obtener(Long id) {
        return cursoMapper.toResponse(buscarOFallar(id));
    }

    /**
     * plan.publicado: crea o actualiza la copia local de cada curso por su codigo.
     * Repetir el mismo evento deja los mismos datos (idempotente por codigo).
     */
    @Transactional
    public void sincronizar(List<PlanPublicadoEvento.CursoPublicado> cursos) {
        if (cursos == null) {
            return;
        }
        for (PlanPublicadoEvento.CursoPublicado publicado : cursos) {
            Curso curso = cursoRepository.findByCodigo(publicado.getCodigo())
                    .orElseGet(() -> Curso.builder().codigo(publicado.getCodigo()).build());
            curso.setNombre(publicado.getNombre());
            curso.setCreditos(publicado.getCreditos());
            cursoRepository.save(curso);
        }
        log.info("component=processor cursos={} status=synchronized", cursos.size());
    }

    Curso buscarOFallar(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + id));
    }
}
