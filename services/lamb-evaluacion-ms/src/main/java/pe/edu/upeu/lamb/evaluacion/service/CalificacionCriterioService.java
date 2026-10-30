package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.CalificacionCriterioRequest;
import pe.edu.upeu.lamb.evaluacion.dto.CalificacionCriterioResponse;
import pe.edu.upeu.lamb.evaluacion.entity.CalificacionCriterio;
import pe.edu.upeu.lamb.evaluacion.entity.CriterioRubrica;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.CalificacionCriterioMapper;
import pe.edu.upeu.lamb.evaluacion.repository.CalificacionCriterioRepository;
import pe.edu.upeu.lamb.evaluacion.repository.CriterioRubricaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalificacionCriterioService {

    private final CalificacionCriterioRepository calificacionCriterioRepository;
    private final CriterioRubricaRepository criterioRubricaRepository;
    private final CalificacionCriterioMapper calificacionCriterioMapper;

    @Transactional(readOnly = true)
    public List<CalificacionCriterioResponse> listar(Long idCriterio) {
        List<CalificacionCriterio> lista = idCriterio == null
                ? calificacionCriterioRepository.findAll()
                : calificacionCriterioRepository.findByCriterioId(idCriterio);
        return lista.stream().map(calificacionCriterioMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CalificacionCriterioResponse obtener(Long id) {
        return calificacionCriterioMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CalificacionCriterioResponse crear(CalificacionCriterioRequest request) {
        validar(request, null);
        CalificacionCriterio calificacionCriterio = calificacionCriterioMapper.toEntity(request);
        asignarRelaciones(calificacionCriterio, request);
        return calificacionCriterioMapper.toResponse(calificacionCriterioRepository.save(calificacionCriterio));
    }

    @Transactional
    public CalificacionCriterioResponse actualizar(Long id, CalificacionCriterioRequest request) {
        CalificacionCriterio calificacionCriterio = buscarOFallar(id);
        validar(request, id);
        calificacionCriterio.setIdGrupo(request.getIdGrupo());
        calificacionCriterio.setIdPersona(request.getIdPersona());
        calificacionCriterio.setPuntaje(request.getPuntaje());
        asignarRelaciones(calificacionCriterio, request);
        return calificacionCriterioMapper.toResponse(calificacionCriterioRepository.save(calificacionCriterio));
    }

    @Transactional
    public void eliminar(Long id) {
        calificacionCriterioRepository.delete(buscarOFallar(id));
    }

    CalificacionCriterio buscarOFallar(Long id) {
        return calificacionCriterioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificacion por criterio no encontrada: " + id));
    }

    private void validar(CalificacionCriterioRequest request, Long id) {
        boolean duplicado = id == null
                ? calificacionCriterioRepository.existsByCriterioIdAndIdGrupoAndIdPersona(request.getIdCriterio(), request.getIdGrupo(), request.getIdPersona())
                : calificacionCriterioRepository.existsByCriterioIdAndIdGrupoAndIdPersonaAndIdNot(request.getIdCriterio(), request.getIdGrupo(), request.getIdPersona(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de calificacion por criterio para esa combinacion de: criterio, grupo, persona");
        }
        CriterioRubrica criterio = criterioRubricaRepository.findById(request.getIdCriterio())
                .orElseThrow(() -> new ResourceNotFoundException("Criterio de rubrica no encontrado: " + request.getIdCriterio()));
        if (request.getPuntaje().compareTo(criterio.getPuntajeMaximo()) > 0) {
            throw new BusinessRuleException("El puntaje " + request.getPuntaje() + " pasa el maximo del criterio ("
                    + criterio.getPuntajeMaximo() + ")");
        }
    }

    private void asignarRelaciones(CalificacionCriterio calificacionCriterio, CalificacionCriterioRequest request) {
        calificacionCriterio.setCriterio(criterioRubricaRepository.findById(request.getIdCriterio())
                .orElseThrow(() -> new ResourceNotFoundException("Criterio de rubrica no encontrado: " + request.getIdCriterio())));
    }
}
