package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.GrupoActualizarRequest;
import pe.edu.upeu.lamb.planificacion.dto.GrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.GrupoResponse;
import pe.edu.upeu.lamb.planificacion.service.GrupoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grupo", description = "CRUD 7 · Grupo (operacion transaccional: cabecera + horarios + docentes)")
@RestController
@RequestMapping("/api/v1/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoService grupoService;

    @GetMapping
    public List<GrupoResponse> listar(@RequestParam(required = false) Long idOferta) {
        return grupoService.listar(idOferta);
    }

    @GetMapping("/{id}")
    public GrupoResponse obtener(@PathVariable Long id) {
        return grupoService.obtener(id);
    }

    @Operation(summary = "Abre un grupo con sus horarios y docentes en una sola transaccion (todo o nada)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoResponse crear(@Valid @RequestBody GrupoRequest request) {
        return grupoService.crear(request);
    }

    @PostMapping("/{id}/revalidar")
    public GrupoResponse revalidar(@PathVariable Long id) {
        return grupoService.revalidar(id);
    }

    @PutMapping("/{id}")
    public GrupoResponse actualizar(@PathVariable Long id, @Valid @RequestBody GrupoActualizarRequest request) {
        return grupoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        grupoService.eliminar(id);
    }
}
