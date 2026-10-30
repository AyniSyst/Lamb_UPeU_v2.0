package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.dto.AdjudicacionRequest;
import pe.edu.upeu.lamb.admision.dto.AdjudicacionResponse;
import pe.edu.upeu.lamb.admision.service.AdjudicacionService;

import java.util.List;

@Tag(name = "Vacantes e ingreso", description = "CRUD 6 - Adjudicacion de vacantes y lista de ingresantes")
@RestController
@RequestMapping("/api/v1/adjudicaciones")
@RequiredArgsConstructor
public class AdjudicacionController {
    private final AdjudicacionService service;

    @GetMapping
    public List<AdjudicacionResponse> listar(@RequestParam(required = false) Long idModalidad) {
        return service.listar(idModalidad);
    }

    @GetMapping("/{id}")
    public AdjudicacionResponse obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdjudicacionResponse crear(@Valid @RequestBody AdjudicacionRequest r) { return service.crear(r); }

    @PutMapping("/{id}")
    public AdjudicacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody AdjudicacionRequest r) {
        return service.actualizar(id, r);
    }

    @PostMapping("/{id}/publicar")
    public AdjudicacionResponse publicar(@PathVariable Long id) { return service.publicar(id); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
