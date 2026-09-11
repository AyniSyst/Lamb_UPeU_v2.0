package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.NotaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.NotaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.Nota;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.NotaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;

    public List<NotaResponse> listar() {
        return notaRepository.findAll().stream()
                .map(notaMapper::toResponse)
                .toList();
    }

    public NotaResponse obtener(Long id) {
        return notaMapper.toResponse(buscarOFallar(id));
    }

    public NotaResponse crear(NotaRequest request) {
        Nota nota = notaMapper.toEntity(request);
        return notaMapper.toResponse(notaRepository.save(nota));
    }

    public NotaResponse actualizar(Long id, NotaRequest request) {
        Nota nota = buscarOFallar(id);
        nota.setIdPersona(request.getIdPersona());
        nota.setIdSeccion(request.getIdSeccion());
        nota.setTipoEvaluacion(request.getTipoEvaluacion());
        nota.setCalificacion(request.getCalificacion());
        nota.setPeriodo(request.getPeriodo());
        return notaMapper.toResponse(notaRepository.save(nota));
    }

    public void eliminar(Long id) {
        notaRepository.delete(buscarOFallar(id));
    }

    private Nota buscarOFallar(Long id) {
        return notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada: " + id));
    }
}
