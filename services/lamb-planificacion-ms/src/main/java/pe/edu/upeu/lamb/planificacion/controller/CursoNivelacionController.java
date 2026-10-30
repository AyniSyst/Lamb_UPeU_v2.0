package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.CursoNivelacionRequest;
import pe.edu.upeu.lamb.planificacion.dto.CursoNivelacionResponse;
import pe.edu.upeu.lamb.planificacion.service.CursoNivelacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Curso de nivelacion / verano", description = "CRUD 11 · Curso de nivelacion o de verano")
@RestController
@RequestMapping("/api/v1/cursos-nivelacion")
@RequiredArgsConstructor
public class CursoNivelacionController {

    private final CursoNivelacionService cursoNivelacionService;

    @GetMapping
    public List<CursoNivelacionResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return cursoNivelacionService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public CursoNivelacionResponse obtener(@PathVariable Long id) {
        return cursoNivelacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoNivelacionResponse crear(@Valid @RequestBody CursoNivelacionRequest request) {
        return cursoNivelacionService.crear(request);
    }

    @PutMapping("/{id}")
    public CursoNivelacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody CursoNivelacionRequest request) {
        return cursoNivelacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cursoNivelacionService.eliminar(id);
    }
}
