package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.ReglaAlertaRequest;
import pe.edu.upeu.lamb.trayectoria.dto.ReglaAlertaResponse;
import pe.edu.upeu.lamb.trayectoria.service.RiesgoAcademicoService;

import java.util.List;

@Tag(name = "Riesgo academico - Reglas", description = "CRUD 3 - Reglas de generacion de alertas")
@RestController
@RequestMapping("/api/v1/reglas-alerta")
@RequiredArgsConstructor
public class ReglaAlertaController {

    private final RiesgoAcademicoService service;

    @GetMapping
    public List<ReglaAlertaResponse> listar() {
        return service.listarReglas();
    }

    @GetMapping("/{id}")
    public ReglaAlertaResponse obtener(@PathVariable Long id) {
        return service.obtenerRegla(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReglaAlertaResponse crear(@Valid @RequestBody ReglaAlertaRequest r) {
        return service.crearRegla(r);
    }

    @PutMapping("/{id}")
    public ReglaAlertaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ReglaAlertaRequest r) {
        return service.actualizarRegla(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarRegla(id);
    }
}
