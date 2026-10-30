package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.CursoCatalogoRequest;
import pe.edu.upeu.lamb.curriculo.dto.CursoCatalogoResponse;
import pe.edu.upeu.lamb.curriculo.service.CursoCatalogoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Catalogo de cursos", description = "CRUD 12 · Catalogo de cursos: el curso se define una sola vez aqui; el plan lo incluye como PlanCurso")
@RestController
@RequestMapping("/api/v1/cursos-catalogo")
@RequiredArgsConstructor
public class CursoCatalogoController {

    private final CursoCatalogoService cursoCatalogoService;

    @GetMapping
    public List<CursoCatalogoResponse> listar(@RequestParam(required = false) Long idDepartamento) {
        return cursoCatalogoService.listar(idDepartamento);
    }

    @GetMapping("/{id}")
    public CursoCatalogoResponse obtener(@PathVariable Long id) {
        return cursoCatalogoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoCatalogoResponse crear(@Valid @RequestBody CursoCatalogoRequest request) {
        return cursoCatalogoService.crear(request);
    }

    @PutMapping("/{id}")
    public CursoCatalogoResponse actualizar(@PathVariable Long id, @Valid @RequestBody CursoCatalogoRequest request) {
        return cursoCatalogoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cursoCatalogoService.eliminar(id);
    }
}
