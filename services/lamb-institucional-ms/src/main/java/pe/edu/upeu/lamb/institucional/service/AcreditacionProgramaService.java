package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.AcreditacionProgramaRequest;
import pe.edu.upeu.lamb.institucional.dto.AcreditacionProgramaResponse;
import pe.edu.upeu.lamb.institucional.entity.AcreditacionPrograma;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.AcreditacionProgramaMapper;
import pe.edu.upeu.lamb.institucional.repository.AcreditacionProgramaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcreditacionProgramaService {

    private final AcreditacionProgramaRepository acreditacionProgramaRepository;
    private final AcreditacionProgramaMapper acreditacionProgramaMapper;

    public List<AcreditacionProgramaResponse> listar() {
        return acreditacionProgramaRepository.findAll().stream()
                .map(acreditacionProgramaMapper::toResponse)
                .toList();
    }

    public AcreditacionProgramaResponse obtener(Long id) {
        return acreditacionProgramaMapper.toResponse(buscarOFallar(id));
    }

    public AcreditacionProgramaResponse crear(AcreditacionProgramaRequest request) {
        AcreditacionPrograma entity = acreditacionProgramaMapper.toEntity(request);
        return acreditacionProgramaMapper.toResponse(acreditacionProgramaRepository.save(entity));
    }

    private AcreditacionPrograma buscarOFallar(Long id) {
        return acreditacionProgramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AcreditacionPrograma no encontrada con id: " + id));
    }
}
