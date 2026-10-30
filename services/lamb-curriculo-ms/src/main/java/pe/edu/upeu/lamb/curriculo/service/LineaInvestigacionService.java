package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.LineaInvestigacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.LineaInvestigacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.LineaInvestigacion;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.LineaInvestigacionMapper;
import pe.edu.upeu.lamb.curriculo.repository.LineaInvestigacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineaInvestigacionService {

    private final LineaInvestigacionRepository lineaInvestigacionRepository;
    private final LineaInvestigacionMapper lineaInvestigacionMapper;

    @Transactional(readOnly = true)
    public List<LineaInvestigacionResponse> listar() {
        return lineaInvestigacionRepository.findAll().stream()
                .map(lineaInvestigacionMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public LineaInvestigacionResponse obtener(Long id) {
        return lineaInvestigacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public LineaInvestigacionResponse crear(LineaInvestigacionRequest request) {
        validar(request, null);
        LineaInvestigacion lineaInvestigacion = lineaInvestigacionMapper.toEntity(request);
        return lineaInvestigacionMapper.toResponse(lineaInvestigacionRepository.save(lineaInvestigacion));
    }

    @Transactional
    public LineaInvestigacionResponse actualizar(Long id, LineaInvestigacionRequest request) {
        LineaInvestigacion lineaInvestigacion = buscarOFallar(id);
        validar(request, id);
        lineaInvestigacion.setIdPrograma(request.getIdPrograma());
        lineaInvestigacion.setCodigo(request.getCodigo().trim());
        lineaInvestigacion.setNombre(request.getNombre().trim());
        lineaInvestigacion.setDescripcion(request.getDescripcion());
        lineaInvestigacion.setActiva(request.getActiva());
        return lineaInvestigacionMapper.toResponse(lineaInvestigacionRepository.save(lineaInvestigacion));
    }

    @Transactional
    public void eliminar(Long id) {
        lineaInvestigacionRepository.delete(buscarOFallar(id));
    }

    LineaInvestigacion buscarOFallar(Long id) {
        return lineaInvestigacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Linea de investigacion no encontrada: " + id));
    }

    private void validar(LineaInvestigacionRequest request, Long id) {
        boolean duplicado = id == null
                ? lineaInvestigacionRepository.existsByCodigo(request.getCodigo().trim())
                : lineaInvestigacionRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de linea de investigacion para esa combinacion de: codigo");
        }
    }
}
