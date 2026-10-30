package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.ProyeccionSocialRequest;
import pe.edu.upeu.lamb.curriculo.dto.ProyeccionSocialResponse;
import pe.edu.upeu.lamb.curriculo.service.ProyeccionSocialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Responsabilidad social universitaria", description = "CRUD 28 · Responsabilidad social universitaria (proyeccion social)")
@RestController
@RequestMapping("/api/v1/proyecciones-sociales")
@RequiredArgsConstructor
public class ProyeccionSocialController {

    private final ProyeccionSocialService proyeccionSocialService;

    @GetMapping
    public List<ProyeccionSocialResponse> listar() {
        return proyeccionSocialService.listar();
    }

    @GetMapping("/{id}")
    public ProyeccionSocialResponse obtener(@PathVariable Long id) {
        return proyeccionSocialService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProyeccionSocialResponse crear(@Valid @RequestBody ProyeccionSocialRequest request) {
        return proyeccionSocialService.crear(request);
    }

    @PutMapping("/{id}")
    public ProyeccionSocialResponse actualizar(@PathVariable Long id, @Valid @RequestBody ProyeccionSocialRequest request) {
        return proyeccionSocialService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        proyeccionSocialService.eliminar(id);
    }
}
