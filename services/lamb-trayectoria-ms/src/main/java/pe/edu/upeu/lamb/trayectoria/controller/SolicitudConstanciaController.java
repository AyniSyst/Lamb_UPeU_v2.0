package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.SolicitudConstanciaRequest;
import pe.edu.upeu.lamb.trayectoria.dto.SolicitudConstanciaResponse;
import pe.edu.upeu.lamb.trayectoria.service.EgresoService;

import java.util.List;

@Tag(name = "Cierre y egreso - Constancias", description = "CRUD 6 - Solicitudes y entrega de constancias")
@RestController
@RequestMapping("/api/v1/solicitudes-constancia")
@RequiredArgsConstructor
public class SolicitudConstanciaController {

    private final EgresoService service;

    @GetMapping
    public List<SolicitudConstanciaResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listarConstancias(idPersona);
    }

    @GetMapping("/{id}")
    public SolicitudConstanciaResponse obtener(@PathVariable Long id) {
        return service.obtenerConstancia(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudConstanciaResponse crear(@Valid @RequestBody SolicitudConstanciaRequest r) {
        return service.crearConstancia(r);
    }

    @PutMapping("/{id}")
    public SolicitudConstanciaResponse actualizar(@PathVariable Long id,
                                                  @Valid @RequestBody SolicitudConstanciaRequest r) {
        return service.actualizarConstancia(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarConstancia(id);
    }
}
