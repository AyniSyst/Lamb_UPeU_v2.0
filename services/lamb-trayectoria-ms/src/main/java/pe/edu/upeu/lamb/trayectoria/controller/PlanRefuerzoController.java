package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.PlanRefuerzoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.PlanRefuerzoResponse;
import pe.edu.upeu.lamb.trayectoria.service.RiesgoAcademicoService;

import java.util.List;

@Tag(name = "Riesgo academico - Refuerzo", description = "CRUD 3 - Planes de refuerzo y cursos asignados")
@RestController
@RequestMapping("/api/v1/planes-refuerzo")
@RequiredArgsConstructor
public class PlanRefuerzoController {

    private final RiesgoAcademicoService service;

    @GetMapping
    public List<PlanRefuerzoResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listarPlanes(idPersona);
    }

    @GetMapping("/{id}")
    public PlanRefuerzoResponse obtener(@PathVariable Long id) {
        return service.obtenerPlan(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanRefuerzoResponse crear(@Valid @RequestBody PlanRefuerzoRequest r) {
        return service.crearPlan(r);
    }

    @PutMapping("/{id}")
    public PlanRefuerzoResponse actualizar(@PathVariable Long id, @Valid @RequestBody PlanRefuerzoRequest r) {
        return service.actualizarPlan(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarPlan(id);
    }
}
