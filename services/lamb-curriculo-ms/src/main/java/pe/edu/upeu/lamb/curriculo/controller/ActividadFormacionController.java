package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.ActividadFormacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.ActividadFormacionResponse;
import pe.edu.upeu.lamb.curriculo.service.ActividadFormacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Actividad de formacion integral", description = "CRUD 26 · Actividad de formacion integral (espiritual, cultural, deportiva)")
@RestController
@RequestMapping("/api/v1/actividades-formacion")
@RequiredArgsConstructor
public class ActividadFormacionController {

    private final ActividadFormacionService actividadFormacionService;

    @GetMapping
    public List<ActividadFormacionResponse> listar() {
        return actividadFormacionService.listar();
    }

    @GetMapping("/{id}")
    public ActividadFormacionResponse obtener(@PathVariable Long id) {
        return actividadFormacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActividadFormacionResponse crear(@Valid @RequestBody ActividadFormacionRequest request) {
        return actividadFormacionService.crear(request);
    }

    @PutMapping("/{id}")
    public ActividadFormacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody ActividadFormacionRequest request) {
        return actividadFormacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        actividadFormacionService.eliminar(id);
    }
}
