package pe.edu.upeu.lamb.personas.controller;

import pe.edu.upeu.lamb.personas.dto.PersonaRequest;
import pe.edu.upeu.lamb.personas.dto.PersonaResponse;
import pe.edu.upeu.lamb.personas.service.PersonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping
    public List<PersonaResponse> listar() {
        return personaService.listar();
    }

    @GetMapping("/{id}")
    public PersonaResponse obtener(@PathVariable Long id) {
        return personaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaResponse crear(@Valid @RequestBody PersonaRequest request) {
        return personaService.crear(request);
    }

    @PutMapping("/{id}")
    public PersonaResponse actualizar(@PathVariable Long id, @Valid @RequestBody PersonaRequest request) {
        return personaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        personaService.eliminar(id);
    }
}
