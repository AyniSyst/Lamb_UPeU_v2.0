package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.RespuestaEncuestaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaEncuestaResponse;
import pe.edu.upeu.lamb.evaluacion.service.RespuestaEncuestaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Respuesta de la evaluacion docente", description = "CRUD 21 · Respuesta de la evaluacion docente (anonima: no guarda al estudiante)")
@RestController
@RequestMapping("/api/v1/respuestas-encuesta")
@RequiredArgsConstructor
public class RespuestaEncuestaController {

    private final RespuestaEncuestaService respuestaEncuestaService;

    @GetMapping
    public List<RespuestaEncuestaResponse> listar(@RequestParam(required = false) Long idPregunta) {
        return respuestaEncuestaService.listar(idPregunta);
    }

    @GetMapping("/{id}")
    public RespuestaEncuestaResponse obtener(@PathVariable Long id) {
        return respuestaEncuestaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaEncuestaResponse crear(@Valid @RequestBody RespuestaEncuestaRequest request) {
        return respuestaEncuestaService.crear(request);
    }

    @PutMapping("/{id}")
    public RespuestaEncuestaResponse actualizar(@PathVariable Long id, @Valid @RequestBody RespuestaEncuestaRequest request) {
        return respuestaEncuestaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        respuestaEncuestaService.eliminar(id);
    }
}
