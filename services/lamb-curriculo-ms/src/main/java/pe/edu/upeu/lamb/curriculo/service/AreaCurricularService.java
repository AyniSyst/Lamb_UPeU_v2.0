package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.AreaCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.AreaCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.AreaCurricular;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.AreaCurricularMapper;
import pe.edu.upeu.lamb.curriculo.repository.AreaCurricularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaCurricularService {

    private final AreaCurricularRepository areaCurricularRepository;
    private final AreaCurricularMapper areaCurricularMapper;

    @Transactional(readOnly = true)
    public List<AreaCurricularResponse> listar() {
        return areaCurricularRepository.findAll().stream()
                .map(areaCurricularMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AreaCurricularResponse obtener(Long id) {
        return areaCurricularMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public AreaCurricularResponse crear(AreaCurricularRequest request) {
        validar(request, null);
        AreaCurricular areaCurricular = areaCurricularMapper.toEntity(request);
        return areaCurricularMapper.toResponse(areaCurricularRepository.save(areaCurricular));
    }

    @Transactional
    public AreaCurricularResponse actualizar(Long id, AreaCurricularRequest request) {
        AreaCurricular areaCurricular = buscarOFallar(id);
        validar(request, id);
        areaCurricular.setCodigo(request.getCodigo().trim());
        areaCurricular.setNombre(request.getNombre().trim());
        areaCurricular.setDescripcion(request.getDescripcion());
        return areaCurricularMapper.toResponse(areaCurricularRepository.save(areaCurricular));
    }

    @Transactional
    public void eliminar(Long id) {
        areaCurricularRepository.delete(buscarOFallar(id));
    }

    AreaCurricular buscarOFallar(Long id) {
        return areaCurricularRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Area curricular no encontrada: " + id));
    }

    private void validar(AreaCurricularRequest request, Long id) {
        boolean duplicado = id == null
                ? areaCurricularRepository.existsByCodigo(request.getCodigo().trim())
                : areaCurricularRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de area curricular para esa combinacion de: codigo");
        }
    }
}
