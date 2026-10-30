package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.ParametroMatriculaRequest;
import pe.edu.upeu.lamb.planificacion.dto.ParametroMatriculaResponse;
import pe.edu.upeu.lamb.planificacion.entity.ParametroMatricula;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.ParametroMatriculaMapper;
import pe.edu.upeu.lamb.planificacion.repository.ParametroMatriculaRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParametroMatriculaService {

    private final ParametroMatriculaRepository parametroMatriculaRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final ParametroMatriculaMapper parametroMatriculaMapper;

    @Transactional(readOnly = true)
    public List<ParametroMatriculaResponse> listar(Long idPeriodo) {
        List<ParametroMatricula> lista = idPeriodo == null
                ? parametroMatriculaRepository.findAll()
                : parametroMatriculaRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(parametroMatriculaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ParametroMatriculaResponse obtener(Long id) {
        return parametroMatriculaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ParametroMatriculaResponse crear(ParametroMatriculaRequest request) {
        validar(request, null);
        ParametroMatricula parametroMatricula = parametroMatriculaMapper.toEntity(request);
        asignarRelaciones(parametroMatricula, request);
        return parametroMatriculaMapper.toResponse(parametroMatriculaRepository.save(parametroMatricula));
    }

    @Transactional
    public ParametroMatriculaResponse actualizar(Long id, ParametroMatriculaRequest request) {
        ParametroMatricula parametroMatricula = buscarOFallar(id);
        validar(request, id);
        parametroMatricula.setIdPrograma(request.getIdPrograma());
        parametroMatricula.setFechaInicio(request.getFechaInicio());
        parametroMatricula.setFechaFin(request.getFechaFin());
        parametroMatricula.setFechaLimiteRetiro(request.getFechaLimiteRetiro());
        parametroMatricula.setPermiteExtemporanea(request.getPermiteExtemporanea());
        parametroMatricula.setRecargoExtemporanea(request.getRecargoExtemporanea());
        asignarRelaciones(parametroMatricula, request);
        return parametroMatriculaMapper.toResponse(parametroMatriculaRepository.save(parametroMatricula));
    }

    @Transactional
    public void eliminar(Long id) {
        parametroMatriculaRepository.delete(buscarOFallar(id));
    }

    ParametroMatricula buscarOFallar(Long id) {
        return parametroMatriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parametro de matricula no encontrado: " + id));
    }

    private void validar(ParametroMatriculaRequest request, Long id) {
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha fin no puede ser anterior a fecha inicio");
        }
        if (request.getFechaLimiteRetiro() != null && request.getFechaLimiteRetiro().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha limite retiro no puede ser anterior a fecha inicio");
        }
        boolean duplicado = id == null
                ? parametroMatriculaRepository.existsByPeriodoIdAndIdPrograma(request.getIdPeriodo(), request.getIdPrograma())
                : parametroMatriculaRepository.existsByPeriodoIdAndIdProgramaAndIdNot(request.getIdPeriodo(), request.getIdPrograma(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de parametro de matricula para esa combinacion de: periodo, programa");
        }
    }

    private void asignarRelaciones(ParametroMatricula parametroMatricula, ParametroMatriculaRequest request) {
        parametroMatricula.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
    }
}
