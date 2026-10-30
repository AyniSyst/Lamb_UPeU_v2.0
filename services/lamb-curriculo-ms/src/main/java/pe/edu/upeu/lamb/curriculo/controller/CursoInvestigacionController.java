package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.CursoInvestigacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.CursoInvestigacionResponse;
import pe.edu.upeu.lamb.curriculo.service.CursoInvestigacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Curso de investigacion", description = "CRUD 25 · Curso de investigacion asociado a una linea (relacion linea x curso del plan)")
@RestController
@RequestMapping("/api/v1/cursos-investigacion")
@RequiredArgsConstructor
public class CursoInvestigacionController {

    private final CursoInvestigacionService cursoInvestigacionService;

    @GetMapping
    public List<CursoInvestigacionResponse> listar(@RequestParam(required = false) Long idLinea) {
        return cursoInvestigacionService.listar(idLinea);
    }

    @GetMapping("/{id}")
    public CursoInvestigacionResponse obtener(@PathVariable Long id) {
        return cursoInvestigacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoInvestigacionResponse crear(@Valid @RequestBody CursoInvestigacionRequest request) {
        return cursoInvestigacionService.crear(request);
    }

    @PutMapping("/{id}")
    public CursoInvestigacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody CursoInvestigacionRequest request) {
        return cursoInvestigacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cursoInvestigacionService.eliminar(id);
    }
}
