package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.ReconocimientoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.ReconocimientoResponse;
import pe.edu.upeu.lamb.trayectoria.service.LogrosService;

import java.util.List;

@Tag(name = "Logros y evidencias", description = "CRUD 5 - Reconocimientos, competencias y evidencias")
@RestController
@RequestMapping("/api/v1/reconocimientos")
@RequiredArgsConstructor
public class ReconocimientoController {

    private final LogrosService service;

    @GetMapping
    public List<ReconocimientoResponse> listar(@RequestParam(required = false) Long idPersona) {
        return service.listar(idPersona);
    }

    @GetMapping("/{id}")
    public ReconocimientoResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReconocimientoResponse crear(@Valid @RequestBody ReconocimientoRequest r) {
        return service.crear(r);
    }

    @PutMapping("/{id}")
    public ReconocimientoResponse actualizar(@PathVariable Long id, @Valid @RequestBody ReconocimientoRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
