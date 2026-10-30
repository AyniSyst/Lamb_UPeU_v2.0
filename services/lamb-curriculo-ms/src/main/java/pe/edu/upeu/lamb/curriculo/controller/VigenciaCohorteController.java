package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.VigenciaCohorteRequest;
import pe.edu.upeu.lamb.curriculo.dto.VigenciaCohorteResponse;
import pe.edu.upeu.lamb.curriculo.service.VigenciaCohorteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Vigencia por cohorte", description = "CRUD 8 · Vigencia del plan por cohorte de ingreso (que cohortes siguen este plan)")
@RestController
@RequestMapping("/api/v1/vigencias-cohorte")
@RequiredArgsConstructor
public class VigenciaCohorteController {

    private final VigenciaCohorteService vigenciaCohorteService;

    @GetMapping
    public List<VigenciaCohorteResponse> listar(@RequestParam(required = false) Long idPlan) {
        return vigenciaCohorteService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public VigenciaCohorteResponse obtener(@PathVariable Long id) {
        return vigenciaCohorteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VigenciaCohorteResponse crear(@Valid @RequestBody VigenciaCohorteRequest request) {
        return vigenciaCohorteService.crear(request);
    }

    @PutMapping("/{id}")
    public VigenciaCohorteResponse actualizar(@PathVariable Long id, @Valid @RequestBody VigenciaCohorteRequest request) {
        return vigenciaCohorteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        vigenciaCohorteService.eliminar(id);
    }
}
