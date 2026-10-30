package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.CalificacionCriterioRequest;
import pe.edu.upeu.lamb.evaluacion.dto.CalificacionCriterioResponse;
import pe.edu.upeu.lamb.evaluacion.service.CalificacionCriterioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Calificacion por criterio de rubrica", description = "CRUD 8 · Calificacion por criterio de rubrica (jala el id del criterio, CRUD 5)")
@RestController
@RequestMapping("/api/v1/calificaciones-criterio")
@RequiredArgsConstructor
public class CalificacionCriterioController {

    private final CalificacionCriterioService calificacionCriterioService;

    @GetMapping
    public List<CalificacionCriterioResponse> listar(@RequestParam(required = false) Long idCriterio) {
        return calificacionCriterioService.listar(idCriterio);
    }

    @GetMapping("/{id}")
    public CalificacionCriterioResponse obtener(@PathVariable Long id) {
        return calificacionCriterioService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalificacionCriterioResponse crear(@Valid @RequestBody CalificacionCriterioRequest request) {
        return calificacionCriterioService.crear(request);
    }

    @PutMapping("/{id}")
    public CalificacionCriterioResponse actualizar(@PathVariable Long id, @Valid @RequestBody CalificacionCriterioRequest request) {
        return calificacionCriterioService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        calificacionCriterioService.eliminar(id);
    }
}
