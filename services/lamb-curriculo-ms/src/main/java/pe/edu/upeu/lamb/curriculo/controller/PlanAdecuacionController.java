package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.PlanAdecuacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanAdecuacionResponse;
import pe.edu.upeu.lamb.curriculo.service.PlanAdecuacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan de adecuacion", description = "CRUD 40 · Plan de adecuacion / transicion entre un plan y otro")
@RestController
@RequestMapping("/api/v1/planes-adecuacion")
@RequiredArgsConstructor
public class PlanAdecuacionController {

    private final PlanAdecuacionService planAdecuacionService;

    @GetMapping
    public List<PlanAdecuacionResponse> listar(@RequestParam(required = false) Long idPlanOrigen) {
        return planAdecuacionService.listar(idPlanOrigen);
    }

    @GetMapping("/{id}")
    public PlanAdecuacionResponse obtener(@PathVariable Long id) {
        return planAdecuacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanAdecuacionResponse crear(@Valid @RequestBody PlanAdecuacionRequest request) {
        return planAdecuacionService.crear(request);
    }

    @PutMapping("/{id}")
    public PlanAdecuacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody PlanAdecuacionRequest request) {
        return planAdecuacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        planAdecuacionService.eliminar(id);
    }
}
