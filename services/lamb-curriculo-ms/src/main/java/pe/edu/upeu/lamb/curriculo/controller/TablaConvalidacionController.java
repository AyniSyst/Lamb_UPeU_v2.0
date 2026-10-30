package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.TablaConvalidacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.TablaConvalidacionResponse;
import pe.edu.upeu.lamb.curriculo.service.TablaConvalidacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tabla de convalidacion", description = "CRUD 41 · Tabla de convalidacion por traslado externo (universidad de origen x plan destino)")
@RestController
@RequestMapping("/api/v1/tablas-convalidacion")
@RequiredArgsConstructor
public class TablaConvalidacionController {

    private final TablaConvalidacionService tablaConvalidacionService;

    @GetMapping
    public List<TablaConvalidacionResponse> listar(@RequestParam(required = false) Long idUniversidad) {
        return tablaConvalidacionService.listar(idUniversidad);
    }

    @GetMapping("/{id}")
    public TablaConvalidacionResponse obtener(@PathVariable Long id) {
        return tablaConvalidacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TablaConvalidacionResponse crear(@Valid @RequestBody TablaConvalidacionRequest request) {
        return tablaConvalidacionService.crear(request);
    }

    @PutMapping("/{id}")
    public TablaConvalidacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody TablaConvalidacionRequest request) {
        return tablaConvalidacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tablaConvalidacionService.eliminar(id);
    }
}
