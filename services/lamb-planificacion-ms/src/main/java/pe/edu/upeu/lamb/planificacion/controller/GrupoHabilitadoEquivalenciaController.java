package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.GrupoHabilitadoEquivalenciaRequest;
import pe.edu.upeu.lamb.planificacion.dto.GrupoHabilitadoEquivalenciaResponse;
import pe.edu.upeu.lamb.planificacion.service.GrupoHabilitadoEquivalenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grupo habilitado por equivalencia", description = "CRUD 34 · Grupo de otra carrera habilitado por equivalencia y con cuantos cupos")
@RestController
@RequestMapping("/api/v1/grupos-habilitados-equivalencia")
@RequiredArgsConstructor
public class GrupoHabilitadoEquivalenciaController {

    private final GrupoHabilitadoEquivalenciaService grupoHabilitadoEquivalenciaService;

    @GetMapping
    public List<GrupoHabilitadoEquivalenciaResponse> listar(@RequestParam(required = false) Long idEquivalencia) {
        return grupoHabilitadoEquivalenciaService.listar(idEquivalencia);
    }

    @GetMapping("/{id}")
    public GrupoHabilitadoEquivalenciaResponse obtener(@PathVariable Long id) {
        return grupoHabilitadoEquivalenciaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoHabilitadoEquivalenciaResponse crear(@Valid @RequestBody GrupoHabilitadoEquivalenciaRequest request) {
        return grupoHabilitadoEquivalenciaService.crear(request);
    }

    @PutMapping("/{id}")
    public GrupoHabilitadoEquivalenciaResponse actualizar(@PathVariable Long id, @Valid @RequestBody GrupoHabilitadoEquivalenciaRequest request) {
        return grupoHabilitadoEquivalenciaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        grupoHabilitadoEquivalenciaService.eliminar(id);
    }
}
