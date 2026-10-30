package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.CursoExternoConvalidableRequest;
import pe.edu.upeu.lamb.curriculo.dto.CursoExternoConvalidableResponse;
import pe.edu.upeu.lamb.curriculo.service.CursoExternoConvalidableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Curso externo convalidable", description = "CRUD 43 · Curso externo convalidable y su curso equivalente del plan")
@RestController
@RequestMapping("/api/v1/cursos-externos-convalidables")
@RequiredArgsConstructor
public class CursoExternoConvalidableController {

    private final CursoExternoConvalidableService cursoExternoConvalidableService;

    @GetMapping
    public List<CursoExternoConvalidableResponse> listar(@RequestParam(required = false) Long idTabla) {
        return cursoExternoConvalidableService.listar(idTabla);
    }

    @GetMapping("/{id}")
    public CursoExternoConvalidableResponse obtener(@PathVariable Long id) {
        return cursoExternoConvalidableService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoExternoConvalidableResponse crear(@Valid @RequestBody CursoExternoConvalidableRequest request) {
        return cursoExternoConvalidableService.crear(request);
    }

    @PutMapping("/{id}")
    public CursoExternoConvalidableResponse actualizar(@PathVariable Long id, @Valid @RequestBody CursoExternoConvalidableRequest request) {
        return cursoExternoConvalidableService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cursoExternoConvalidableService.eliminar(id);
    }
}
