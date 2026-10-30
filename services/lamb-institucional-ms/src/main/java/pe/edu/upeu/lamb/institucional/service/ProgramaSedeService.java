package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.ProgramaSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.ProgramaSedeResponse;
import pe.edu.upeu.lamb.institucional.entity.ProgramaSede;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ProgramaSedeMapper;
import pe.edu.upeu.lamb.institucional.repository.ProgramaSedeRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgramaSedeService {

    private final ProgramaSedeRepository programaSedeRepository;
    private final ProgramaSedeMapper programaSedeMapper;

    public List<ProgramaSedeResponse> listar() {
        return programaSedeRepository.findAll().stream()
                .map(programaSedeMapper::toResponse)
                .toList();
    }

    public ProgramaSedeResponse obtener(Long id) {
        return programaSedeMapper.toResponse(buscarOFallar(id));
    }

    public ProgramaSedeResponse crear(ProgramaSedeRequest request) {
        ProgramaSede entity = programaSedeMapper.toEntity(request);
        return programaSedeMapper.toResponse(programaSedeRepository.save(entity));
    }

    public ProgramaSedeResponse actualizar(Long id, ProgramaSedeRequest request) {
        ProgramaSede existente = buscarOFallar(id);
        ProgramaSede entity = programaSedeMapper.toEntity(request);
        return programaSedeMapper.toResponse(programaSedeRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        programaSedeRepository.delete(buscarOFallar(id));
    }

    private ProgramaSede buscarOFallar(Long id) {
        return programaSedeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProgramaSede no encontrado con id: " + id));
    }
}
