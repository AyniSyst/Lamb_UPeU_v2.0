package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.CronogramaSesionRequest;
import pe.edu.upeu.lamb.planificacion.dto.CronogramaSesionResponse;
import pe.edu.upeu.lamb.planificacion.service.CronogramaSesionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cronograma de sesiones", description = "CRUD 27 · Cronograma de sesiones (programada, dictada, reprogramada)")
@RestController
@RequestMapping("/api/v1/cronogramas-sesion")
@RequiredArgsConstructor
public class CronogramaSesionController {

    private final CronogramaSesionService cronogramaSesionService;

    @GetMapping
    public List<CronogramaSesionResponse> listar(@RequestParam(required = false) Long idSesion) {
        return cronogramaSesionService.listar(idSesion);
    }

    @GetMapping("/{id}")
    public CronogramaSesionResponse obtener(@PathVariable Long id) {
        return cronogramaSesionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CronogramaSesionResponse crear(@Valid @RequestBody CronogramaSesionRequest request) {
        return cronogramaSesionService.crear(request);
    }

    @PutMapping("/{id}")
    public CronogramaSesionResponse actualizar(@PathVariable Long id, @Valid @RequestBody CronogramaSesionRequest request) {
        return cronogramaSesionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cronogramaSesionService.eliminar(id);
    }
}
