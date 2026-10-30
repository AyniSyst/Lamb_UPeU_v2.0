package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.CompetenciaGeneralRequest;
import pe.edu.upeu.lamb.curriculo.dto.CompetenciaGeneralResponse;
import pe.edu.upeu.lamb.curriculo.entity.CompetenciaGeneral;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.CompetenciaGeneralMapper;
import pe.edu.upeu.lamb.curriculo.repository.CompetenciaGeneralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetenciaGeneralService {

    private final CompetenciaGeneralRepository competenciaGeneralRepository;
    private final CompetenciaGeneralMapper competenciaGeneralMapper;

    @Transactional(readOnly = true)
    public List<CompetenciaGeneralResponse> listar() {
        return competenciaGeneralRepository.findAll().stream()
                .map(competenciaGeneralMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CompetenciaGeneralResponse obtener(Long id) {
        return competenciaGeneralMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CompetenciaGeneralResponse crear(CompetenciaGeneralRequest request) {
        validar(request, null);
        CompetenciaGeneral competenciaGeneral = competenciaGeneralMapper.toEntity(request);
        return competenciaGeneralMapper.toResponse(competenciaGeneralRepository.save(competenciaGeneral));
    }

    @Transactional
    public CompetenciaGeneralResponse actualizar(Long id, CompetenciaGeneralRequest request) {
        CompetenciaGeneral competenciaGeneral = buscarOFallar(id);
        validar(request, id);
        competenciaGeneral.setCodigo(request.getCodigo().trim());
        competenciaGeneral.setNombre(request.getNombre().trim());
        competenciaGeneral.setDescripcion(request.getDescripcion());
        return competenciaGeneralMapper.toResponse(competenciaGeneralRepository.save(competenciaGeneral));
    }

    @Transactional
    public void eliminar(Long id) {
        competenciaGeneralRepository.delete(buscarOFallar(id));
    }

    CompetenciaGeneral buscarOFallar(Long id) {
        return competenciaGeneralRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competencia general no encontrada: " + id));
    }

    private void validar(CompetenciaGeneralRequest request, Long id) {
        boolean duplicado = id == null
                ? competenciaGeneralRepository.existsByCodigo(request.getCodigo().trim())
                : competenciaGeneralRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de competencia general para esa combinacion de: codigo");
        }
    }
}
