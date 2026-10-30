package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.DepartamentoAcademicoRequest;
import pe.edu.upeu.lamb.curriculo.dto.DepartamentoAcademicoResponse;
import pe.edu.upeu.lamb.curriculo.entity.DepartamentoAcademico;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.DepartamentoAcademicoMapper;
import pe.edu.upeu.lamb.curriculo.repository.DepartamentoAcademicoRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoAcademicoService {

    private final DepartamentoAcademicoRepository departamentoAcademicoRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final DepartamentoAcademicoMapper departamentoAcademicoMapper;

    @Transactional(readOnly = true)
    public List<DepartamentoAcademicoResponse> listar(Long idPlan) {
        List<DepartamentoAcademico> lista = idPlan == null
                ? departamentoAcademicoRepository.findAll()
                : departamentoAcademicoRepository.findByPlanId(idPlan);
        return lista.stream().map(departamentoAcademicoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public DepartamentoAcademicoResponse obtener(Long id) {
        return departamentoAcademicoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DepartamentoAcademicoResponse crear(DepartamentoAcademicoRequest request) {
        validar(request, null);
        DepartamentoAcademico departamentoAcademico = departamentoAcademicoMapper.toEntity(request);
        asignarRelaciones(departamentoAcademico, request);
        return departamentoAcademicoMapper.toResponse(departamentoAcademicoRepository.save(departamentoAcademico));
    }

    @Transactional
    public DepartamentoAcademicoResponse actualizar(Long id, DepartamentoAcademicoRequest request) {
        DepartamentoAcademico departamentoAcademico = buscarOFallar(id);
        validar(request, id);
        departamentoAcademico.setCodigo(request.getCodigo().trim());
        departamentoAcademico.setNombre(request.getNombre().trim());
        departamentoAcademico.setIdFacultad(request.getIdFacultad());
        asignarRelaciones(departamentoAcademico, request);
        return departamentoAcademicoMapper.toResponse(departamentoAcademicoRepository.save(departamentoAcademico));
    }

    @Transactional
    public void eliminar(Long id) {
        departamentoAcademicoRepository.delete(buscarOFallar(id));
    }

    DepartamentoAcademico buscarOFallar(Long id) {
        return departamentoAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento academico no encontrado: " + id));
    }

    private void validar(DepartamentoAcademicoRequest request, Long id) {
        boolean duplicado = id == null
                ? departamentoAcademicoRepository.existsByCodigo(request.getCodigo().trim())
                : departamentoAcademicoRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de departamento academico para esa combinacion de: codigo");
        }
    }

    private void asignarRelaciones(DepartamentoAcademico departamentoAcademico, DepartamentoAcademicoRequest request) {
        departamentoAcademico.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }
}
