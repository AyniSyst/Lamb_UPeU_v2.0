package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.SumillaCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.SumillaCursoResponse;
import pe.edu.upeu.lamb.curriculo.service.SumillaCursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sumilla del curso", description = "CRUD 14 · Sumilla del curso")
@RestController
@RequestMapping("/api/v1/sumillas-curso")
@RequiredArgsConstructor
public class SumillaCursoController {

    private final SumillaCursoService sumillaCursoService;

    @GetMapping
    public List<SumillaCursoResponse> listar(@RequestParam(required = false) Long idCurso) {
        return sumillaCursoService.listar(idCurso);
    }

    @GetMapping("/{id}")
    public SumillaCursoResponse obtener(@PathVariable Long id) {
        return sumillaCursoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SumillaCursoResponse crear(@Valid @RequestBody SumillaCursoRequest request) {
        return sumillaCursoService.crear(request);
    }

    @PutMapping("/{id}")
    public SumillaCursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody SumillaCursoRequest request) {
        return sumillaCursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        sumillaCursoService.eliminar(id);
    }
}
