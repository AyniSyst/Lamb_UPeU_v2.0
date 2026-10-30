package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.UniversidadOrigenRequest;
import pe.edu.upeu.lamb.curriculo.dto.UniversidadOrigenResponse;
import pe.edu.upeu.lamb.curriculo.service.UniversidadOrigenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Universidad de origen", description = "CRUD 42 · Universidad de origen (la consulta Admision para traslados externos)")
@RestController
@RequestMapping("/api/v1/universidades-origen")
@RequiredArgsConstructor
public class UniversidadOrigenController {

    private final UniversidadOrigenService universidadOrigenService;

    @GetMapping
    public List<UniversidadOrigenResponse> listar() {
        return universidadOrigenService.listar();
    }

    @GetMapping("/{id}")
    public UniversidadOrigenResponse obtener(@PathVariable Long id) {
        return universidadOrigenService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UniversidadOrigenResponse crear(@Valid @RequestBody UniversidadOrigenRequest request) {
        return universidadOrigenService.crear(request);
    }

    @PutMapping("/{id}")
    public UniversidadOrigenResponse actualizar(@PathVariable Long id, @Valid @RequestBody UniversidadOrigenRequest request) {
        return universidadOrigenService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        universidadOrigenService.eliminar(id);
    }
}
