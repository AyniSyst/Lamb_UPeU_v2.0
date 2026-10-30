package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.CodigoInstitucionalRequest;
import pe.edu.upeu.lamb.personas.dto.CodigoInstitucionalResponse;
import pe.edu.upeu.lamb.personas.entity.CodigoInstitucional;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.CodigoInstitucionalMapper;
import pe.edu.upeu.lamb.personas.repository.CodigoInstitucionalRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodigoInstitucionalService {

    private final CodigoInstitucionalRepository codigoInstitucionalRepository;
    private final CodigoInstitucionalMapper codigoInstitucionalMapper;

    public List<CodigoInstitucionalResponse> listar() {
        return codigoInstitucionalRepository.findAll().stream()
                .map(codigoInstitucionalMapper::toResponse)
                .toList();
    }

    public CodigoInstitucionalResponse obtener(Long id) {
        return codigoInstitucionalMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CodigoInstitucionalResponse crear(CodigoInstitucionalRequest request) {
        CodigoInstitucional entity = codigoInstitucionalMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return codigoInstitucionalMapper.toResponse(codigoInstitucionalRepository.save(entity));
    }

    private CodigoInstitucional buscarOFallar(Long id) {
        return codigoInstitucionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Codigo institucional no encontrado: " + id));
    }
}
