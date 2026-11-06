package pe.edu.upeu.lamb.institucional.service;

import pe.edu.upeu.lamb.institucional.dto.ProgramaRequest;
import pe.edu.upeu.lamb.institucional.dto.ProgramaResponse;
import pe.edu.upeu.lamb.institucional.entity.Programa;
import pe.edu.upeu.lamb.institucional.entity.NivelAcademico;
import pe.edu.upeu.lamb.institucional.entity.GradoAcademicoCatalogo;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ProgramaMapper;
import pe.edu.upeu.lamb.institucional.repository.NivelAcademicoRepository;
import pe.edu.upeu.lamb.institucional.repository.GradoAcademicoCatalogoRepository;
import pe.edu.upeu.lamb.institucional.repository.ProgramaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgramaService {

    private final ProgramaRepository programaRepository;
    private final ProgramaMapper programaMapper;
    private final FacultadService facultadService;
    private final NivelAcademicoRepository nivelAcademicoRepository;
    private final GradoAcademicoCatalogoRepository gradoAcademicoCatalogoRepository;

    public List<ProgramaResponse> listar() {
        return programaRepository.findAllConFacultad().stream()
                .map(programaMapper::toResponse)
                .toList();
    }

    public ProgramaResponse obtener(Long id) {
        return programaMapper.toResponse(buscarOFallar(id));
    }

    public ProgramaResponse crear(ProgramaRequest request) {
        Programa programa = programaMapper.toEntity(request);
        programa.setFacultad(facultadService.buscarOFallar(request.getFacultadId()));
        programa.setNivelAcademico(buscarNivelAcademico(request.getNivelAcademicoId()));
        programa.setGradoAcademicoCatalogo(buscarGradoAcademicoCatalogo(request.getGradoAcademicoCatalogoId()));
        return programaMapper.toResponse(programaRepository.save(programa));
    }

    public ProgramaResponse actualizar(Long id, ProgramaRequest request) {
        Programa programa = buscarOFallar(id);
        programa.setNombre(request.getNombre());
        if (request.getGradoAcademico() != null) {
            programa.setGradoAcademico(request.getGradoAcademico());
        }
        programa.setDuracionSemestres(request.getDuracionSemestres());
        programa.setFacultad(facultadService.buscarOFallar(request.getFacultadId()));
        programa.setNivelAcademico(buscarNivelAcademico(request.getNivelAcademicoId()));
        programa.setGradoAcademicoCatalogo(buscarGradoAcademicoCatalogo(request.getGradoAcademicoCatalogoId()));
        return programaMapper.toResponse(programaRepository.save(programa));
    }

    private NivelAcademico buscarNivelAcademico(Long nivelAcademicoId) {
        if (nivelAcademicoId == null) {
            return null;
        }
        return nivelAcademicoRepository.findById(nivelAcademicoId)
                .orElseThrow(() -> new ResourceNotFoundException("NivelAcademico no encontrado: " + nivelAcademicoId));
    }

    private GradoAcademicoCatalogo buscarGradoAcademicoCatalogo(Long gradoAcademicoCatalogoId) {
        if (gradoAcademicoCatalogoId == null) {
            return null;
        }
        return gradoAcademicoCatalogoRepository.findById(gradoAcademicoCatalogoId)
                .orElseThrow(() -> new ResourceNotFoundException("GradoAcademicoCatalogo no encontrado: " + gradoAcademicoCatalogoId));
    }

    public void eliminar(Long id) {
        programaRepository.delete(buscarOFallar(id));
    }

    private Programa buscarOFallar(Long id) {
        return programaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programa no encontrado: " + id));
    }
}
