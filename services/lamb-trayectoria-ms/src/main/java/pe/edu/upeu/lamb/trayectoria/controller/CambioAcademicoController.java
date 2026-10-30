package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.CambioAcademicoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.CambioAcademicoResponse;
import pe.edu.upeu.lamb.trayectoria.service.EstudiantePlanService;

import java.util.List;

@Tag(name = "Estudiante en el plan", description = "CRUD 1 - Cambios de plan, escuela, sede y estado de trayectoria")
@RestController
@RequestMapping("/api/v1/cambios-academicos")
@RequiredArgsConstructor
public class CambioAcademicoController {

    private final EstudiantePlanService service;

    @GetMapping
    public List<CambioAcademicoResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listar(idPersona);
    }

    @GetMapping("/{id}")
    public CambioAcademicoResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CambioAcademicoResponse crear(@Valid @RequestBody CambioAcademicoRequest r) {
        return service.crear(r);
    }

    @PutMapping("/{id}")
    public CambioAcademicoResponse actualizar(@PathVariable Long id, @Valid @RequestBody CambioAcademicoRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
