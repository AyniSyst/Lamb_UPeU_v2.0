package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.EquivalenciaCursoRequest;
import pe.edu.upeu.lamb.planificacion.dto.EquivalenciaCursoResponse;
import pe.edu.upeu.lamb.planificacion.entity.Curso;
import pe.edu.upeu.lamb.planificacion.entity.EquivalenciaCurso;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.EquivalenciaCursoMapper;
import pe.edu.upeu.lamb.planificacion.repository.CursoRepository;
import pe.edu.upeu.lamb.planificacion.repository.EquivalenciaCursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquivalenciaCursoService {

    private final EquivalenciaCursoRepository equivalenciaCursoRepository;
    private final CursoRepository cursoRepository;
    private final EquivalenciaCursoMapper equivalenciaCursoMapper;

    @Transactional(readOnly = true)
    public List<EquivalenciaCursoResponse> listar(Long idCurso) {
        List<EquivalenciaCurso> lista = idCurso == null
                ? equivalenciaCursoRepository.findAll()
                : equivalenciaCursoRepository.findByCursoId(idCurso);
        return lista.stream().map(equivalenciaCursoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public EquivalenciaCursoResponse obtener(Long id) {
        return equivalenciaCursoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EquivalenciaCursoResponse crear(EquivalenciaCursoRequest request) {
        validar(request, null);
        EquivalenciaCurso equivalenciaCurso = equivalenciaCursoMapper.toEntity(request);
        asignarRelaciones(equivalenciaCurso, request);
        return equivalenciaCursoMapper.toResponse(equivalenciaCursoRepository.save(equivalenciaCurso));
    }

    @Transactional
    public EquivalenciaCursoResponse actualizar(Long id, EquivalenciaCursoRequest request) {
        EquivalenciaCurso equivalenciaCurso = buscarOFallar(id);
        validar(request, id);
        equivalenciaCurso.setIdProgramaOrigen(request.getIdProgramaOrigen());
        equivalenciaCurso.setIdProgramaEquivalente(request.getIdProgramaEquivalente());
        equivalenciaCurso.setObservacion(request.getObservacion());
        asignarRelaciones(equivalenciaCurso, request);
        return equivalenciaCursoMapper.toResponse(equivalenciaCursoRepository.save(equivalenciaCurso));
    }

    @Transactional
    public void eliminar(Long id) {
        equivalenciaCursoRepository.delete(buscarOFallar(id));
    }

    EquivalenciaCurso buscarOFallar(Long id) {
        return equivalenciaCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equivalencia o variacion de curso no encontrada: " + id));
    }

    private void validar(EquivalenciaCursoRequest request, Long id) {
        if (request.getIdCurso().equals(request.getIdCursoEquivalente())
                && request.getIdProgramaOrigen().equals(request.getIdProgramaEquivalente())) {
            throw new BusinessRuleException("Un curso no es equivalente a si mismo dentro del mismo programa");
        }
        boolean duplicado = id == null
                ? equivalenciaCursoRepository.existsByCursoIdAndIdProgramaOrigenAndCursoEquivalenteIdAndIdProgramaEquivalente(request.getIdCurso(), request.getIdProgramaOrigen(), request.getIdCursoEquivalente(), request.getIdProgramaEquivalente())
                : equivalenciaCursoRepository.existsByCursoIdAndIdProgramaOrigenAndCursoEquivalenteIdAndIdProgramaEquivalenteAndIdNot(request.getIdCurso(), request.getIdProgramaOrigen(), request.getIdCursoEquivalente(), request.getIdProgramaEquivalente(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de equivalencia o variacion de curso para esa combinacion de: curso, programa origen, curso equivalente, programa equivalente");
        }
    }

    private void asignarRelaciones(EquivalenciaCurso equivalenciaCurso, EquivalenciaCursoRequest request) {
        equivalenciaCurso.setCurso(cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + request.getIdCurso())));
        equivalenciaCurso.setCursoEquivalente(cursoRepository.findById(request.getIdCursoEquivalente())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + request.getIdCursoEquivalente())));
    }
}
