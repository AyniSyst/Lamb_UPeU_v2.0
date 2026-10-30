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
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.TipoDocumentoPeruResponse;
import pe.edu.upeu.lamb.personas.entity.TipoDocumentoPeru;
import pe.edu.upeu.lamb.personas.exception.BusinessRuleException;
import java.util.Arrays;

@Service
@Transactional
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

    public List<TipoDocumentoPeruResponse> tiposDocumento() {
        return Arrays.stream(TipoDocumentoPeru.values())
                .map(t -> new TipoDocumentoPeruResponse(t.name(), t.getNombre(), t.getPatron(), t.getDescripcionFormato()))
                .toList();
    }

    public PersonaResponse crear(PersonaRequest request) {
        validar(request, null);
        Persona persona = personaMapper.toEntity(request);
        return personaMapper.toResponse(personaRepository.save(persona));
    }

    public PersonaResponse actualizar(Long id, PersonaRequest request) {
        Persona persona = buscarOFallar(id);
        validar(request, id);
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

    /** Formato del numero segun el tipo de documento peruano y unicidad de documento y email. */
    private void validar(PersonaRequest request, Long id) {
        TipoDocumentoPeru tipo = TipoDocumentoPeru.deCodigo(request.getTipoDocumento())
                .orElseThrow(() -> new BusinessRuleException("Tipo de documento no valido: " + request.getTipoDocumento()
                        + ". Use uno de: " + Arrays.toString(TipoDocumentoPeru.values())));
        request.setTipoDocumento(tipo.name());
        String numero = request.getNumeroDocumento().trim().toUpperCase();
        request.setNumeroDocumento(numero);
        if (!tipo.esValido(numero)) {
            throw new BusinessRuleException("Numero de " + tipo.name() + " no valido: debe tener " + tipo.getDescripcionFormato());
        }
        boolean documentoDuplicado = id == null
                ? personaRepository.existsByNumeroDocumento(numero)
                : personaRepository.existsByNumeroDocumentoAndIdNot(numero, id);
        if (documentoDuplicado) {
            throw new BusinessRuleException("Ya existe una persona con el documento " + tipo.name() + " " + numero);
        }
        boolean emailDuplicado = id == null
                ? personaRepository.existsByEmailIgnoreCase(request.getEmail())
                : personaRepository.existsByEmailIgnoreCaseAndIdNot(request.getEmail(), id);
        if (emailDuplicado) {
            throw new BusinessRuleException("Ya existe una persona con el email " + request.getEmail());
        }
    }

    private Persona buscarOFallar(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada: " + id));
    }
}
