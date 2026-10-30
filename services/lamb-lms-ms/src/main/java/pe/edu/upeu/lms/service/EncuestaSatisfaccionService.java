package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.EncuestaSatisfaccionRequest;
import pe.edu.upeu.lms.dto.EncuestaSatisfaccionResponse;
import pe.edu.upeu.lms.entity.Curso;
import pe.edu.upeu.lms.entity.EncuestaSatisfaccion;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.EncuestaSatisfaccionMapper;
import pe.edu.upeu.lms.repository.CursoRepository;
import pe.edu.upeu.lms.repository.EncuestaSatisfaccionRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EncuestaSatisfaccionService {

    private final EncuestaSatisfaccionRepository encuestaSatisfaccionRepository;
    private final EncuestaSatisfaccionMapper encuestaSatisfaccionMapper;
    private final CursoRepository cursoRepository;

    public List<EncuestaSatisfaccionResponse> listar() {
        return encuestaSatisfaccionRepository.findAllConCurso().stream()
                .map(encuestaSatisfaccionMapper::toResponse)
                .toList();
    }

    public EncuestaSatisfaccionResponse obtener(Long id) {
        return encuestaSatisfaccionMapper.toResponse(buscarOFallar(id));
    }

    public EncuestaSatisfaccionResponse crear(EncuestaSatisfaccionRequest request) {
        EncuestaSatisfaccion encuestaSatisfaccion = encuestaSatisfaccionMapper.toEntity(request);
        encuestaSatisfaccion.setCurso(buscarCursoOFallar(request.getCursoId()));
        encuestaSatisfaccion.setFecha(LocalDateTime.now());
        return encuestaSatisfaccionMapper.toResponse(encuestaSatisfaccionRepository.save(encuestaSatisfaccion));
    }

    public EncuestaSatisfaccionResponse actualizar(Long id, EncuestaSatisfaccionRequest request) {
        EncuestaSatisfaccion encuestaSatisfaccion = buscarOFallar(id);
        encuestaSatisfaccion.setIdEstudiante(request.getIdEstudiante());
        encuestaSatisfaccion.setCalificacion(request.getCalificacion());
        encuestaSatisfaccion.setComentario(request.getComentario());
        encuestaSatisfaccion.setCurso(buscarCursoOFallar(request.getCursoId()));
        return encuestaSatisfaccionMapper.toResponse(encuestaSatisfaccionRepository.save(encuestaSatisfaccion));
    }

    public void eliminar(Long id) {
        encuestaSatisfaccionRepository.delete(buscarOFallar(id));
    }

    private EncuestaSatisfaccion buscarOFallar(Long id) {
        return encuestaSatisfaccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta de satisfaccion no encontrada: " + id));
    }

    private Curso buscarCursoOFallar(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + cursoId));
    }
}
