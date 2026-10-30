package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.ComisionCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.ComisionCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.ComisionCurricular;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.ComisionCurricularMapper;
import pe.edu.upeu.lamb.curriculo.repository.ComisionCurricularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComisionCurricularService {

    private final ComisionCurricularRepository comisionCurricularRepository;
    private final ComisionCurricularMapper comisionCurricularMapper;

    @Transactional(readOnly = true)
    public List<ComisionCurricularResponse> listar() {
        return comisionCurricularRepository.findAll().stream()
                .map(comisionCurricularMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ComisionCurricularResponse obtener(Long id) {
        return comisionCurricularMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ComisionCurricularResponse crear(ComisionCurricularRequest request) {
        validar(request, null);
        ComisionCurricular comisionCurricular = comisionCurricularMapper.toEntity(request);
        return comisionCurricularMapper.toResponse(comisionCurricularRepository.save(comisionCurricular));
    }

    @Transactional
    public ComisionCurricularResponse actualizar(Long id, ComisionCurricularRequest request) {
        ComisionCurricular comisionCurricular = buscarOFallar(id);
        validar(request, id);
        comisionCurricular.setIdPrograma(request.getIdPrograma());
        comisionCurricular.setNombre(request.getNombre().trim());
        comisionCurricular.setResolucion(request.getResolucion());
        comisionCurricular.setFechaInicio(request.getFechaInicio());
        comisionCurricular.setFechaFin(request.getFechaFin());
        comisionCurricular.setActiva(request.getActiva());
        return comisionCurricularMapper.toResponse(comisionCurricularRepository.save(comisionCurricular));
    }

    @Transactional
    public void eliminar(Long id) {
        comisionCurricularRepository.delete(buscarOFallar(id));
    }

    ComisionCurricular buscarOFallar(Long id) {
        return comisionCurricularRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comision curricular no encontrada: " + id));
    }

    private void validar(ComisionCurricularRequest request, Long id) {
        if (request.getFechaFin() != null && request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha fin no puede ser anterior a fecha inicio");
        }
    }
}
