package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.TipoCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.TipoCursoResponse;
import pe.edu.upeu.lamb.curriculo.service.TipoCursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tipo de curso", description = "CRUD 13 · Tipo de curso (obligatorio, requisito, electivo)")
@RestController
@RequestMapping("/api/v1/tipos-curso")
@RequiredArgsConstructor
public class TipoCursoController {

    private final TipoCursoService tipoCursoService;

    @GetMapping
    public List<TipoCursoResponse> listar() {
        return tipoCursoService.listar();
    }

    @GetMapping("/{id}")
    public TipoCursoResponse obtener(@PathVariable Long id) {
        return tipoCursoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoCursoResponse crear(@Valid @RequestBody TipoCursoRequest request) {
        return tipoCursoService.crear(request);
    }

    @PutMapping("/{id}")
    public TipoCursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody TipoCursoRequest request) {
        return tipoCursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tipoCursoService.eliminar(id);
    }
}
