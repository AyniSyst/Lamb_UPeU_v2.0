package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.EscalaCalificacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.EscalaCalificacionResponse;
import pe.edu.upeu.lamb.evaluacion.service.EscalaCalificacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Escala de calificacion", description = "CRUD 3 · Escala de calificacion (vigesimal 0-20, nota minima aprobatoria y regla de redondeo)")
@RestController
@RequestMapping("/api/v1/escalas-calificacion")
@RequiredArgsConstructor
public class EscalaCalificacionController {

    private final EscalaCalificacionService escalaCalificacionService;

    @GetMapping
    public List<EscalaCalificacionResponse> listar() {
        return escalaCalificacionService.listar();
    }

    @GetMapping("/{id}")
    public EscalaCalificacionResponse obtener(@PathVariable Long id) {
        return escalaCalificacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EscalaCalificacionResponse crear(@Valid @RequestBody EscalaCalificacionRequest request) {
        return escalaCalificacionService.crear(request);
    }

    @PutMapping("/{id}")
    public EscalaCalificacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody EscalaCalificacionRequest request) {
        return escalaCalificacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        escalaCalificacionService.eliminar(id);
    }
}
