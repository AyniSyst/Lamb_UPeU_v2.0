package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.NacionalidadRequest;
import pe.edu.upeu.lamb.personas.dto.NacionalidadResponse;
import pe.edu.upeu.lamb.personas.entity.Nacionalidad;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.NacionalidadMapper;
import pe.edu.upeu.lamb.personas.repository.NacionalidadRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NacionalidadService {

    private final NacionalidadRepository nacionalidadRepository;
    private final NacionalidadMapper nacionalidadMapper;

    public List<NacionalidadResponse> listar() {
        return nacionalidadRepository.findAll().stream()
                .map(nacionalidadMapper::toResponse)
                .toList();
    }

    public NacionalidadResponse obtener(Long id) {
        return nacionalidadMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public NacionalidadResponse crear(NacionalidadRequest request) {
        Nacionalidad entity = nacionalidadMapper.toEntity(request);
        return nacionalidadMapper.toResponse(nacionalidadRepository.save(entity));
    }

    private Nacionalidad buscarOFallar(Long id) {
        return nacionalidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nacionalidad no encontrada: " + id));
    }
}
