package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.EvaluacionCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.EvaluacionCurricularResponse;
import pe.edu.upeu.lamb.curriculo.service.EvaluacionCurricularService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Evaluacion curricular periodica", description = "CRUD 47 · Evaluacion curricular periodica")
@RestController
@RequestMapping("/api/v1/evaluaciones-curriculares")
@RequiredArgsConstructor
public class EvaluacionCurricularController {

    private final EvaluacionCurricularService evaluacionCurricularService;

    @GetMapping
    public List<EvaluacionCurricularResponse> listar(@RequestParam(required = false) Long idPlan) {
        return evaluacionCurricularService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public EvaluacionCurricularResponse obtener(@PathVariable Long id) {
        return evaluacionCurricularService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluacionCurricularResponse crear(@Valid @RequestBody EvaluacionCurricularRequest request) {
        return evaluacionCurricularService.crear(request);
    }

    @PutMapping("/{id}")
    public EvaluacionCurricularResponse actualizar(@PathVariable Long id, @Valid @RequestBody EvaluacionCurricularRequest request) {
        return evaluacionCurricularService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        evaluacionCurricularService.eliminar(id);
    }
}
