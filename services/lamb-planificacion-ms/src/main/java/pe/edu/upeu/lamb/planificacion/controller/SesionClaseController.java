package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.SesionClaseRequest;
import pe.edu.upeu.lamb.planificacion.dto.SesionClaseResponse;
import pe.edu.upeu.lamb.planificacion.service.SesionClaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sesion de clase", description = "CRUD 26 · Sesion de clase del silabo")
@RestController
@RequestMapping("/api/v1/sesiones-clase")
@RequiredArgsConstructor
public class SesionClaseController {

    private final SesionClaseService sesionClaseService;

    @GetMapping
    public List<SesionClaseResponse> listar(@RequestParam(required = false) Long idSilabo) {
        return sesionClaseService.listar(idSilabo);
    }

    @GetMapping("/{id}")
    public SesionClaseResponse obtener(@PathVariable Long id) {
        return sesionClaseService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SesionClaseResponse crear(@Valid @RequestBody SesionClaseRequest request) {
        return sesionClaseService.crear(request);
    }

    @PutMapping("/{id}")
    public SesionClaseResponse actualizar(@PathVariable Long id, @Valid @RequestBody SesionClaseRequest request) {
        return sesionClaseService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        sesionClaseService.eliminar(id);
    }
}
