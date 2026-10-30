package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.FirmaActaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.FirmaActaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.Acta;
import pe.edu.upeu.lamb.evaluacion.entity.FirmaActa;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.FirmaActaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.ActaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.FirmaActaRepository;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoActa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FirmaActaService {

    private final FirmaActaRepository firmaActaRepository;
    private final ActaRepository actaRepository;
    private final FirmaActaMapper firmaActaMapper;

    @Transactional(readOnly = true)
    public List<FirmaActaResponse> listar(Long idActa) {
        List<FirmaActa> lista = idActa == null
                ? firmaActaRepository.findAll()
                : firmaActaRepository.findByActaId(idActa);
        return lista.stream().map(firmaActaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public FirmaActaResponse obtener(Long id) {
        return firmaActaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public FirmaActaResponse crear(FirmaActaRequest request) {
        validar(request, null);
        FirmaActa firmaActa = firmaActaMapper.toEntity(request);
        asignarRelaciones(firmaActa, request);
        return firmaActaMapper.toResponse(firmaActaRepository.save(firmaActa));
    }

    @Transactional
    public FirmaActaResponse actualizar(Long id, FirmaActaRequest request) {
        FirmaActa firmaActa = buscarOFallar(id);
        validar(request, id);
        firmaActa.setIdPersona(request.getIdPersona());
        firmaActa.setRol(request.getRol());
        firmaActa.setFecha(request.getFecha());
        asignarRelaciones(firmaActa, request);
        return firmaActaMapper.toResponse(firmaActaRepository.save(firmaActa));
    }

    @Transactional
    public void eliminar(Long id) {
        firmaActaRepository.delete(buscarOFallar(id));
    }

    FirmaActa buscarOFallar(Long id) {
        return firmaActaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Firma / visado del acta no encontrada: " + id));
    }

    private void validar(FirmaActaRequest request, Long id) {
        boolean duplicado = id == null
                ? firmaActaRepository.existsByActaIdAndRol(request.getIdActa(), request.getRol())
                : firmaActaRepository.existsByActaIdAndRolAndIdNot(request.getIdActa(), request.getRol(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de firma / visado del acta para esa combinacion de: acta, rol");
        }
        Acta acta = actaRepository.findById(request.getIdActa())
                .orElseThrow(() -> new ResourceNotFoundException("Acta no encontrada: " + request.getIdActa()));
        if (acta.getEstado() != EstadoActa.CERRADA) {
            throw new BusinessRuleException("El acta " + acta.getId() + " esta " + acta.getEstado() + "; esta operacion es sobre un acta CERRADA");
        }
    }

    private void asignarRelaciones(FirmaActa firmaActa, FirmaActaRequest request) {
        firmaActa.setActa(actaRepository.findById(request.getIdActa())
                .orElseThrow(() -> new ResourceNotFoundException("Acta no encontrada: " + request.getIdActa())));
    }
}
