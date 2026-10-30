package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.ResolucionProgramaRequest;
import pe.edu.upeu.lamb.institucional.dto.ResolucionProgramaResponse;
import pe.edu.upeu.lamb.institucional.entity.ResolucionPrograma;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ResolucionProgramaMapper;
import pe.edu.upeu.lamb.institucional.repository.ResolucionProgramaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolucionProgramaService {

    private final ResolucionProgramaRepository resolucionProgramaRepository;
    private final ResolucionProgramaMapper resolucionProgramaMapper;

    public List<ResolucionProgramaResponse> listar() {
        return resolucionProgramaRepository.findAll().stream()
                .map(resolucionProgramaMapper::toResponse)
                .toList();
    }

    public ResolucionProgramaResponse obtener(Long id) {
        return resolucionProgramaMapper.toResponse(buscarOFallar(id));
    }

    public ResolucionProgramaResponse crear(ResolucionProgramaRequest request) {
        ResolucionPrograma entity = resolucionProgramaMapper.toEntity(request);
        return resolucionProgramaMapper.toResponse(resolucionProgramaRepository.save(entity));
    }

    private ResolucionPrograma buscarOFallar(Long id) {
        return resolucionProgramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ResolucionPrograma no encontrada con id: " + id));
    }
}
