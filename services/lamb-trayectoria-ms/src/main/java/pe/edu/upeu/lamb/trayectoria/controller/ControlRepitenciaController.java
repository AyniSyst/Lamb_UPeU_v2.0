package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.ControlRepitenciaRequest;
import pe.edu.upeu.lamb.trayectoria.dto.ControlRepitenciaResponse;
import pe.edu.upeu.lamb.trayectoria.service.RiesgoAcademicoService;

import java.util.List;

@Tag(name = "Riesgo academico - Repitencia", description = "CRUD 3 - Control de cursos repetidos")
@RestController
@RequestMapping("/api/v1/controles-repitencia")
@RequiredArgsConstructor
public class ControlRepitenciaController {

    private final RiesgoAcademicoService service;

    @GetMapping
    public List<ControlRepitenciaResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listarRepitencias(idPersona);
    }

    @GetMapping("/{id}")
    public ControlRepitenciaResponse obtener(@PathVariable Long id) {
        return service.obtenerRepitencia(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ControlRepitenciaResponse crear(@Valid @RequestBody ControlRepitenciaRequest r) {
        return service.crearRepitencia(r);
    }

    @PutMapping("/{id}")
    public ControlRepitenciaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ControlRepitenciaRequest r) {
        return service.actualizarRepitencia(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarRepitencia(id);
    }
}
