package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.CumplimientoRequisitoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.CumplimientoRequisitoResponse;
import pe.edu.upeu.lamb.trayectoria.service.AvanceRequisitosService;

import java.util.List;

@Tag(name = "Avance y requisitos", description = "CRUD 2 - Cumplimiento de requisitos de grado y titulacion")
@RestController
@RequestMapping("/api/v1/cumplimientos-requisito")
@RequiredArgsConstructor
public class CumplimientoRequisitoController {

    private final AvanceRequisitosService service;

    @GetMapping
    public List<CumplimientoRequisitoResponse> listar(@RequestParam(required = false) Long idPersona,
                                                      @RequestParam(required = false) Long idTrayectoria) {
        return service.listar(idPersona, idTrayectoria);
    }

    @GetMapping("/{id}")
    public CumplimientoRequisitoResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CumplimientoRequisitoResponse crear(@Valid @RequestBody CumplimientoRequisitoRequest r) {
        return service.crear(r);
    }

    @PutMapping("/{id}")
    public CumplimientoRequisitoResponse actualizar(@PathVariable Long id,
                                                    @Valid @RequestBody CumplimientoRequisitoRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
