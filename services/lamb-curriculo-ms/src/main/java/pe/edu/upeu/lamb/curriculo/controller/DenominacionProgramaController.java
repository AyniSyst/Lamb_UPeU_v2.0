package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.DenominacionProgramaRequest;
import pe.edu.upeu.lamb.curriculo.dto.DenominacionProgramaResponse;
import pe.edu.upeu.lamb.curriculo.service.DenominacionProgramaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Denominacion del programa", description = "CRUD 2 · Denominacion del programa")
@RestController
@RequestMapping("/api/v1/denominaciones-programa")
@RequiredArgsConstructor
public class DenominacionProgramaController {

    private final DenominacionProgramaService denominacionProgramaService;

    @GetMapping
    public List<DenominacionProgramaResponse> listar() {
        return denominacionProgramaService.listar();
    }

    @GetMapping("/{id}")
    public DenominacionProgramaResponse obtener(@PathVariable Long id) {
        return denominacionProgramaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DenominacionProgramaResponse crear(@Valid @RequestBody DenominacionProgramaRequest request) {
        return denominacionProgramaService.crear(request);
    }

    @PutMapping("/{id}")
    public DenominacionProgramaResponse actualizar(@PathVariable Long id, @Valid @RequestBody DenominacionProgramaRequest request) {
        return denominacionProgramaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        denominacionProgramaService.eliminar(id);
    }
}
