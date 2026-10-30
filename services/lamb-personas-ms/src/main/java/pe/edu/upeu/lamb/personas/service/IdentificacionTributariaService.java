package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.IdentificacionTributariaRequest;
import pe.edu.upeu.lamb.personas.dto.IdentificacionTributariaResponse;
import pe.edu.upeu.lamb.personas.entity.IdentificacionTributaria;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.IdentificacionTributariaMapper;
import pe.edu.upeu.lamb.personas.repository.IdentificacionTributariaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IdentificacionTributariaService {

    private final IdentificacionTributariaRepository identificacionTributariaRepository;
    private final IdentificacionTributariaMapper identificacionTributariaMapper;

    public List<IdentificacionTributariaResponse> listar() {
        return identificacionTributariaRepository.findAll().stream()
                .map(identificacionTributariaMapper::toResponse)
                .toList();
    }

    public IdentificacionTributariaResponse obtener(Long id) {
        return identificacionTributariaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public IdentificacionTributariaResponse crear(IdentificacionTributariaRequest request) {
        IdentificacionTributaria entity = identificacionTributariaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return identificacionTributariaMapper.toResponse(identificacionTributariaRepository.save(entity));
    }

    public IdentificacionTributariaResponse actualizar(Long id, IdentificacionTributariaRequest request) {
        IdentificacionTributaria existente = buscarOFallar(id);
        IdentificacionTributaria entity = identificacionTributariaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return identificacionTributariaMapper.toResponse(identificacionTributariaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        identificacionTributariaRepository.delete(buscarOFallar(id));
    }

    private IdentificacionTributaria buscarOFallar(Long id) {
        return identificacionTributariaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Identificacion tributaria no encontrada: " + id));
    }
}
