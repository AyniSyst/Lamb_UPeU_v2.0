package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.EquivalenciaPlanRequest;
import pe.edu.upeu.lamb.curriculo.dto.EquivalenciaPlanResponse;
import pe.edu.upeu.lamb.curriculo.service.EquivalenciaPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Equivalencia entre planes", description = "CRUD 39 · Equivalencia entre cursos de dos planes")
@RestController
@RequestMapping("/api/v1/equivalencias-plan")
@RequiredArgsConstructor
public class EquivalenciaPlanController {

    private final EquivalenciaPlanService equivalenciaPlanService;

    @GetMapping
    public List<EquivalenciaPlanResponse> listar(@RequestParam(required = false) Long idPlanCursoOrigen) {
        return equivalenciaPlanService.listar(idPlanCursoOrigen);
    }

    @GetMapping("/{id}")
    public EquivalenciaPlanResponse obtener(@PathVariable Long id) {
        return equivalenciaPlanService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquivalenciaPlanResponse crear(@Valid @RequestBody EquivalenciaPlanRequest request) {
        return equivalenciaPlanService.crear(request);
    }

    @PutMapping("/{id}")
    public EquivalenciaPlanResponse actualizar(@PathVariable Long id, @Valid @RequestBody EquivalenciaPlanRequest request) {
        return equivalenciaPlanService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        equivalenciaPlanService.eliminar(id);
    }
}
