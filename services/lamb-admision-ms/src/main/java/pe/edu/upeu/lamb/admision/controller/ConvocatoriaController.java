package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.dto.ConvocatoriaRequest;
import pe.edu.upeu.lamb.admision.dto.ConvocatoriaResponse;
import pe.edu.upeu.lamb.admision.service.ConvocatoriaService;

import java.util.List;

@Tag(name = "Convocatoria y oferta", description = "CRUD 1 - Procesos de admision, periodos y vacantes totales")
@RestController
@RequestMapping("/api/v1/convocatorias")
@RequiredArgsConstructor
public class ConvocatoriaController {
    private final ConvocatoriaService service;

    @GetMapping
    public List<ConvocatoriaResponse> listar(@RequestParam(required = false) String periodo) {
        return service.listar(periodo);
    }

    @GetMapping("/{id}")
    public ConvocatoriaResponse obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConvocatoriaResponse crear(@Valid @RequestBody ConvocatoriaRequest r) { return service.crear(r); }

    @PutMapping("/{id}")
    public ConvocatoriaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ConvocatoriaRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
