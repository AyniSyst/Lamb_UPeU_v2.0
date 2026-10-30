package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.ComponenteEvaluacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ComponenteEvaluacionResponse;
import pe.edu.upeu.lamb.evaluacion.service.ComponenteEvaluacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Componente de evaluacion", description = "CRUD 1 · Componente de evaluacion (evaluacion de sesiones, evaluacion de producto, examen)")
@RestController
@RequestMapping("/api/v1/componentes-evaluacion")
@RequiredArgsConstructor
public class ComponenteEvaluacionController {

    private final ComponenteEvaluacionService componenteEvaluacionService;

    @GetMapping
    public List<ComponenteEvaluacionResponse> listar() {
        return componenteEvaluacionService.listar();
    }

    @GetMapping("/{id}")
    public ComponenteEvaluacionResponse obtener(@PathVariable Long id) {
        return componenteEvaluacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComponenteEvaluacionResponse crear(@Valid @RequestBody ComponenteEvaluacionRequest request) {
        return componenteEvaluacionService.crear(request);
    }

    @PutMapping("/{id}")
    public ComponenteEvaluacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody ComponenteEvaluacionRequest request) {
        return componenteEvaluacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        componenteEvaluacionService.eliminar(id);
    }
}
