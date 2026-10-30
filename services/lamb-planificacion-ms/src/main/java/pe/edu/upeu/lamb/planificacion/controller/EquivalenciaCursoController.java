package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.EquivalenciaCursoRequest;
import pe.edu.upeu.lamb.planificacion.dto.EquivalenciaCursoResponse;
import pe.edu.upeu.lamb.planificacion.service.EquivalenciaCursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Equivalencia o variacion de curso", description = "CRUD 33 · Equivalencia o variacion de curso entre ciclos o carreras (los cursos se jalan de curriculo)")
@RestController
@RequestMapping("/api/v1/equivalencias-curso")
@RequiredArgsConstructor
public class EquivalenciaCursoController {

    private final EquivalenciaCursoService equivalenciaCursoService;

    @GetMapping
    public List<EquivalenciaCursoResponse> listar(@RequestParam(required = false) Long idCurso) {
        return equivalenciaCursoService.listar(idCurso);
    }

    @GetMapping("/{id}")
    public EquivalenciaCursoResponse obtener(@PathVariable Long id) {
        return equivalenciaCursoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquivalenciaCursoResponse crear(@Valid @RequestBody EquivalenciaCursoRequest request) {
        return equivalenciaCursoService.crear(request);
    }

    @PutMapping("/{id}")
    public EquivalenciaCursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody EquivalenciaCursoRequest request) {
        return equivalenciaCursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        equivalenciaCursoService.eliminar(id);
    }
}
