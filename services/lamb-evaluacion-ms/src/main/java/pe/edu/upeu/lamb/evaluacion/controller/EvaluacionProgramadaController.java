package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.EvaluacionProgramadaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.EvaluacionProgramadaResponse;
import pe.edu.upeu.lamb.evaluacion.service.EvaluacionProgramadaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Evaluacion programada", description = "CRUD 7 · Evaluacion programada (fecha de examen)")
@RestController
@RequestMapping("/api/v1/evaluaciones-programadas")
@RequiredArgsConstructor
public class EvaluacionProgramadaController {

    private final EvaluacionProgramadaService evaluacionProgramadaService;

    @GetMapping
    public List<EvaluacionProgramadaResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return evaluacionProgramadaService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public EvaluacionProgramadaResponse obtener(@PathVariable Long id) {
        return evaluacionProgramadaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluacionProgramadaResponse crear(@Valid @RequestBody EvaluacionProgramadaRequest request) {
        return evaluacionProgramadaService.crear(request);
    }

    @PutMapping("/{id}")
    public EvaluacionProgramadaResponse actualizar(@PathVariable Long id, @Valid @RequestBody EvaluacionProgramadaRequest request) {
        return evaluacionProgramadaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        evaluacionProgramadaService.eliminar(id);
    }
}
