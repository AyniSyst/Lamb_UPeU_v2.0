package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.PeriodoAcademicoRequest;
import pe.edu.upeu.lamb.planificacion.dto.PeriodoAcademicoResponse;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.entity.TipoPeriodo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.PeriodoAcademicoMapper;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import pe.edu.upeu.lamb.planificacion.repository.TipoPeriodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodoAcademicoService {

    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final TipoPeriodoRepository tipoPeriodoRepository;
    private final PeriodoAcademicoMapper periodoAcademicoMapper;

    @Transactional(readOnly = true)
    public List<PeriodoAcademicoResponse> listar(Long idTipoPeriodo) {
        List<PeriodoAcademico> lista = idTipoPeriodo == null
                ? periodoAcademicoRepository.findAll()
                : periodoAcademicoRepository.findByTipoPeriodoId(idTipoPeriodo);
        return lista.stream().map(periodoAcademicoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PeriodoAcademicoResponse obtener(Long id) {
        return periodoAcademicoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public PeriodoAcademicoResponse crear(PeriodoAcademicoRequest request) {
        validar(request, null);
        PeriodoAcademico periodoAcademico = periodoAcademicoMapper.toEntity(request);
        asignarRelaciones(periodoAcademico, request);
        return periodoAcademicoMapper.toResponse(periodoAcademicoRepository.save(periodoAcademico));
    }

    @Transactional
    public PeriodoAcademicoResponse actualizar(Long id, PeriodoAcademicoRequest request) {
        PeriodoAcademico periodoAcademico = buscarOFallar(id);
        validar(request, id);
        periodoAcademico.setCodigo(request.getCodigo().trim());
        periodoAcademico.setNombre(request.getNombre().trim());
        periodoAcademico.setFechaInicio(request.getFechaInicio());
        periodoAcademico.setFechaFin(request.getFechaFin());
        periodoAcademico.setEstado(request.getEstado());
        asignarRelaciones(periodoAcademico, request);
        return periodoAcademicoMapper.toResponse(periodoAcademicoRepository.save(periodoAcademico));
    }

    @Transactional
    public void eliminar(Long id) {
        periodoAcademicoRepository.delete(buscarOFallar(id));
    }

    PeriodoAcademico buscarOFallar(Long id) {
        return periodoAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + id));
    }

    private void validar(PeriodoAcademicoRequest request, Long id) {
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha fin no puede ser anterior a fecha inicio");
        }
        boolean duplicado = id == null
                ? periodoAcademicoRepository.existsByCodigo(request.getCodigo().trim())
                : periodoAcademicoRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de periodo academico para esa combinacion de: codigo");
        }
    }

    private void asignarRelaciones(PeriodoAcademico periodoAcademico, PeriodoAcademicoRequest request) {
        periodoAcademico.setTipoPeriodo(tipoPeriodoRepository.findById(request.getIdTipoPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de periodo no encontrado: " + request.getIdTipoPeriodo())));
    }
}
