package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ContactoEmergenciaRequest;
import pe.edu.upeu.lamb.personas.dto.ContactoEmergenciaResponse;
import pe.edu.upeu.lamb.personas.entity.ContactoEmergencia;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ContactoEmergenciaMapper;
import pe.edu.upeu.lamb.personas.repository.ContactoEmergenciaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoEmergenciaService {

    private final ContactoEmergenciaRepository contactoEmergenciaRepository;
    private final ContactoEmergenciaMapper contactoEmergenciaMapper;

    public List<ContactoEmergenciaResponse> listar() {
        return contactoEmergenciaRepository.findAll().stream()
                .map(contactoEmergenciaMapper::toResponse)
                .toList();
    }

    public ContactoEmergenciaResponse obtener(Long id) {
        return contactoEmergenciaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ContactoEmergenciaResponse crear(ContactoEmergenciaRequest request) {
        ContactoEmergencia entity = contactoEmergenciaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return contactoEmergenciaMapper.toResponse(contactoEmergenciaRepository.save(entity));
    }

    public ContactoEmergenciaResponse actualizar(Long id, ContactoEmergenciaRequest request) {
        ContactoEmergencia existente = buscarOFallar(id);
        ContactoEmergencia entity = contactoEmergenciaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return contactoEmergenciaMapper.toResponse(contactoEmergenciaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        contactoEmergenciaRepository.delete(buscarOFallar(id));
    }

    private ContactoEmergencia buscarOFallar(Long id) {
        return contactoEmergenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacto de emergencia no encontrado: " + id));
    }
}
