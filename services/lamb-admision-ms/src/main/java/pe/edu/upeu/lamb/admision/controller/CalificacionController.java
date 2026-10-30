package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.dto.CalificacionRequest;
import pe.edu.upeu.lamb.admision.dto.CalificacionResponse;
import pe.edu.upeu.lamb.admision.service.CalificacionService;

import java.util.List;

@Tag(name = "Examen y calificacion", description = "CRUD 5 - Calificaciones del examen (encargado, supervisor, clave de respuestas)")
@RestController
@RequestMapping("/api/v1/calificaciones")
@RequiredArgsConstructor
public class CalificacionController {
    private final CalificacionService service;

    @GetMapping
    public List<CalificacionResponse> listar(@RequestParam(required = false) Long idExamen) {
        return service.listar(idExamen);
    }

    @GetMapping("/{id}")
    public CalificacionResponse obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalificacionResponse crear(@Valid @RequestBody CalificacionRequest r) { return service.crear(r); }

    @PutMapping("/{id}")
    public CalificacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody CalificacionRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
