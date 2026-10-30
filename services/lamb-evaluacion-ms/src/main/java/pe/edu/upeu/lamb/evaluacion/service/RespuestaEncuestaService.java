package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.RespuestaEncuestaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaEncuestaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.PreguntaEncuesta;
import pe.edu.upeu.lamb.evaluacion.entity.RespuestaEncuesta;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.RespuestaEncuestaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.PreguntaEncuestaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.RespuestaEncuestaRepository;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoEncuesta;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespuestaEncuestaService {

    private final RespuestaEncuestaRepository respuestaEncuestaRepository;
    private final PreguntaEncuestaRepository preguntaEncuestaRepository;
    private final RespuestaEncuestaMapper respuestaEncuestaMapper;

    @Transactional(readOnly = true)
    public List<RespuestaEncuestaResponse> listar(Long idPregunta) {
        List<RespuestaEncuesta> lista = idPregunta == null
                ? respuestaEncuestaRepository.findAll()
                : respuestaEncuestaRepository.findByPreguntaId(idPregunta);
        return lista.stream().map(respuestaEncuestaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RespuestaEncuestaResponse obtener(Long id) {
        return respuestaEncuestaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RespuestaEncuestaResponse crear(RespuestaEncuestaRequest request) {
        validar(request, null);
        RespuestaEncuesta respuestaEncuesta = respuestaEncuestaMapper.toEntity(request);
        asignarRelaciones(respuestaEncuesta, request);
        return respuestaEncuestaMapper.toResponse(respuestaEncuestaRepository.save(respuestaEncuesta));
    }

    @Transactional
    public RespuestaEncuestaResponse actualizar(Long id, RespuestaEncuestaRequest request) {
        RespuestaEncuesta respuestaEncuesta = buscarOFallar(id);
        validar(request, id);
        respuestaEncuesta.setIdGrupo(request.getIdGrupo());
        respuestaEncuesta.setIdDocente(request.getIdDocente());
        respuestaEncuesta.setValorEscala(request.getValorEscala());
        respuestaEncuesta.setValorTexto(request.getValorTexto());
        asignarRelaciones(respuestaEncuesta, request);
        return respuestaEncuestaMapper.toResponse(respuestaEncuestaRepository.save(respuestaEncuesta));
    }

    @Transactional
    public void eliminar(Long id) {
        respuestaEncuestaRepository.delete(buscarOFallar(id));
    }

    RespuestaEncuesta buscarOFallar(Long id) {
        return respuestaEncuestaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta de la evaluacion docente no encontrada: " + id));
    }

    private void validar(RespuestaEncuestaRequest request, Long id) {
    }

    private void asignarRelaciones(RespuestaEncuesta respuestaEncuesta, RespuestaEncuestaRequest request) {
        respuestaEncuesta.setPregunta(preguntaEncuestaRepository.findById(request.getIdPregunta())
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta de la evaluacion docente no encontrada: " + request.getIdPregunta())));
        PreguntaEncuesta pregunta = preguntaEncuestaRepository.findById(request.getIdPregunta())
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta de la evaluacion docente no encontrada: " + request.getIdPregunta()));
        if (pregunta.getEncuesta().getEstado() != EstadoEncuesta.ABIERTA) {
            throw new BusinessRuleException("La encuesta " + pregunta.getEncuesta().getId() + " esta " + pregunta.getEncuesta().getEstado());
        }
        boolean valido = switch (pregunta.getTipo()) {
            case ESCALA_1_5 -> request.getValorEscala() != null && request.getValorEscala() >= 1;
            case SI_NO -> request.getValorEscala() != null && request.getValorEscala() <= 1;
            case ABIERTA -> request.getValorTexto() != null && !request.getValorTexto().isBlank();
        };
        if (!valido) {
            throw new BusinessRuleException("La respuesta no corresponde al tipo de pregunta " + pregunta.getTipo()
                    + " (ESCALA_1_5: valorEscala 1-5, SI_NO: valorEscala 0/1, ABIERTA: valorTexto)");
        }
    }
}
