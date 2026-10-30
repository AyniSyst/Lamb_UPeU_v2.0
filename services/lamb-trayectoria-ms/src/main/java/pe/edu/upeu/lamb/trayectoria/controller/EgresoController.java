package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.EgresoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.EgresoResponse;
import pe.edu.upeu.lamb.trayectoria.service.EgresoService;

import java.util.List;

@Tag(name = "Cierre y egreso - Egresos", description = "CRUD 6 - Registro de egreso y validacion de requisitos")
@RestController
@RequestMapping("/api/v1/egresos")
@RequiredArgsConstructor
public class EgresoController {

    private final EgresoService service;

    @GetMapping
    public List<EgresoResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listarEgresos(idPersona);
    }

    @GetMapping("/{id}")
    public EgresoResponse obtener(@PathVariable Long id) {
        return service.obtenerEgreso(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EgresoResponse crear(@Valid @RequestBody EgresoRequest r) {
        return service.crearEgreso(r);
    }

    @PutMapping("/{id}")
    public EgresoResponse actualizar(@PathVariable Long id, @Valid @RequestBody EgresoRequest r) {
        return service.actualizarEgreso(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarEgreso(id);
    }
}
