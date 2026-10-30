package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.ConfiguracionEvaluacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ConfiguracionEvaluacionResponse;
import pe.edu.upeu.lamb.evaluacion.entity.ComponenteEvaluacion;
import pe.edu.upeu.lamb.evaluacion.entity.ConfiguracionEvaluacion;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.ConfiguracionEvaluacionMapper;
import pe.edu.upeu.lamb.evaluacion.repository.ComponenteEvaluacionRepository;
import pe.edu.upeu.lamb.evaluacion.repository.ConfiguracionEvaluacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfiguracionEvaluacionService {

    private static final BigDecimal CIEN = new BigDecimal("100");

    private final ConfiguracionEvaluacionRepository configuracionEvaluacionRepository;
    private final ComponenteEvaluacionRepository componenteEvaluacionRepository;
    private final ConfiguracionEvaluacionMapper configuracionEvaluacionMapper;

    @Transactional(readOnly = true)
    public List<ConfiguracionEvaluacionResponse> listar() {
        return configuracionEvaluacionRepository.findAll().stream()
                .map(configuracionEvaluacionMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ConfiguracionEvaluacionResponse obtener(Long id) {
        return configuracionEvaluacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ConfiguracionEvaluacionResponse crear(ConfiguracionEvaluacionRequest request) {
        validar(request, null);
        ConfiguracionEvaluacion configuracionEvaluacion = configuracionEvaluacionMapper.toEntity(request);
        asignarRelaciones(configuracionEvaluacion, request);
        return configuracionEvaluacionMapper.toResponse(configuracionEvaluacionRepository.save(configuracionEvaluacion));
    }

    @Transactional
    public ConfiguracionEvaluacionResponse actualizar(Long id, ConfiguracionEvaluacionRequest request) {
        ConfiguracionEvaluacion configuracionEvaluacion = buscarOFallar(id);
        validar(request, id);
        configuracionEvaluacion.setIdGrupo(request.getIdGrupo());
        configuracionEvaluacion.setPeso(request.getPeso());
        asignarRelaciones(configuracionEvaluacion, request);
        return configuracionEvaluacionMapper.toResponse(configuracionEvaluacionRepository.save(configuracionEvaluacion));
    }

    @Transactional
    public void eliminar(Long id) {
        configuracionEvaluacionRepository.delete(buscarOFallar(id));
    }

    ConfiguracionEvaluacion buscarOFallar(Long id) {
        return configuracionEvaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Peso de componente no encontrado: " + id));
    }

    private void validar(ConfiguracionEvaluacionRequest request, Long id) {
        boolean duplicado = id == null
                ? configuracionEvaluacionRepository.existsByIdGrupoAndComponenteId(request.getIdGrupo(), request.getIdComponente())
                : configuracionEvaluacionRepository.existsByIdGrupoAndComponenteIdAndIdNot(request.getIdGrupo(), request.getIdComponente(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de peso de componente para esa combinacion de: grupo, componente");
        }
        BigDecimal otros = configuracionEvaluacionRepository.sumarPesosDelGrupo(request.getIdGrupo(), id == null ? 0L : id);
        if (otros.add(request.getPeso()).compareTo(CIEN) > 0) {
            throw new BusinessRuleException("Los pesos del grupo " + request.getIdGrupo() + " ya suman " + otros
                    + "; con " + request.getPeso() + " mas pasarian de 100");
        }
    }

    private void asignarRelaciones(ConfiguracionEvaluacion configuracionEvaluacion, ConfiguracionEvaluacionRequest request) {
        configuracionEvaluacion.setComponente(componenteEvaluacionRepository.findById(request.getIdComponente())
                .orElseThrow(() -> new ResourceNotFoundException("Componente de evaluacion no encontrado: " + request.getIdComponente())));
    }
}
