package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.DisponibilidadDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.DisponibilidadDocenteResponse;
import pe.edu.upeu.lamb.planificacion.service.DisponibilidadDocenteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Disponibilidad horaria del docente", description = "CRUD 23 · Disponibilidad horaria del docente")
@RestController
@RequestMapping("/api/v1/disponibilidades-docente")
@RequiredArgsConstructor
public class DisponibilidadDocenteController {

    private final DisponibilidadDocenteService disponibilidadDocenteService;

    @GetMapping
    public List<DisponibilidadDocenteResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return disponibilidadDocenteService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public DisponibilidadDocenteResponse obtener(@PathVariable Long id) {
        return disponibilidadDocenteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DisponibilidadDocenteResponse crear(@Valid @RequestBody DisponibilidadDocenteRequest request) {
        return disponibilidadDocenteService.crear(request);
    }

    @PutMapping("/{id}")
    public DisponibilidadDocenteResponse actualizar(@PathVariable Long id, @Valid @RequestBody DisponibilidadDocenteRequest request) {
        return disponibilidadDocenteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        disponibilidadDocenteService.eliminar(id);
    }
}
