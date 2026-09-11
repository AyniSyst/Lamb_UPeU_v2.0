package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.SeccionRequest;
import pe.edu.upeu.lamb.planificacion.dto.SeccionResponse;
import pe.edu.upeu.lamb.planificacion.entity.Seccion;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SeccionMapper;
import pe.edu.upeu.lamb.planificacion.repository.SeccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeccionService {

    private final SeccionRepository seccionRepository;
    private final SeccionMapper seccionMapper;
    private final CursoService cursoService;

    public List<SeccionResponse> listar() {
        return seccionRepository.findAllConCurso().stream()
                .map(seccionMapper::toResponse)
                .toList();
    }

    public SeccionResponse obtener(Long id) {
        return seccionMapper.toResponse(buscarOFallar(id));
    }

    public SeccionResponse crear(SeccionRequest request) {
        Seccion seccion = seccionMapper.toEntity(request);
        seccion.setCurso(cursoService.buscarOFallar(request.getCursoId()));
        return seccionMapper.toResponse(seccionRepository.save(seccion));
    }

    public SeccionResponse actualizar(Long id, SeccionRequest request) {
        Seccion seccion = buscarOFallar(id);
        seccion.setPeriodo(request.getPeriodo());
        seccion.setDocente(request.getDocente());
        seccion.setCupoMaximo(request.getCupoMaximo());
        seccion.setHorario(request.getHorario());
        seccion.setCurso(cursoService.buscarOFallar(request.getCursoId()));
        return seccionMapper.toResponse(seccionRepository.save(seccion));
    }

    public void eliminar(Long id) {
        seccionRepository.delete(buscarOFallar(id));
    }

    Seccion buscarOFallar(Long id) {
        return seccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seccion no encontrada: " + id));
    }
}
