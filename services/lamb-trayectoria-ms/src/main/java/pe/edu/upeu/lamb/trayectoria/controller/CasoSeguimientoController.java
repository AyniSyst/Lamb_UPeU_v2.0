package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.CasoSeguimientoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.CasoSeguimientoResponse;
import pe.edu.upeu.lamb.trayectoria.service.RiesgoAcademicoService;

import java.util.List;

@Tag(name = "Riesgo academico - Seguimiento", description = "CRUD 3 - Casos de acompanamiento")
@RestController
@RequestMapping("/api/v1/casos-seguimiento")
@RequiredArgsConstructor
public class CasoSeguimientoController {

    private final RiesgoAcademicoService service;

    @GetMapping
    public List<CasoSeguimientoResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listarCasos(idPersona);
    }

    @GetMapping("/{id}")
    public CasoSeguimientoResponse obtener(@PathVariable Long id) {
        return service.obtenerCaso(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CasoSeguimientoResponse crear(@Valid @RequestBody CasoSeguimientoRequest r) {
        return service.crearCaso(r);
    }

    @PutMapping("/{id}")
    public CasoSeguimientoResponse actualizar(@PathVariable Long id, @Valid @RequestBody CasoSeguimientoRequest r) {
        return service.actualizarCaso(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarCaso(id);
    }
}
