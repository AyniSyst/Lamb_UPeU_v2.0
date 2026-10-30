package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.GradoAcademicoRequest;
import pe.edu.upeu.lamb.curriculo.dto.GradoAcademicoResponse;
import pe.edu.upeu.lamb.curriculo.entity.GradoAcademico;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.GradoAcademicoMapper;
import pe.edu.upeu.lamb.curriculo.repository.GradoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradoAcademicoService {

    private final GradoAcademicoRepository gradoAcademicoRepository;
    private final GradoAcademicoMapper gradoAcademicoMapper;

    @Transactional(readOnly = true)
    public List<GradoAcademicoResponse> listar() {
        return gradoAcademicoRepository.findAll().stream()
                .map(gradoAcademicoMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public GradoAcademicoResponse obtener(Long id) {
        return gradoAcademicoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public GradoAcademicoResponse crear(GradoAcademicoRequest request) {
        validar(request, null);
        GradoAcademico gradoAcademico = gradoAcademicoMapper.toEntity(request);
        return gradoAcademicoMapper.toResponse(gradoAcademicoRepository.save(gradoAcademico));
    }

    @Transactional
    public GradoAcademicoResponse actualizar(Long id, GradoAcademicoRequest request) {
        GradoAcademico gradoAcademico = buscarOFallar(id);
        validar(request, id);
        gradoAcademico.setIdPrograma(request.getIdPrograma());
        gradoAcademico.setDenominacion(request.getDenominacion().trim());
        gradoAcademico.setResolucion(request.getResolucion());
        return gradoAcademicoMapper.toResponse(gradoAcademicoRepository.save(gradoAcademico));
    }

    @Transactional
    public void eliminar(Long id) {
        gradoAcademicoRepository.delete(buscarOFallar(id));
    }

    GradoAcademico buscarOFallar(Long id) {
        return gradoAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grado academico no encontrado: " + id));
    }

    private void validar(GradoAcademicoRequest request, Long id) {
        boolean duplicado = id == null
                ? gradoAcademicoRepository.existsByIdPrograma(request.getIdPrograma())
                : gradoAcademicoRepository.existsByIdProgramaAndIdNot(request.getIdPrograma(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de grado academico para esa combinacion de: programa");
        }
    }
}
