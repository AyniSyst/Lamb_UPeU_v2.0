package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.FirmaActaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.FirmaActaResponse;
import pe.edu.upeu.lamb.evaluacion.service.FirmaActaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Firma / visado del acta", description = "CRUD 12 · Firma / visado del acta (solo sobre un acta CERRADA)")
@RestController
@RequestMapping("/api/v1/firmas-acta")
@RequiredArgsConstructor
public class FirmaActaController {

    private final FirmaActaService firmaActaService;

    @GetMapping
    public List<FirmaActaResponse> listar(@RequestParam(required = false) Long idActa) {
        return firmaActaService.listar(idActa);
    }

    @GetMapping("/{id}")
    public FirmaActaResponse obtener(@PathVariable Long id) {
        return firmaActaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FirmaActaResponse crear(@Valid @RequestBody FirmaActaRequest request) {
        return firmaActaService.crear(request);
    }

    @PutMapping("/{id}")
    public FirmaActaResponse actualizar(@PathVariable Long id, @Valid @RequestBody FirmaActaRequest request) {
        return firmaActaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        firmaActaService.eliminar(id);
    }
}
