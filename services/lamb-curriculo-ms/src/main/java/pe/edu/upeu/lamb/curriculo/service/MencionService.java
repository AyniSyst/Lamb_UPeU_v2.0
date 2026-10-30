package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.MencionRequest;
import pe.edu.upeu.lamb.curriculo.dto.MencionResponse;
import pe.edu.upeu.lamb.curriculo.entity.Mencion;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.MencionMapper;
import pe.edu.upeu.lamb.curriculo.repository.MencionRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MencionService {

    private final MencionRepository mencionRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final MencionMapper mencionMapper;

    @Transactional(readOnly = true)
    public List<MencionResponse> listar(Long idPlan) {
        List<Mencion> lista = idPlan == null
                ? mencionRepository.findAll()
                : mencionRepository.findByPlanId(idPlan);
        return lista.stream().map(mencionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public MencionResponse obtener(Long id) {
        return mencionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public MencionResponse crear(MencionRequest request) {
        validar(request, null);
        Mencion mencion = mencionMapper.toEntity(request);
        asignarRelaciones(mencion, request);
        return mencionMapper.toResponse(mencionRepository.save(mencion));
    }

    @Transactional
    public MencionResponse actualizar(Long id, MencionRequest request) {
        Mencion mencion = buscarOFallar(id);
        validar(request, id);
        mencion.setNombre(request.getNombre().trim());
        mencion.setCreditosRequeridos(request.getCreditosRequeridos());
        mencion.setDescripcion(request.getDescripcion());
        asignarRelaciones(mencion, request);
        return mencionMapper.toResponse(mencionRepository.save(mencion));
    }

    @Transactional
    public void eliminar(Long id) {
        mencionRepository.delete(buscarOFallar(id));
    }

    Mencion buscarOFallar(Long id) {
        return mencionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mencion no encontrada: " + id));
    }

    private void validar(MencionRequest request, Long id) {
        boolean duplicado = id == null
                ? mencionRepository.existsByPlanIdAndNombre(request.getIdPlan(), request.getNombre().trim())
                : mencionRepository.existsByPlanIdAndNombreAndIdNot(request.getIdPlan(), request.getNombre().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de mencion para esa combinacion de: plan, nombre");
        }
    }

    private void asignarRelaciones(Mencion mencion, MencionRequest request) {
        mencion.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
