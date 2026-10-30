package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ConsentimientoDatosRequest;
import pe.edu.upeu.lamb.personas.dto.ConsentimientoDatosResponse;
import pe.edu.upeu.lamb.personas.entity.ConsentimientoDatos;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ConsentimientoDatosMapper;
import pe.edu.upeu.lamb.personas.repository.ConsentimientoDatosRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsentimientoDatosService {

    private final ConsentimientoDatosRepository consentimientoDatosRepository;
    private final ConsentimientoDatosMapper consentimientoDatosMapper;

    public List<ConsentimientoDatosResponse> listar() {
        return consentimientoDatosRepository.findAll().stream()
                .map(consentimientoDatosMapper::toResponse)
                .toList();
    }

    public ConsentimientoDatosResponse obtener(Long id) {
        return consentimientoDatosMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ConsentimientoDatosResponse crear(ConsentimientoDatosRequest request) {
        ConsentimientoDatos entity = consentimientoDatosMapper.toEntity(request);
        entity.setFechaConsentimiento(Instant.now());
        return consentimientoDatosMapper.toResponse(consentimientoDatosRepository.save(entity));
    }

    public ConsentimientoDatosResponse actualizar(Long id, ConsentimientoDatosRequest request) {
        ConsentimientoDatos existente = buscarOFallar(id);
        ConsentimientoDatos entity = consentimientoDatosMapper.toEntity(request);
        entity.setFechaConsentimiento(Instant.now());
        return consentimientoDatosMapper.toResponse(consentimientoDatosRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        consentimientoDatosRepository.delete(buscarOFallar(id));
    }

    private ConsentimientoDatos buscarOFallar(Long id) {
        return consentimientoDatosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consentimiento de datos no encontrado: " + id));
    }
}
