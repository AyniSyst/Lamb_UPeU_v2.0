package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ExpedienteLegajoRequest;
import pe.edu.upeu.lamb.personas.dto.ExpedienteLegajoResponse;
import pe.edu.upeu.lamb.personas.entity.ExpedienteLegajo;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ExpedienteLegajoMapper;
import pe.edu.upeu.lamb.personas.repository.ExpedienteLegajoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpedienteLegajoService {

    private final ExpedienteLegajoRepository expedienteLegajoRepository;
    private final ExpedienteLegajoMapper expedienteLegajoMapper;

    public List<ExpedienteLegajoResponse> listar() {
        return expedienteLegajoRepository.findAll().stream()
                .map(expedienteLegajoMapper::toResponse)
                .toList();
    }

    public ExpedienteLegajoResponse obtener(Long id) {
        return expedienteLegajoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ExpedienteLegajoResponse crear(ExpedienteLegajoRequest request) {
        ExpedienteLegajo entity = expedienteLegajoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return expedienteLegajoMapper.toResponse(expedienteLegajoRepository.save(entity));
    }

    private ExpedienteLegajo buscarOFallar(Long id) {
        return expedienteLegajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expediente/legajo no encontrado: " + id));
    }
}
