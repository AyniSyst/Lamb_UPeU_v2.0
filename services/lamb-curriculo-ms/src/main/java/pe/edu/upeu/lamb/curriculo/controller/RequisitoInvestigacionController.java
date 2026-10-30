package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoInvestigacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoInvestigacionResponse;
import pe.edu.upeu.lamb.curriculo.service.RequisitoInvestigacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Requisito de investigacion", description = "CRUD 36 · Requisito de trabajo de investigacion (bachiller)")
@RestController
@RequestMapping("/api/v1/requisitos-investigacion")
@RequiredArgsConstructor
public class RequisitoInvestigacionController {

    private final RequisitoInvestigacionService requisitoInvestigacionService;

    @GetMapping
    public List<RequisitoInvestigacionResponse> listar(@RequestParam(required = false) Long idPlan) {
        return requisitoInvestigacionService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public RequisitoInvestigacionResponse obtener(@PathVariable Long id) {
        return requisitoInvestigacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequisitoInvestigacionResponse crear(@Valid @RequestBody RequisitoInvestigacionRequest request) {
        return requisitoInvestigacionService.crear(request);
    }

    @PutMapping("/{id}")
    public RequisitoInvestigacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody RequisitoInvestigacionRequest request) {
        return requisitoInvestigacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        requisitoInvestigacionService.eliminar(id);
    }
}
