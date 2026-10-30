package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.ConfiguracionEvaluacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ConfiguracionEvaluacionResponse;
import pe.edu.upeu.lamb.evaluacion.service.ConfiguracionEvaluacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Peso por componente", description = "CRUD 2 · Peso de cada componente en la nota final del grupo (la suma por grupo no pasa de 100)")
@RestController
@RequestMapping("/api/v1/configuraciones-evaluacion")
@RequiredArgsConstructor
public class ConfiguracionEvaluacionController {

    private final ConfiguracionEvaluacionService configuracionEvaluacionService;

    @GetMapping
    public List<ConfiguracionEvaluacionResponse> listar() {
        return configuracionEvaluacionService.listar();
    }

    @GetMapping("/{id}")
    public ConfiguracionEvaluacionResponse obtener(@PathVariable Long id) {
        return configuracionEvaluacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConfiguracionEvaluacionResponse crear(@Valid @RequestBody ConfiguracionEvaluacionRequest request) {
        return configuracionEvaluacionService.crear(request);
    }

    @PutMapping("/{id}")
    public ConfiguracionEvaluacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody ConfiguracionEvaluacionRequest request) {
        return configuracionEvaluacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        configuracionEvaluacionService.eliminar(id);
    }
}
