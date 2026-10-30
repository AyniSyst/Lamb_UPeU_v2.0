package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ResponsablePagoRequest;
import pe.edu.upeu.lamb.personas.dto.ResponsablePagoResponse;
import pe.edu.upeu.lamb.personas.entity.ResponsablePago;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ResponsablePagoMapper;
import pe.edu.upeu.lamb.personas.repository.ResponsablePagoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsablePagoService {

    private final ResponsablePagoRepository responsablePagoRepository;
    private final ResponsablePagoMapper responsablePagoMapper;

    public List<ResponsablePagoResponse> listar() {
        return responsablePagoRepository.findAll().stream()
                .map(responsablePagoMapper::toResponse)
                .toList();
    }

    public ResponsablePagoResponse obtener(Long id) {
        return responsablePagoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ResponsablePagoResponse crear(ResponsablePagoRequest request) {
        ResponsablePago entity = responsablePagoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return responsablePagoMapper.toResponse(responsablePagoRepository.save(entity));
    }

    public ResponsablePagoResponse actualizar(Long id, ResponsablePagoRequest request) {
        ResponsablePago existente = buscarOFallar(id);
        ResponsablePago entity = responsablePagoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return responsablePagoMapper.toResponse(responsablePagoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        responsablePagoRepository.delete(buscarOFallar(id));
    }

    private ResponsablePago buscarOFallar(Long id) {
        return responsablePagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Responsable de pago no encontrado: " + id));
    }
}
