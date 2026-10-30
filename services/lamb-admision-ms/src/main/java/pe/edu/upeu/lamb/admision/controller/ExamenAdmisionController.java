package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.dto.ExamenAdmisionRequest;
import pe.edu.upeu.lamb.admision.dto.ExamenAdmisionResponse;
import pe.edu.upeu.lamb.admision.service.ExamenAdmisionService;

import java.util.List;

@Tag(name = "Examen de admision", description = "CRUD 3 - Examen de admision, aulas y asignaciones")
@RestController
@RequestMapping("/api/v1/examenes-admision")
@RequiredArgsConstructor
public class ExamenAdmisionController {
    private final ExamenAdmisionService service;

    @GetMapping
    public List<ExamenAdmisionResponse> listar(@RequestParam(required = false) Long idConvocatoria) {
        return service.listar(idConvocatoria);
    }

    @GetMapping("/{id}")
    public ExamenAdmisionResponse obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExamenAdmisionResponse crear(@Valid @RequestBody ExamenAdmisionRequest r) { return service.crear(r); }

    @PutMapping("/{id}")
    public ExamenAdmisionResponse actualizar(@PathVariable Long id, @Valid @RequestBody ExamenAdmisionRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
