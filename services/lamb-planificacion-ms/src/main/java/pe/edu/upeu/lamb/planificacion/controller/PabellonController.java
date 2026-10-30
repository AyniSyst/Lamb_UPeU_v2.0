package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.PabellonRequest;
import pe.edu.upeu.lamb.planificacion.dto.PabellonResponse;
import pe.edu.upeu.lamb.planificacion.service.PabellonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Campus / pabellon", description = "CRUD 14 · Campus / pabellon")
@RestController
@RequestMapping("/api/v1/pabellones")
@RequiredArgsConstructor
public class PabellonController {

    private final PabellonService pabellonService;

    @GetMapping
    public List<PabellonResponse> listar() {
        return pabellonService.listar();
    }

    @GetMapping("/{id}")
    public PabellonResponse obtener(@PathVariable Long id) {
        return pabellonService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PabellonResponse crear(@Valid @RequestBody PabellonRequest request) {
        return pabellonService.crear(request);
    }

    @PutMapping("/{id}")
    public PabellonResponse actualizar(@PathVariable Long id, @Valid @RequestBody PabellonRequest request) {
        return pabellonService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        pabellonService.eliminar(id);
    }
}
