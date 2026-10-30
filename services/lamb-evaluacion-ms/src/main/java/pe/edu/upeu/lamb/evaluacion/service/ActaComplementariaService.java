package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.ActaComplementariaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ActaComplementariaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.Acta;
import pe.edu.upeu.lamb.evaluacion.entity.ActaComplementaria;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.ActaComplementariaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.ActaComplementariaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.ActaRepository;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoActa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActaComplementariaService {

    private final ActaComplementariaRepository actaComplementariaRepository;
    private final ActaRepository actaRepository;
    private final ActaComplementariaMapper actaComplementariaMapper;

    @Transactional(readOnly = true)
    public List<ActaComplementariaResponse> listar(Long idActa) {
        List<ActaComplementaria> lista = idActa == null
                ? actaComplementariaRepository.findAll()
                : actaComplementariaRepository.findByActaId(idActa);
        return lista.stream().map(actaComplementariaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ActaComplementariaResponse obtener(Long id) {
        return actaComplementariaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ActaComplementariaResponse crear(ActaComplementariaRequest request) {
        validar(request, null);
        ActaComplementaria actaComplementaria = actaComplementariaMapper.toEntity(request);
        asignarRelaciones(actaComplementaria, request);
        return actaComplementariaMapper.toResponse(actaComplementariaRepository.save(actaComplementaria));
    }

    @Transactional
    public ActaComplementariaResponse actualizar(Long id, ActaComplementariaRequest request) {
        ActaComplementaria actaComplementaria = buscarOFallar(id);
        validar(request, id);
        actaComplementaria.setIdPersona(request.getIdPersona());
        actaComplementaria.setNotaSustitutorio(request.getNotaSustitutorio());
        actaComplementaria.setFecha(request.getFecha());
        actaComplementaria.setObservacion(request.getObservacion());
        asignarRelaciones(actaComplementaria, request);
        return actaComplementariaMapper.toResponse(actaComplementariaRepository.save(actaComplementaria));
    }

    @Transactional
    public void eliminar(Long id) {
        actaComplementariaRepository.delete(buscarOFallar(id));
    }

    ActaComplementaria buscarOFallar(Long id) {
        return actaComplementariaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acta complementaria no encontrada: " + id));
    }

    private void validar(ActaComplementariaRequest request, Long id) {
        boolean duplicado = id == null
                ? actaComplementariaRepository.existsByActaIdAndIdPersona(request.getIdActa(), request.getIdPersona())
                : actaComplementariaRepository.existsByActaIdAndIdPersonaAndIdNot(request.getIdActa(), request.getIdPersona(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de acta complementaria para esa combinacion de: acta, persona");
        }
        Acta acta = actaRepository.findById(request.getIdActa())
                .orElseThrow(() -> new ResourceNotFoundException("Acta no encontrada: " + request.getIdActa()));
        if (acta.getEstado() != EstadoActa.CERRADA) {
            throw new BusinessRuleException("El acta " + acta.getId() + " esta " + acta.getEstado() + "; esta operacion es sobre un acta CERRADA");
        }
    }

    private void asignarRelaciones(ActaComplementaria actaComplementaria, ActaComplementariaRequest request) {
        actaComplementaria.setActa(actaRepository.findById(request.getIdActa())
                .orElseThrow(() -> new ResourceNotFoundException("Acta no encontrada: " + request.getIdActa())));
    }
}
