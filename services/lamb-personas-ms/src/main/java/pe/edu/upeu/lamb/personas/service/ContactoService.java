package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ContactoRequest;
import pe.edu.upeu.lamb.personas.dto.ContactoResponse;
import pe.edu.upeu.lamb.personas.entity.Contacto;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ContactoMapper;
import pe.edu.upeu.lamb.personas.repository.ContactoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoService {

    private final ContactoRepository contactoRepository;
    private final ContactoMapper contactoMapper;

    public List<ContactoResponse> listar() {
        return contactoRepository.findAll().stream()
                .map(contactoMapper::toResponse)
                .toList();
    }

    public ContactoResponse obtener(Long id) {
        return contactoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ContactoResponse crear(ContactoRequest request) {
        Contacto entity = contactoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return contactoMapper.toResponse(contactoRepository.save(entity));
    }

    private Contacto buscarOFallar(Long id) {
        return contactoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacto no encontrado: " + id));
    }
}
