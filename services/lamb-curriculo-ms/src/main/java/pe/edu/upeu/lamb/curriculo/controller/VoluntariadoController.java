package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.VoluntariadoRequest;
import pe.edu.upeu.lamb.curriculo.dto.VoluntariadoResponse;
import pe.edu.upeu.lamb.curriculo.service.VoluntariadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Voluntariado / servicio misionero", description = "CRUD 27 · Voluntariado / servicio misionero")
@RestController
@RequestMapping("/api/v1/voluntariados")
@RequiredArgsConstructor
public class VoluntariadoController {

    private final VoluntariadoService voluntariadoService;

    @GetMapping
    public List<VoluntariadoResponse> listar() {
        return voluntariadoService.listar();
    }

    @GetMapping("/{id}")
    public VoluntariadoResponse obtener(@PathVariable Long id) {
        return voluntariadoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VoluntariadoResponse crear(@Valid @RequestBody VoluntariadoRequest request) {
        return voluntariadoService.crear(request);
    }

    @PutMapping("/{id}")
    public VoluntariadoResponse actualizar(@PathVariable Long id, @Valid @RequestBody VoluntariadoRequest request) {
        return voluntariadoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        voluntariadoService.eliminar(id);
    }
}
