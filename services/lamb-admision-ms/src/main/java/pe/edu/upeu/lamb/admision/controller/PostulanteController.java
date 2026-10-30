package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.dto.PostulanteRequest;
import pe.edu.upeu.lamb.admision.dto.PostulanteResponse;
import pe.edu.upeu.lamb.admision.service.PostulanteService;

import java.util.List;

@Tag(name = "Postulante", description = "CRUD 2 - Postulante con datos del colegio y apoderado")
@RestController
@RequestMapping("/api/v1/postulantes")
@RequiredArgsConstructor
public class PostulanteController {
    private final PostulanteService service;

    @GetMapping
    public List<PostulanteResponse> listar(@RequestParam(required = false) Long idConvocatoria) {
        return service.listar(idConvocatoria);
    }

    @GetMapping("/{id}")
    public PostulanteResponse obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostulanteResponse crear(@Valid @RequestBody PostulanteRequest r) { return service.crear(r); }

    @PutMapping("/{id}")
    public PostulanteResponse actualizar(@PathVariable Long id, @Valid @RequestBody PostulanteRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
