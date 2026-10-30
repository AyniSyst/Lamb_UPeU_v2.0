package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.PerfilIngresoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PerfilIngresoResponse;
import pe.edu.upeu.lamb.curriculo.service.PerfilIngresoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Perfil de ingreso", description = "CRUD 29 · Perfil de ingreso (lo consulta Admision)")
@RestController
@RequestMapping("/api/v1/perfiles-ingreso")
@RequiredArgsConstructor
public class PerfilIngresoController {

    private final PerfilIngresoService perfilIngresoService;

    @GetMapping
    public List<PerfilIngresoResponse> listar(@RequestParam(required = false) Long idPlan) {
        return perfilIngresoService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public PerfilIngresoResponse obtener(@PathVariable Long id) {
        return perfilIngresoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerfilIngresoResponse crear(@Valid @RequestBody PerfilIngresoRequest request) {
        return perfilIngresoService.crear(request);
    }

    @PutMapping("/{id}")
    public PerfilIngresoResponse actualizar(@PathVariable Long id, @Valid @RequestBody PerfilIngresoRequest request) {
        return perfilIngresoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        perfilIngresoService.eliminar(id);
    }
}
