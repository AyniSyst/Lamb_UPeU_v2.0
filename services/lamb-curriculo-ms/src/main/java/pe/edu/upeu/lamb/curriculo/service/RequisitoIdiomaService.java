package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoIdiomaRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoIdiomaResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoIdioma;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.RequisitoIdiomaMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.RequisitoIdiomaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoIdiomaService {

    private final RequisitoIdiomaRepository requisitoIdiomaRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final RequisitoIdiomaMapper requisitoIdiomaMapper;

    @Transactional(readOnly = true)
    public List<RequisitoIdiomaResponse> listar(Long idPlan) {
        List<RequisitoIdioma> lista = idPlan == null
                ? requisitoIdiomaRepository.findAll()
                : requisitoIdiomaRepository.findByPlanId(idPlan);
        return lista.stream().map(requisitoIdiomaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RequisitoIdiomaResponse obtener(Long id) {
        return requisitoIdiomaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RequisitoIdiomaResponse crear(RequisitoIdiomaRequest request) {
        validar(request, null);
        RequisitoIdioma requisitoIdioma = requisitoIdiomaMapper.toEntity(request);
        asignarRelaciones(requisitoIdioma, request);
        return requisitoIdiomaMapper.toResponse(requisitoIdiomaRepository.save(requisitoIdioma));
    }

    @Transactional
    public RequisitoIdiomaResponse actualizar(Long id, RequisitoIdiomaRequest request) {
        RequisitoIdioma requisitoIdioma = buscarOFallar(id);
        validar(request, id);
        requisitoIdioma.setIdioma(request.getIdioma().trim());
        requisitoIdioma.setTipo(request.getTipo());
        requisitoIdioma.setNivel(request.getNivel().trim());
        requisitoIdioma.setModoAcreditacion(request.getModoAcreditacion());
        asignarRelaciones(requisitoIdioma, request);
        return requisitoIdiomaMapper.toResponse(requisitoIdiomaRepository.save(requisitoIdioma));
    }

    @Transactional
    public void eliminar(Long id) {
        requisitoIdiomaRepository.delete(buscarOFallar(id));
    }

    RequisitoIdioma buscarOFallar(Long id) {
        return requisitoIdiomaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requisito de idioma no encontrado: " + id));
    }

    private void validar(RequisitoIdiomaRequest request, Long id) {
        boolean duplicado = id == null
                ? requisitoIdiomaRepository.existsByPlanIdAndIdioma(request.getIdPlan(), request.getIdioma().trim())
                : requisitoIdiomaRepository.existsByPlanIdAndIdiomaAndIdNot(request.getIdPlan(), request.getIdioma().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de requisito de idioma para esa combinacion de: plan, idioma");
        }
    }

    private void asignarRelaciones(RequisitoIdioma requisitoIdioma, RequisitoIdiomaRequest request) {
        requisitoIdioma.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
