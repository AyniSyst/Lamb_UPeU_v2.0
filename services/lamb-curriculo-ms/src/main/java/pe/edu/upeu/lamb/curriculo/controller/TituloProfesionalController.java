package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.TituloProfesionalRequest;
import pe.edu.upeu.lamb.curriculo.dto.TituloProfesionalResponse;
import pe.edu.upeu.lamb.curriculo.service.TituloProfesionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Titulo profesional", description = "CRUD 4 · Titulo profesional que otorga")
@RestController
@RequestMapping("/api/v1/titulos-profesionales")
@RequiredArgsConstructor
public class TituloProfesionalController {

    private final TituloProfesionalService tituloProfesionalService;

    @GetMapping
    public List<TituloProfesionalResponse> listar() {
        return tituloProfesionalService.listar();
    }

    @GetMapping("/{id}")
    public TituloProfesionalResponse obtener(@PathVariable Long id) {
        return tituloProfesionalService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TituloProfesionalResponse crear(@Valid @RequestBody TituloProfesionalRequest request) {
        return tituloProfesionalService.crear(request);
    }

    @PutMapping("/{id}")
    public TituloProfesionalResponse actualizar(@PathVariable Long id, @Valid @RequestBody TituloProfesionalRequest request) {
        return tituloProfesionalService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tituloProfesionalService.eliminar(id);
    }
}
