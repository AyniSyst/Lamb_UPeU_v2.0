package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.PerfilEgresoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PerfilEgresoResponse;
import pe.edu.upeu.lamb.curriculo.service.PerfilEgresoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Perfil de egreso", description = "CRUD 30 · Perfil de egreso")
@RestController
@RequestMapping("/api/v1/perfiles-egreso")
@RequiredArgsConstructor
public class PerfilEgresoController {

    private final PerfilEgresoService perfilEgresoService;

    @GetMapping
    public List<PerfilEgresoResponse> listar(@RequestParam(required = false) Long idPlan) {
        return perfilEgresoService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public PerfilEgresoResponse obtener(@PathVariable Long id) {
        return perfilEgresoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerfilEgresoResponse crear(@Valid @RequestBody PerfilEgresoRequest request) {
        return perfilEgresoService.crear(request);
    }

    @PutMapping("/{id}")
    public PerfilEgresoResponse actualizar(@PathVariable Long id, @Valid @RequestBody PerfilEgresoRequest request) {
        return perfilEgresoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        perfilEgresoService.eliminar(id);
    }
}
