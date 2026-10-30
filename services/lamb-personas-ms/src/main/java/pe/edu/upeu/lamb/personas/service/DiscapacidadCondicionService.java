package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.DiscapacidadCondicionRequest;
import pe.edu.upeu.lamb.personas.dto.DiscapacidadCondicionResponse;
import pe.edu.upeu.lamb.personas.entity.DiscapacidadCondicion;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.DiscapacidadCondicionMapper;
import pe.edu.upeu.lamb.personas.repository.DiscapacidadCondicionRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscapacidadCondicionService {

    private final DiscapacidadCondicionRepository discapacidadCondicionRepository;
    private final DiscapacidadCondicionMapper discapacidadCondicionMapper;

    public List<DiscapacidadCondicionResponse> listar() {
        return discapacidadCondicionRepository.findAll().stream()
                .map(discapacidadCondicionMapper::toResponse)
                .toList();
    }

    public DiscapacidadCondicionResponse obtener(Long id) {
        return discapacidadCondicionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DiscapacidadCondicionResponse crear(DiscapacidadCondicionRequest request) {
        DiscapacidadCondicion entity = discapacidadCondicionMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return discapacidadCondicionMapper.toResponse(discapacidadCondicionRepository.save(entity));
    }

    private DiscapacidadCondicion buscarOFallar(Long id) {
        return discapacidadCondicionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discapacidad/condicion no encontrada: " + id));
    }
}
