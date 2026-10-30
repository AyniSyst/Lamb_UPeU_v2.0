package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.ResolucionPlanRequest;
import pe.edu.upeu.lamb.curriculo.dto.ResolucionPlanResponse;
import pe.edu.upeu.lamb.curriculo.service.ResolucionPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Resolucion del plan", description = "CRUD 9 · Resolucion de aprobacion del plan")
@RestController
@RequestMapping("/api/v1/resoluciones-plan")
@RequiredArgsConstructor
public class ResolucionPlanController {

    private final ResolucionPlanService resolucionPlanService;

    @GetMapping
    public List<ResolucionPlanResponse> listar(@RequestParam(required = false) Long idPlan) {
        return resolucionPlanService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public ResolucionPlanResponse obtener(@PathVariable Long id) {
        return resolucionPlanService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResolucionPlanResponse crear(@Valid @RequestBody ResolucionPlanRequest request) {
        return resolucionPlanService.crear(request);
    }

    @PutMapping("/{id}")
    public ResolucionPlanResponse actualizar(@PathVariable Long id, @Valid @RequestBody ResolucionPlanRequest request) {
        return resolucionPlanService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        resolucionPlanService.eliminar(id);
    }
}
