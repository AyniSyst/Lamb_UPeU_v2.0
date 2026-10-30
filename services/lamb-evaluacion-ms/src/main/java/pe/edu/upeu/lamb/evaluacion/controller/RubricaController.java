package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.RubricaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RubricaResponse;
import pe.edu.upeu.lamb.evaluacion.service.RubricaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Rubrica", description = "CRUD 4 · Rubrica de un componente de evaluacion")
@RestController
@RequestMapping("/api/v1/rubricas")
@RequiredArgsConstructor
public class RubricaController {

    private final RubricaService rubricaService;

    @GetMapping
    public List<RubricaResponse> listar(@RequestParam(required = false) Long idComponente) {
        return rubricaService.listar(idComponente);
    }

    @GetMapping("/{id}")
    public RubricaResponse obtener(@PathVariable Long id) {
        return rubricaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RubricaResponse crear(@Valid @RequestBody RubricaRequest request) {
        return rubricaService.crear(request);
    }

    @PutMapping("/{id}")
    public RubricaResponse actualizar(@PathVariable Long id, @Valid @RequestBody RubricaRequest request) {
        return rubricaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        rubricaService.eliminar(id);
    }
}
