package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.SesionTutoriaRequest;
import pe.edu.upeu.lamb.trayectoria.dto.SesionTutoriaResponse;
import pe.edu.upeu.lamb.trayectoria.exception.BusinessRuleException;
import pe.edu.upeu.lamb.trayectoria.service.TutoriaService;

@Tag(name = "Tutoria - Sesiones", description = "CRUD 4 - Sesiones de tutoria por identificador")
@RestController
@RequestMapping("/api/v1/sesiones-tutoria")
@RequiredArgsConstructor
public class SesionTutoriaController {

    private final TutoriaService service;

    @GetMapping("/{id}")
    public SesionTutoriaResponse obtener(@PathVariable Long id) {
        return service.obtenerSesion(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SesionTutoriaResponse crear(@Valid @RequestBody SesionTutoriaRequest r) {
        if (r.idTutoria() == null) {
            throw new BusinessRuleException("El campo idTutoria es obligatorio");
        }
        return service.crearSesion(r.idTutoria(), r);
    }

    @PutMapping("/{id}")
    public SesionTutoriaResponse actualizar(@PathVariable Long id, @Valid @RequestBody SesionTutoriaRequest r) {
        return service.actualizarSesion(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarSesion(id);
    }
}
