package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.PlazoAperturaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.PlazoAperturaResponse;
import pe.edu.upeu.lamb.evaluacion.service.PlazoAperturaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plazo de apertura habilitado", description = "CRUD 17 · Plazo de apertura habilitado (grupo, docente, fecha de inicio y de cierre)")
@RestController
@RequestMapping("/api/v1/plazos-apertura")
@RequiredArgsConstructor
public class PlazoAperturaController {

    private final PlazoAperturaService plazoAperturaService;

    @GetMapping
    public List<PlazoAperturaResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return plazoAperturaService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public PlazoAperturaResponse obtener(@PathVariable Long id) {
        return plazoAperturaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlazoAperturaResponse crear(@Valid @RequestBody PlazoAperturaRequest request) {
        return plazoAperturaService.crear(request);
    }

    @PutMapping("/{id}")
    public PlazoAperturaResponse actualizar(@PathVariable Long id, @Valid @RequestBody PlazoAperturaRequest request) {
        return plazoAperturaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        plazoAperturaService.eliminar(id);
    }
}
