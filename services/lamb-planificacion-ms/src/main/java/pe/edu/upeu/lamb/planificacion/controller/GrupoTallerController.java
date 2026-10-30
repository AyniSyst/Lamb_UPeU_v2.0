package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.GrupoTallerRequest;
import pe.edu.upeu.lamb.planificacion.dto.GrupoTallerResponse;
import pe.edu.upeu.lamb.planificacion.service.GrupoTallerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grupo de talleres", description = "CRUD 8 · Grupo de talleres compartido entre programas (reemplaza a la seccion compartida)")
@RestController
@RequestMapping("/api/v1/grupos-taller")
@RequiredArgsConstructor
public class GrupoTallerController {

    private final GrupoTallerService grupoTallerService;

    @GetMapping
    public List<GrupoTallerResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return grupoTallerService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public GrupoTallerResponse obtener(@PathVariable Long id) {
        return grupoTallerService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoTallerResponse crear(@Valid @RequestBody GrupoTallerRequest request) {
        return grupoTallerService.crear(request);
    }

    @PutMapping("/{id}")
    public GrupoTallerResponse actualizar(@PathVariable Long id, @Valid @RequestBody GrupoTallerRequest request) {
        return grupoTallerService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        grupoTallerService.eliminar(id);
    }
}
