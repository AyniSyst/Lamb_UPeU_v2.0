package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.LineaInvestigacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.LineaInvestigacionResponse;
import pe.edu.upeu.lamb.curriculo.service.LineaInvestigacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Linea de investigacion", description = "CRUD 24 · Linea de investigacion")
@RestController
@RequestMapping("/api/v1/lineas-investigacion")
@RequiredArgsConstructor
public class LineaInvestigacionController {

    private final LineaInvestigacionService lineaInvestigacionService;

    @GetMapping
    public List<LineaInvestigacionResponse> listar() {
        return lineaInvestigacionService.listar();
    }

    @GetMapping("/{id}")
    public LineaInvestigacionResponse obtener(@PathVariable Long id) {
        return lineaInvestigacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LineaInvestigacionResponse crear(@Valid @RequestBody LineaInvestigacionRequest request) {
        return lineaInvestigacionService.crear(request);
    }

    @PutMapping("/{id}")
    public LineaInvestigacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody LineaInvestigacionRequest request) {
        return lineaInvestigacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        lineaInvestigacionService.eliminar(id);
    }
}
