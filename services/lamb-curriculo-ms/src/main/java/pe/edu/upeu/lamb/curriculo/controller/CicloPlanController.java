package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.CicloPlanRequest;
import pe.edu.upeu.lamb.curriculo.dto.CicloPlanResponse;
import pe.edu.upeu.lamb.curriculo.service.CicloPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ciclo del plan", description = "CRUD 6 · Ciclo del plan (I a X)")
@RestController
@RequestMapping("/api/v1/ciclos-plan")
@RequiredArgsConstructor
public class CicloPlanController {

    private final CicloPlanService cicloPlanService;

    @GetMapping
    public List<CicloPlanResponse> listar(@RequestParam(required = false) Long idPlan) {
        return cicloPlanService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public CicloPlanResponse obtener(@PathVariable Long id) {
        return cicloPlanService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CicloPlanResponse crear(@Valid @RequestBody CicloPlanRequest request) {
        return cicloPlanService.crear(request);
    }

    @PutMapping("/{id}")
    public CicloPlanResponse actualizar(@PathVariable Long id, @Valid @RequestBody CicloPlanRequest request) {
        return cicloPlanService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cicloPlanService.eliminar(id);
    }
}
