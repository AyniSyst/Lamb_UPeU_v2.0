package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.ResultadoAprendizajeRequest;
import pe.edu.upeu.lamb.curriculo.dto.ResultadoAprendizajeResponse;
import pe.edu.upeu.lamb.curriculo.service.ResultadoAprendizajeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Resultado de aprendizaje", description = "CRUD 17 · Resultados de aprendizaje del curso")
@RestController
@RequestMapping("/api/v1/resultados-aprendizaje")
@RequiredArgsConstructor
public class ResultadoAprendizajeController {

    private final ResultadoAprendizajeService resultadoAprendizajeService;

    @GetMapping
    public List<ResultadoAprendizajeResponse> listar(@RequestParam(required = false) Long idCurso) {
        return resultadoAprendizajeService.listar(idCurso);
    }

    @GetMapping("/{id}")
    public ResultadoAprendizajeResponse obtener(@PathVariable Long id) {
        return resultadoAprendizajeService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultadoAprendizajeResponse crear(@Valid @RequestBody ResultadoAprendizajeRequest request) {
        return resultadoAprendizajeService.crear(request);
    }

    @PutMapping("/{id}")
    public ResultadoAprendizajeResponse actualizar(@PathVariable Long id, @Valid @RequestBody ResultadoAprendizajeRequest request) {
        return resultadoAprendizajeService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        resultadoAprendizajeService.eliminar(id);
    }
}
