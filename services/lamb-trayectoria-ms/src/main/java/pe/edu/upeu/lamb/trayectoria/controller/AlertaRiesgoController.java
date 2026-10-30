package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.AlertaRiesgoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.AlertaRiesgoResponse;
import pe.edu.upeu.lamb.trayectoria.service.RiesgoAcademicoService;

import java.util.List;

@Tag(name = "Riesgo academico - Alertas", description = "CRUD 3 - Alertas tempranas de riesgo")
@RestController
@RequestMapping("/api/v1/alertas-riesgo")
@RequiredArgsConstructor
public class AlertaRiesgoController {

    private final RiesgoAcademicoService service;

    @GetMapping
    public List<AlertaRiesgoResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listarAlertas(idPersona);
    }

    @GetMapping("/{id}")
    public AlertaRiesgoResponse obtener(@PathVariable Long id) {
        return service.obtenerAlerta(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlertaRiesgoResponse crear(@Valid @RequestBody AlertaRiesgoRequest r) {
        return service.crearAlerta(r);
    }

    @PutMapping("/{id}")
    public AlertaRiesgoResponse actualizar(@PathVariable Long id, @Valid @RequestBody AlertaRiesgoRequest r) {
        return service.actualizarAlerta(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarAlerta(id);
    }
}
