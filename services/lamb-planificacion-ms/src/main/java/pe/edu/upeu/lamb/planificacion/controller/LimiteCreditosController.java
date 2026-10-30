package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.LimiteCreditosRequest;
import pe.edu.upeu.lamb.planificacion.dto.LimiteCreditosResponse;
import pe.edu.upeu.lamb.planificacion.service.LimiteCreditosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Limite de creditos por ciclo", description = "CRUD 12 · Limite de creditos por ciclo, programa y periodo")
@RestController
@RequestMapping("/api/v1/limites-creditos")
@RequiredArgsConstructor
public class LimiteCreditosController {

    private final LimiteCreditosService limiteCreditosService;

    @GetMapping
    public List<LimiteCreditosResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return limiteCreditosService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public LimiteCreditosResponse obtener(@PathVariable Long id) {
        return limiteCreditosService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LimiteCreditosResponse crear(@Valid @RequestBody LimiteCreditosRequest request) {
        return limiteCreditosService.crear(request);
    }

    @PutMapping("/{id}")
    public LimiteCreditosResponse actualizar(@PathVariable Long id, @Valid @RequestBody LimiteCreditosRequest request) {
        return limiteCreditosService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        limiteCreditosService.eliminar(id);
    }
}
