package pe.edu.upeu.lamb.institucional.service;

import pe.edu.upeu.lamb.institucional.dto.ProgramaRequest;
import pe.edu.upeu.lamb.institucional.dto.ProgramaResponse;
import pe.edu.upeu.lamb.institucional.entity.Programa;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ProgramaMapper;
import pe.edu.upeu.lamb.institucional.repository.ProgramaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramaService {

    private final ProgramaRepository programaRepository;
    private final ProgramaMapper programaMapper;
    private final FacultadService facultadService;

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
        return programaMapper.toResponse(programaRepository.save(programa));
    }

    public ProgramaResponse actualizar(Long id, ProgramaRequest request) {
        Programa programa = buscarOFallar(id);
        programa.setNombre(request.getNombre());
        programa.setGradoAcademico(request.getGradoAcademico());
        programa.setDuracionSemestres(request.getDuracionSemestres());
        programa.setFacultad(facultadService.buscarOFallar(request.getFacultadId()));
        return programaMapper.toResponse(programaRepository.save(programa));
    }

    public void eliminar(Long id) {
        programaRepository.delete(buscarOFallar(id));
    }

    private Programa buscarOFallar(Long id) {
        return programaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programa no encontrado: " + id));
    }
}
