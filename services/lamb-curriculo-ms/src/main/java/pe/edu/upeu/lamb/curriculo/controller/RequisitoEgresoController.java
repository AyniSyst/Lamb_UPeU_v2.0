package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoEgresoRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoEgresoResponse;
import pe.edu.upeu.lamb.curriculo.service.RequisitoEgresoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Requisito de egreso", description = "CRUD 33 · Requisito de egreso (creditos minimos). Trayectoria registra si el estudiante lo cumple")
@RestController
@RequestMapping("/api/v1/requisitos-egreso")
@RequiredArgsConstructor
public class RequisitoEgresoController {

    private final RequisitoEgresoService requisitoEgresoService;

    @GetMapping
    public List<RequisitoEgresoResponse> listar(@RequestParam(required = false) Long idPlan) {
        return requisitoEgresoService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public RequisitoEgresoResponse obtener(@PathVariable Long id) {
        return requisitoEgresoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequisitoEgresoResponse crear(@Valid @RequestBody RequisitoEgresoRequest request) {
        return requisitoEgresoService.crear(request);
    }

    @PutMapping("/{id}")
    public RequisitoEgresoResponse actualizar(@PathVariable Long id, @Valid @RequestBody RequisitoEgresoRequest request) {
        return requisitoEgresoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        requisitoEgresoService.eliminar(id);
    }
}
