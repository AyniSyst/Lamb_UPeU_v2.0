package pe.edu.upeu.lamb.institucional.controller;

import pe.edu.upeu.lamb.institucional.dto.FacultadRequest;
import pe.edu.upeu.lamb.institucional.dto.FacultadResponse;
import pe.edu.upeu.lamb.institucional.service.FacultadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facultades")
@RequiredArgsConstructor
public class FacultadController {

    private final FacultadService facultadService;

    @GetMapping
    public List<FacultadResponse> listar() {
        return facultadService.listar();
    }

    @GetMapping("/{id}")
    public FacultadResponse obtener(@PathVariable Long id) {
        return facultadService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FacultadResponse crear(@Valid @RequestBody FacultadRequest request) {
        return facultadService.crear(request);
    }

    @PutMapping("/{id}")
    public FacultadResponse actualizar(@PathVariable Long id, @Valid @RequestBody FacultadRequest request) {
        return facultadService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        facultadService.eliminar(id);
    }
}
