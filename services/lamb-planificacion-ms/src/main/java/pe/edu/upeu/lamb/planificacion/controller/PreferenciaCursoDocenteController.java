package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.PreferenciaCursoDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.PreferenciaCursoDocenteResponse;
import pe.edu.upeu.lamb.planificacion.service.PreferenciaCursoDocenteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Preferencia de cursos del docente", description = "CRUD 24 · Preferencia de cursos del docente")
@RestController
@RequestMapping("/api/v1/preferencias-curso-docente")
@RequiredArgsConstructor
public class PreferenciaCursoDocenteController {

    private final PreferenciaCursoDocenteService preferenciaCursoDocenteService;

    @GetMapping
    public List<PreferenciaCursoDocenteResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return preferenciaCursoDocenteService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public PreferenciaCursoDocenteResponse obtener(@PathVariable Long id) {
        return preferenciaCursoDocenteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PreferenciaCursoDocenteResponse crear(@Valid @RequestBody PreferenciaCursoDocenteRequest request) {
        return preferenciaCursoDocenteService.crear(request);
    }

    @PutMapping("/{id}")
    public PreferenciaCursoDocenteResponse actualizar(@PathVariable Long id, @Valid @RequestBody PreferenciaCursoDocenteRequest request) {
        return preferenciaCursoDocenteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        preferenciaCursoDocenteService.eliminar(id);
    }
}
