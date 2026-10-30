package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.CriterioRubricaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.CriterioRubricaResponse;
import pe.edu.upeu.lamb.evaluacion.service.CriterioRubricaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Criterio de rubrica", description = "CRUD 5 · Criterio de rubrica")
@RestController
@RequestMapping("/api/v1/criterios-rubrica")
@RequiredArgsConstructor
public class CriterioRubricaController {

    private final CriterioRubricaService criterioRubricaService;

    @GetMapping
    public List<CriterioRubricaResponse> listar(@RequestParam(required = false) Long idRubrica) {
        return criterioRubricaService.listar(idRubrica);
    }

    @GetMapping("/{id}")
    public CriterioRubricaResponse obtener(@PathVariable Long id) {
        return criterioRubricaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CriterioRubricaResponse crear(@Valid @RequestBody CriterioRubricaRequest request) {
        return criterioRubricaService.crear(request);
    }

    @PutMapping("/{id}")
    public CriterioRubricaResponse actualizar(@PathVariable Long id, @Valid @RequestBody CriterioRubricaRequest request) {
        return criterioRubricaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        criterioRubricaService.eliminar(id);
    }
}
