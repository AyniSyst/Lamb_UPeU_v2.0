package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.SeccionRequest;
import pe.edu.upeu.lamb.planificacion.dto.SeccionResponse;
import pe.edu.upeu.lamb.planificacion.service.SeccionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secciones")
@RequiredArgsConstructor
public class SeccionController {

    private final SeccionService seccionService;

    @GetMapping
    public List<SeccionResponse> listar() {
        return seccionService.listar();
    }

    @GetMapping("/{id}")
    public SeccionResponse obtener(@PathVariable Long id) {
        return seccionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeccionResponse crear(@Valid @RequestBody SeccionRequest request) {
        return seccionService.crear(request);
    }

    @PostMapping("/{id}/revalidar")
    public SeccionResponse revalidar(@PathVariable Long id) {
        return seccionService.revalidar(id);
    }

    @PutMapping("/{id}")
    public SeccionResponse actualizar(@PathVariable Long id, @Valid @RequestBody SeccionRequest request) {
        return seccionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        seccionService.eliminar(id);
    }
}
