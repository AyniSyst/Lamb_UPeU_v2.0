package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.ParametroMatriculaRequest;
import pe.edu.upeu.lamb.planificacion.dto.ParametroMatriculaResponse;
import pe.edu.upeu.lamb.planificacion.service.ParametroMatriculaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Parametros de matricula", description = "CRUD 13 · Parametros de matricula por escuela y periodo")
@RestController
@RequestMapping("/api/v1/parametros-matricula")
@RequiredArgsConstructor
public class ParametroMatriculaController {

    private final ParametroMatriculaService parametroMatriculaService;

    @GetMapping
    public List<ParametroMatriculaResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return parametroMatriculaService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public ParametroMatriculaResponse obtener(@PathVariable Long id) {
        return parametroMatriculaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParametroMatriculaResponse crear(@Valid @RequestBody ParametroMatriculaRequest request) {
        return parametroMatriculaService.crear(request);
    }

    @PutMapping("/{id}")
    public ParametroMatriculaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ParametroMatriculaRequest request) {
        return parametroMatriculaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        parametroMatriculaService.eliminar(id);
    }
}
