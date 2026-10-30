package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.CargaNoLectivaRequest;
import pe.edu.upeu.lamb.planificacion.dto.CargaNoLectivaResponse;
import pe.edu.upeu.lamb.planificacion.service.CargaNoLectivaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carga no lectiva", description = "CRUD 22 · Carga no lectiva (investigacion, tutoria, gestion)")
@RestController
@RequestMapping("/api/v1/cargas-no-lectivas")
@RequiredArgsConstructor
public class CargaNoLectivaController {

    private final CargaNoLectivaService cargaNoLectivaService;

    @GetMapping
    public List<CargaNoLectivaResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return cargaNoLectivaService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public CargaNoLectivaResponse obtener(@PathVariable Long id) {
        return cargaNoLectivaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CargaNoLectivaResponse crear(@Valid @RequestBody CargaNoLectivaRequest request) {
        return cargaNoLectivaService.crear(request);
    }

    @PutMapping("/{id}")
    public CargaNoLectivaResponse actualizar(@PathVariable Long id, @Valid @RequestBody CargaNoLectivaRequest request) {
        return cargaNoLectivaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cargaNoLectivaService.eliminar(id);
    }
}
