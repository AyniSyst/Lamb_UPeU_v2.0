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
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    public ResolucionProgramaResponse actualizar(Long id, ResolucionProgramaRequest request) {
        ResolucionPrograma existente = buscarOFallar(id);
        ResolucionPrograma entity = resolucionProgramaMapper.toEntity(request);
        return resolucionProgramaMapper.toResponse(resolucionProgramaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        resolucionProgramaRepository.delete(buscarOFallar(id));
    }

    private ResolucionPrograma buscarOFallar(Long id) {
        return resolucionProgramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ResolucionPrograma no encontrada con id: " + id));
    }
}
