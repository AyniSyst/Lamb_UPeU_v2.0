package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.SituacionCierreRequest;
import pe.edu.upeu.lamb.trayectoria.dto.SituacionCierreResponse;
import pe.edu.upeu.lamb.trayectoria.service.EgresoService;

import java.util.List;

@Tag(name = "Cierre y egreso - Situacion academica", description = "CRUD 6 - Cierre de periodo y situacion academica")
@RestController
@RequestMapping("/api/v1/situaciones-cierre")
@RequiredArgsConstructor
public class SituacionCierreController {

    private final EgresoService service;

    @GetMapping
    public List<SituacionCierreResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listarSituaciones(idPersona);
    }

    @GetMapping("/{id}")
    public SituacionCierreResponse obtener(@PathVariable Long id) {
        return service.obtenerSituacion(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SituacionCierreResponse crear(@Valid @RequestBody SituacionCierreRequest r) {
        return service.crearSituacion(r);
    }

    @PutMapping("/{id}")
    public SituacionCierreResponse actualizar(@PathVariable Long id, @Valid @RequestBody SituacionCierreRequest r) {
        return service.actualizarSituacion(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarSituacion(id);
    }
}
