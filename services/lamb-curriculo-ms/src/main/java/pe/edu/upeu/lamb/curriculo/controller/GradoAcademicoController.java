package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.GradoAcademicoRequest;
import pe.edu.upeu.lamb.curriculo.dto.GradoAcademicoResponse;
import pe.edu.upeu.lamb.curriculo.service.GradoAcademicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grado academico", description = "CRUD 3 · Grado academico que otorga (bachiller)")
@RestController
@RequestMapping("/api/v1/grados-academicos")
@RequiredArgsConstructor
public class GradoAcademicoController {

    private final GradoAcademicoService gradoAcademicoService;

    @GetMapping
    public List<GradoAcademicoResponse> listar() {
        return gradoAcademicoService.listar();
    }

    @GetMapping("/{id}")
    public GradoAcademicoResponse obtener(@PathVariable Long id) {
        return gradoAcademicoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GradoAcademicoResponse crear(@Valid @RequestBody GradoAcademicoRequest request) {
        return gradoAcademicoService.crear(request);
    }

    @PutMapping("/{id}")
    public GradoAcademicoResponse actualizar(@PathVariable Long id, @Valid @RequestBody GradoAcademicoRequest request) {
        return gradoAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        gradoAcademicoService.eliminar(id);
    }
}
