package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.AsignacionDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.AsignacionDocenteResponse;
import pe.edu.upeu.lamb.planificacion.service.AsignacionDocenteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Asignacion docente a grupo", description = "CRUD 20 · Asignacion docente a grupo (detalle Grupo x Docente)")
@RestController
@RequestMapping("/api/v1/asignaciones-docente")
@RequiredArgsConstructor
public class AsignacionDocenteController {

    private final AsignacionDocenteService asignacionDocenteService;

    @GetMapping
    public List<AsignacionDocenteResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return asignacionDocenteService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public AsignacionDocenteResponse obtener(@PathVariable Long id) {
        return asignacionDocenteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AsignacionDocenteResponse crear(@Valid @RequestBody AsignacionDocenteRequest request) {
        return asignacionDocenteService.crear(request);
    }

    @PutMapping("/{id}")
    public AsignacionDocenteResponse actualizar(@PathVariable Long id, @Valid @RequestBody AsignacionDocenteRequest request) {
        return asignacionDocenteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        asignacionDocenteService.eliminar(id);
    }
}
