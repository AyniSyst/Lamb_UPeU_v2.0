package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.EncuestaDocenteRequest;
import pe.edu.upeu.lamb.evaluacion.dto.EncuestaDocenteResponse;
import pe.edu.upeu.lamb.evaluacion.service.EncuestaDocenteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Encuesta de evaluacion docente", description = "CRUD 19 · Encuesta de evaluacion docente")
@RestController
@RequestMapping("/api/v1/encuestas-docente")
@RequiredArgsConstructor
public class EncuestaDocenteController {

    private final EncuestaDocenteService encuestaDocenteService;

    @GetMapping
    public List<EncuestaDocenteResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return encuestaDocenteService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public EncuestaDocenteResponse obtener(@PathVariable Long id) {
        return encuestaDocenteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EncuestaDocenteResponse crear(@Valid @RequestBody EncuestaDocenteRequest request) {
        return encuestaDocenteService.crear(request);
    }

    @PutMapping("/{id}")
    public EncuestaDocenteResponse actualizar(@PathVariable Long id, @Valid @RequestBody EncuestaDocenteRequest request) {
        return encuestaDocenteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        encuestaDocenteService.eliminar(id);
    }
}
