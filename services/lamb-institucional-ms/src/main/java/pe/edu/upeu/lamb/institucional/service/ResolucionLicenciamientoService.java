package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.ResolucionLicenciamientoRequest;
import pe.edu.upeu.lamb.institucional.dto.ResolucionLicenciamientoResponse;
import pe.edu.upeu.lamb.institucional.entity.ResolucionLicenciamiento;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ResolucionLicenciamientoMapper;
import pe.edu.upeu.lamb.institucional.repository.ResolucionLicenciamientoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolucionLicenciamientoService {

    private final ResolucionLicenciamientoRepository resolucionLicenciamientoRepository;
    private final ResolucionLicenciamientoMapper resolucionLicenciamientoMapper;

    public List<ResolucionLicenciamientoResponse> listar() {
        return resolucionLicenciamientoRepository.findAll().stream()
                .map(resolucionLicenciamientoMapper::toResponse)
                .toList();
    }

    public ResolucionLicenciamientoResponse obtener(Long id) {
        return resolucionLicenciamientoMapper.toResponse(buscarOFallar(id));
    }

    public ResolucionLicenciamientoResponse crear(ResolucionLicenciamientoRequest request) {
        ResolucionLicenciamiento entity = resolucionLicenciamientoMapper.toEntity(request);
        return resolucionLicenciamientoMapper.toResponse(resolucionLicenciamientoRepository.save(entity));
    }

    private ResolucionLicenciamiento buscarOFallar(Long id) {
        return resolucionLicenciamientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resolucion de licenciamiento no encontrada: " + id));
    }
}
