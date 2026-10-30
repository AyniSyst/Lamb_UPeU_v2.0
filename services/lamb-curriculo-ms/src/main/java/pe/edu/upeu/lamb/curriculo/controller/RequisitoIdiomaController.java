package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoIdiomaRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoIdiomaResponse;
import pe.edu.upeu.lamb.curriculo.service.RequisitoIdiomaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Requisito de idioma", description = "CRUD 34 · Requisito de idioma extranjero o nativo")
@RestController
@RequestMapping("/api/v1/requisitos-idioma")
@RequiredArgsConstructor
public class RequisitoIdiomaController {

    private final RequisitoIdiomaService requisitoIdiomaService;

    @GetMapping
    public List<RequisitoIdiomaResponse> listar(@RequestParam(required = false) Long idPlan) {
        return requisitoIdiomaService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public RequisitoIdiomaResponse obtener(@PathVariable Long id) {
        return requisitoIdiomaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequisitoIdiomaResponse crear(@Valid @RequestBody RequisitoIdiomaRequest request) {
        return requisitoIdiomaService.crear(request);
    }

    @PutMapping("/{id}")
    public RequisitoIdiomaResponse actualizar(@PathVariable Long id, @Valid @RequestBody RequisitoIdiomaRequest request) {
        return requisitoIdiomaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        requisitoIdiomaService.eliminar(id);
    }
}
