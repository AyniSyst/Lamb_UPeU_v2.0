package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.BajaAnonimizacionRequest;
import pe.edu.upeu.lamb.personas.dto.BajaAnonimizacionResponse;
import pe.edu.upeu.lamb.personas.entity.BajaAnonimizacion;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.BajaAnonimizacionMapper;
import pe.edu.upeu.lamb.personas.repository.BajaAnonimizacionRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BajaAnonimizacionService {

    private final BajaAnonimizacionRepository bajaAnonimizacionRepository;
    private final BajaAnonimizacionMapper bajaAnonimizacionMapper;

    public List<BajaAnonimizacionResponse> listar() {
        return bajaAnonimizacionRepository.findAll().stream()
                .map(bajaAnonimizacionMapper::toResponse)
                .toList();
    }

    public BajaAnonimizacionResponse obtener(Long id) {
        return bajaAnonimizacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public BajaAnonimizacionResponse crear(BajaAnonimizacionRequest request) {
        BajaAnonimizacion entity = bajaAnonimizacionMapper.toEntity(request);
        entity.setFechaAplicacion(Instant.now());
        return bajaAnonimizacionMapper.toResponse(bajaAnonimizacionRepository.save(entity));
    }

    private BajaAnonimizacion buscarOFallar(Long id) {
        return bajaAnonimizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baja/anonimizacion no encontrada: " + id));
    }
}
