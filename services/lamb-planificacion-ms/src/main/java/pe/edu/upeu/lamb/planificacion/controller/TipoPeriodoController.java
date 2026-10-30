package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.TipoPeriodoRequest;
import pe.edu.upeu.lamb.planificacion.dto.TipoPeriodoResponse;
import pe.edu.upeu.lamb.planificacion.service.TipoPeriodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tipo de periodo", description = "CRUD 2 · Tipo de periodo (regular, verano, nivelacion)")
@RestController
@RequestMapping("/api/v1/tipos-periodo")
@RequiredArgsConstructor
public class TipoPeriodoController {

    private final TipoPeriodoService tipoPeriodoService;

    @GetMapping
    public List<TipoPeriodoResponse> listar() {
        return tipoPeriodoService.listar();
    }

    @GetMapping("/{id}")
    public TipoPeriodoResponse obtener(@PathVariable Long id) {
        return tipoPeriodoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoPeriodoResponse crear(@Valid @RequestBody TipoPeriodoRequest request) {
        return tipoPeriodoService.crear(request);
    }

    @PutMapping("/{id}")
    public TipoPeriodoResponse actualizar(@PathVariable Long id, @Valid @RequestBody TipoPeriodoRequest request) {
        return tipoPeriodoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tipoPeriodoService.eliminar(id);
    }
}
