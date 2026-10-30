package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.InformeTrayectoriaRequest;
import pe.edu.upeu.lamb.trayectoria.dto.InformeTrayectoriaResponse;
import pe.edu.upeu.lamb.trayectoria.service.EgresoService;

import java.util.List;

@Tag(name = "Cierre y egreso - Informes", description = "CRUD 6 - Informes de trayectoria para el apoderado")
@RestController
@RequestMapping("/api/v1/informes-trayectoria")
@RequiredArgsConstructor
public class InformeTrayectoriaController {

    private final EgresoService service;

    @GetMapping
    public List<InformeTrayectoriaResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listarInformes(idPersona);
    }

    @GetMapping("/{id}")
    public InformeTrayectoriaResponse obtener(@PathVariable Long id) {
        return service.obtenerInforme(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformeTrayectoriaResponse crear(@Valid @RequestBody InformeTrayectoriaRequest r) {
        return service.crearInforme(r);
    }

    @PutMapping("/{id}")
    public InformeTrayectoriaResponse actualizar(@PathVariable Long id, @Valid @RequestBody InformeTrayectoriaRequest r) {
        return service.actualizarInforme(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarInforme(id);
    }
}
