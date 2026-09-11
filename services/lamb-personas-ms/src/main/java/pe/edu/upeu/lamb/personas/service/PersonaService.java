package pe.edu.upeu.lamb.personas.service;

import pe.edu.upeu.lamb.personas.dto.PersonaRequest;
import pe.edu.upeu.lamb.personas.dto.PersonaResponse;
import pe.edu.upeu.lamb.personas.entity.Persona;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.PersonaMapper;
import pe.edu.upeu.lamb.personas.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    public List<PersonaResponse> listar() {
        return personaRepository.findAll().stream()
                .map(personaMapper::toResponse)
                .toList();
    }

    public PersonaResponse obtener(Long id) {
        return personaMapper.toResponse(buscarOFallar(id));
    }

    public PersonaResponse crear(PersonaRequest request) {
        Persona persona = personaMapper.toEntity(request);
        return personaMapper.toResponse(personaRepository.save(persona));
    }

    public PersonaResponse actualizar(Long id, PersonaRequest request) {
        Persona persona = buscarOFallar(id);
        persona.setTipoDocumento(request.getTipoDocumento());
        persona.setNumeroDocumento(request.getNumeroDocumento());
        persona.setNombres(request.getNombres());
        persona.setApellidos(request.getApellidos());
        persona.setEmail(request.getEmail());
        persona.setTelefono(request.getTelefono());
        persona.setFechaNacimiento(request.getFechaNacimiento());
        persona.setTipo(request.getTipo());
        return personaMapper.toResponse(personaRepository.save(persona));
    }

    public void eliminar(Long id) {
        personaRepository.delete(buscarOFallar(id));
    }

    private Persona buscarOFallar(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada: " + id));
    }
}
