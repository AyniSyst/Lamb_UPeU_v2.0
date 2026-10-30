package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.LimiteCreditosRequest;
import pe.edu.upeu.lamb.planificacion.dto.LimiteCreditosResponse;
import pe.edu.upeu.lamb.planificacion.entity.LimiteCreditos;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.LimiteCreditosMapper;
import pe.edu.upeu.lamb.planificacion.repository.LimiteCreditosRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LimiteCreditosService {

    private final LimiteCreditosRepository limiteCreditosRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final LimiteCreditosMapper limiteCreditosMapper;

    @Transactional(readOnly = true)
    public List<LimiteCreditosResponse> listar(Long idPeriodo) {
        List<LimiteCreditos> lista = idPeriodo == null
                ? limiteCreditosRepository.findAll()
                : limiteCreditosRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(limiteCreditosMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public LimiteCreditosResponse obtener(Long id) {
        return limiteCreditosMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public LimiteCreditosResponse crear(LimiteCreditosRequest request) {
        validar(request, null);
        LimiteCreditos limiteCreditos = limiteCreditosMapper.toEntity(request);
        asignarRelaciones(limiteCreditos, request);
        return limiteCreditosMapper.toResponse(limiteCreditosRepository.save(limiteCreditos));
    }

    @Transactional
    public LimiteCreditosResponse actualizar(Long id, LimiteCreditosRequest request) {
        LimiteCreditos limiteCreditos = buscarOFallar(id);
        validar(request, id);
        limiteCreditos.setIdPrograma(request.getIdPrograma());
        limiteCreditos.setCiclo(request.getCiclo());
        limiteCreditos.setCreditosMinimos(request.getCreditosMinimos());
        limiteCreditos.setCreditosMaximos(request.getCreditosMaximos());
        asignarRelaciones(limiteCreditos, request);
        return limiteCreditosMapper.toResponse(limiteCreditosRepository.save(limiteCreditos));
    }

    @Transactional
    public void eliminar(Long id) {
        limiteCreditosRepository.delete(buscarOFallar(id));
    }

    LimiteCreditos buscarOFallar(Long id) {
        return limiteCreditosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Limite de creditos por ciclo no encontrado: " + id));
    }

    private void validar(LimiteCreditosRequest request, Long id) {
        if (request.getCreditosMaximos() < request.getCreditosMinimos()) {
            throw new BusinessRuleException("Creditos maximos no puede ser menor que creditos minimos");
        }
        boolean duplicado = id == null
                ? limiteCreditosRepository.existsByPeriodoIdAndIdProgramaAndCiclo(request.getIdPeriodo(), request.getIdPrograma(), request.getCiclo())
                : limiteCreditosRepository.existsByPeriodoIdAndIdProgramaAndCicloAndIdNot(request.getIdPeriodo(), request.getIdPrograma(), request.getCiclo(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de limite de creditos por ciclo para esa combinacion de: periodo, programa, ciclo");
        }
    }

    private void asignarRelaciones(LimiteCreditos limiteCreditos, LimiteCreditosRequest request) {
        limiteCreditos.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
    }
}
