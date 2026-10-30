package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.PreguntaEncuestaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.PreguntaEncuestaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.EncuestaDocente;
import pe.edu.upeu.lamb.evaluacion.entity.PreguntaEncuesta;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.PreguntaEncuestaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.EncuestaDocenteRepository;
import pe.edu.upeu.lamb.evaluacion.repository.PreguntaEncuestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreguntaEncuestaService {

    private final PreguntaEncuestaRepository preguntaEncuestaRepository;
    private final EncuestaDocenteRepository encuestaDocenteRepository;
    private final PreguntaEncuestaMapper preguntaEncuestaMapper;

    @Transactional(readOnly = true)
    public List<PreguntaEncuestaResponse> listar(Long idEncuesta) {
        List<PreguntaEncuesta> lista = idEncuesta == null
                ? preguntaEncuestaRepository.findAll()
                : preguntaEncuestaRepository.findByEncuestaId(idEncuesta);
        return lista.stream().map(preguntaEncuestaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PreguntaEncuestaResponse obtener(Long id) {
        return preguntaEncuestaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public PreguntaEncuestaResponse crear(PreguntaEncuestaRequest request) {
        validar(request, null);
        PreguntaEncuesta preguntaEncuesta = preguntaEncuestaMapper.toEntity(request);
        asignarRelaciones(preguntaEncuesta, request);
        return preguntaEncuestaMapper.toResponse(preguntaEncuestaRepository.save(preguntaEncuesta));
    }

    @Transactional
    public PreguntaEncuestaResponse actualizar(Long id, PreguntaEncuestaRequest request) {
        PreguntaEncuesta preguntaEncuesta = buscarOFallar(id);
        validar(request, id);
        preguntaEncuesta.setOrden(request.getOrden());
        preguntaEncuesta.setEnunciado(request.getEnunciado().trim());
        preguntaEncuesta.setTipo(request.getTipo());
        asignarRelaciones(preguntaEncuesta, request);
        return preguntaEncuestaMapper.toResponse(preguntaEncuestaRepository.save(preguntaEncuesta));
    }

    @Transactional
    public void eliminar(Long id) {
        preguntaEncuestaRepository.delete(buscarOFallar(id));
    }

    PreguntaEncuesta buscarOFallar(Long id) {
        return preguntaEncuestaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta de la evaluacion docente no encontrada: " + id));
    }

    private void validar(PreguntaEncuestaRequest request, Long id) {
        boolean duplicado = id == null
                ? preguntaEncuestaRepository.existsByEncuestaIdAndOrden(request.getIdEncuesta(), request.getOrden())
                : preguntaEncuestaRepository.existsByEncuestaIdAndOrdenAndIdNot(request.getIdEncuesta(), request.getOrden(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de pregunta de la evaluacion docente para esa combinacion de: encuesta, orden");
        }
    }

    private void asignarRelaciones(PreguntaEncuesta preguntaEncuesta, PreguntaEncuestaRequest request) {
        preguntaEncuesta.setEncuesta(encuestaDocenteRepository.findById(request.getIdEncuesta())
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta docente no encontrada: " + request.getIdEncuesta())));
    }
}
