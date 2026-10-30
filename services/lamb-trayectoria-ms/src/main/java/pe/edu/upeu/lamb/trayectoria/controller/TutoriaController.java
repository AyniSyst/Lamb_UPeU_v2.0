package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.SesionTutoriaRequest;
import pe.edu.upeu.lamb.trayectoria.dto.SesionTutoriaResponse;
import pe.edu.upeu.lamb.trayectoria.dto.TutoriaRequest;
import pe.edu.upeu.lamb.trayectoria.dto.TutoriaResponse;
import pe.edu.upeu.lamb.trayectoria.service.TutoriaService;

import java.util.List;

@Tag(name = "Tutoria y bienestar", description = "CRUD 4 - Tutorias y sesiones de acompanamiento")
@RestController
@RequestMapping("/api/v1/tutorias")
@RequiredArgsConstructor
public class TutoriaController {

    private final TutoriaService service;

    @GetMapping
    public List<TutoriaResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listarTutorias(idPersona);
    }

    @GetMapping("/{id}")
    public TutoriaResponse obtener(@PathVariable Long id) {
        return service.obtenerTutoria(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TutoriaResponse crear(@Valid @RequestBody TutoriaRequest r) {
        return service.crearTutoria(r);
    }

    @PutMapping("/{id}")
    public TutoriaResponse actualizar(@PathVariable Long id, @Valid @RequestBody TutoriaRequest r) {
        return service.actualizarTutoria(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarTutoria(id);
    }

    @GetMapping("/{idTutoria}/sesiones")
    public List<SesionTutoriaResponse> listarSesiones(@PathVariable Long idTutoria) {
        return service.listarSesiones(idTutoria);
    }

    @PostMapping("/{idTutoria}/sesiones")
    @ResponseStatus(HttpStatus.CREATED)
    public SesionTutoriaResponse crearSesion(@PathVariable Long idTutoria,
                                             @Valid @RequestBody SesionTutoriaRequest r) {
        return service.crearSesion(idTutoria, r);
    }
}
