package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.NivelRiesgoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.NivelRiesgoResponse;
import pe.edu.upeu.lamb.trayectoria.service.RiesgoAcademicoService;

import java.util.List;

@Tag(name = "Riesgo academico - Niveles", description = "CRUD 3 - Catalogo de niveles de riesgo")
@RestController
@RequestMapping("/api/v1/niveles-riesgo")
@RequiredArgsConstructor
public class NivelRiesgoController {

    private final RiesgoAcademicoService service;

    @GetMapping
    public List<NivelRiesgoResponse> listar() {
        return service.listarNiveles();
    }

    @GetMapping("/{id}")
    public NivelRiesgoResponse obtener(@PathVariable Long id) {
        return service.obtenerNivel(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NivelRiesgoResponse crear(@Valid @RequestBody NivelRiesgoRequest r) {
        return service.crearNivel(r);
    }

    @PutMapping("/{id}")
    public NivelRiesgoResponse actualizar(@PathVariable Long id, @Valid @RequestBody NivelRiesgoRequest r) {
        return service.actualizarNivel(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarNivel(id);
    }
}
