package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.UnidadAprendizajeRequest;
import pe.edu.upeu.lamb.curriculo.dto.UnidadAprendizajeResponse;
import pe.edu.upeu.lamb.curriculo.service.UnidadAprendizajeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Unidad de aprendizaje", description = "CRUD 16 · Unidades de aprendizaje del silabo base")
@RestController
@RequestMapping("/api/v1/unidades-aprendizaje")
@RequiredArgsConstructor
public class UnidadAprendizajeController {

    private final UnidadAprendizajeService unidadAprendizajeService;

    @GetMapping
    public List<UnidadAprendizajeResponse> listar(@RequestParam(required = false) Long idSilabo) {
        return unidadAprendizajeService.listar(idSilabo);
    }

    @GetMapping("/{id}")
    public UnidadAprendizajeResponse obtener(@PathVariable Long id) {
        return unidadAprendizajeService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnidadAprendizajeResponse crear(@Valid @RequestBody UnidadAprendizajeRequest request) {
        return unidadAprendizajeService.crear(request);
    }

    @PutMapping("/{id}")
    public UnidadAprendizajeResponse actualizar(@PathVariable Long id, @Valid @RequestBody UnidadAprendizajeRequest request) {
        return unidadAprendizajeService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        unidadAprendizajeService.eliminar(id);
    }
}
