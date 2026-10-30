package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.ModalidadTitulacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.ModalidadTitulacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.ModalidadTitulacion;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.ModalidadTitulacionMapper;
import pe.edu.upeu.lamb.curriculo.repository.ModalidadTitulacionRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModalidadTitulacionService {

    private final ModalidadTitulacionRepository modalidadTitulacionRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final ModalidadTitulacionMapper modalidadTitulacionMapper;

    @Transactional(readOnly = true)
    public List<ModalidadTitulacionResponse> listar(Long idPlan) {
        List<ModalidadTitulacion> lista = idPlan == null
                ? modalidadTitulacionRepository.findAll()
                : modalidadTitulacionRepository.findByPlanId(idPlan);
        return lista.stream().map(modalidadTitulacionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ModalidadTitulacionResponse obtener(Long id) {
        return modalidadTitulacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ModalidadTitulacionResponse crear(ModalidadTitulacionRequest request) {
        validar(request, null);
        ModalidadTitulacion modalidadTitulacion = modalidadTitulacionMapper.toEntity(request);
        asignarRelaciones(modalidadTitulacion, request);
        return modalidadTitulacionMapper.toResponse(modalidadTitulacionRepository.save(modalidadTitulacion));
    }

    @Transactional
    public ModalidadTitulacionResponse actualizar(Long id, ModalidadTitulacionRequest request) {
        ModalidadTitulacion modalidadTitulacion = buscarOFallar(id);
        validar(request, id);
        modalidadTitulacion.setTipo(request.getTipo());
        modalidadTitulacion.setDescripcion(request.getDescripcion());
        modalidadTitulacion.setVigente(request.getVigente());
        asignarRelaciones(modalidadTitulacion, request);
        return modalidadTitulacionMapper.toResponse(modalidadTitulacionRepository.save(modalidadTitulacion));
    }

    @Transactional
    public void eliminar(Long id) {
        modalidadTitulacionRepository.delete(buscarOFallar(id));
    }

    ModalidadTitulacion buscarOFallar(Long id) {
        return modalidadTitulacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modalidad de titulacion no encontrada: " + id));
    }

    private void validar(ModalidadTitulacionRequest request, Long id) {
        boolean duplicado = id == null
                ? modalidadTitulacionRepository.existsByPlanIdAndTipo(request.getIdPlan(), request.getTipo())
                : modalidadTitulacionRepository.existsByPlanIdAndTipoAndIdNot(request.getIdPlan(), request.getTipo(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de modalidad de titulacion para esa combinacion de: plan, tipo");
        }
    }

    private void asignarRelaciones(ModalidadTitulacion modalidadTitulacion, ModalidadTitulacionRequest request) {
        modalidadTitulacion.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
