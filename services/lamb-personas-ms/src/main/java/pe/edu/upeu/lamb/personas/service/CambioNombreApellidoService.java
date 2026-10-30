package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.CambioNombreApellidoRequest;
import pe.edu.upeu.lamb.personas.dto.CambioNombreApellidoResponse;
import pe.edu.upeu.lamb.personas.entity.CambioNombreApellido;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.CambioNombreApellidoMapper;
import pe.edu.upeu.lamb.personas.repository.CambioNombreApellidoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CambioNombreApellidoService {

    private final CambioNombreApellidoRepository cambioNombreApellidoRepository;
    private final CambioNombreApellidoMapper cambioNombreApellidoMapper;

    public List<CambioNombreApellidoResponse> listar() {
        return cambioNombreApellidoRepository.findAll().stream()
                .map(cambioNombreApellidoMapper::toResponse)
                .toList();
    }

    public CambioNombreApellidoResponse obtener(Long id) {
        return cambioNombreApellidoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CambioNombreApellidoResponse crear(CambioNombreApellidoRequest request) {
        CambioNombreApellido entity = cambioNombreApellidoMapper.toEntity(request);
        entity.setFechaCambio(Instant.now());
        return cambioNombreApellidoMapper.toResponse(cambioNombreApellidoRepository.save(entity));
    }

    private CambioNombreApellido buscarOFallar(Long id) {
        return cambioNombreApellidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cambio de nombre/apellido no encontrado: " + id));
    }
}
