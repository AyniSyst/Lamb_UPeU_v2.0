package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.PreguntaEncuestaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.PreguntaEncuestaResponse;
import pe.edu.upeu.lamb.evaluacion.service.PreguntaEncuestaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pregunta de la evaluacion docente", description = "CRUD 20 · Pregunta de la evaluacion docente")
@RestController
@RequestMapping("/api/v1/preguntas-encuesta")
@RequiredArgsConstructor
public class PreguntaEncuestaController {

    private final PreguntaEncuestaService preguntaEncuestaService;

    @GetMapping
    public List<PreguntaEncuestaResponse> listar(@RequestParam(required = false) Long idEncuesta) {
        return preguntaEncuestaService.listar(idEncuesta);
    }

    @GetMapping("/{id}")
    public PreguntaEncuestaResponse obtener(@PathVariable Long id) {
        return preguntaEncuestaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PreguntaEncuestaResponse crear(@Valid @RequestBody PreguntaEncuestaRequest request) {
        return preguntaEncuestaService.crear(request);
    }

    @PutMapping("/{id}")
    public PreguntaEncuestaResponse actualizar(@PathVariable Long id, @Valid @RequestBody PreguntaEncuestaRequest request) {
        return preguntaEncuestaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        preguntaEncuestaService.eliminar(id);
    }
}
