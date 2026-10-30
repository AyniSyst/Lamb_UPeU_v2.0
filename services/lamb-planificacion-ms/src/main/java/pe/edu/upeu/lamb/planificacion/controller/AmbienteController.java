package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.AmbienteRequest;
import pe.edu.upeu.lamb.planificacion.dto.AmbienteResponse;
import pe.edu.upeu.lamb.planificacion.service.AmbienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Aula / ambiente", description = "CRUD 15 · Aula / ambiente (incluye laboratorios y locales de examen de Admision)")
@RestController
@RequestMapping("/api/v1/ambientes")
@RequiredArgsConstructor
public class AmbienteController {

    private final AmbienteService ambienteService;

    @GetMapping
    public List<AmbienteResponse> listar(@RequestParam(required = false) Long idPabellon) {
        return ambienteService.listar(idPabellon);
    }

    @GetMapping("/{id}")
    public AmbienteResponse obtener(@PathVariable Long id) {
        return ambienteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AmbienteResponse crear(@Valid @RequestBody AmbienteRequest request) {
        return ambienteService.crear(request);
    }

    @PutMapping("/{id}")
    public AmbienteResponse actualizar(@PathVariable Long id, @Valid @RequestBody AmbienteRequest request) {
        return ambienteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        ambienteService.eliminar(id);
    }
}
