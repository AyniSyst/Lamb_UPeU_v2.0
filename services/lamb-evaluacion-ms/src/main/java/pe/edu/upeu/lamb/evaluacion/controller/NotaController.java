package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.NotaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.NotaResponse;
import pe.edu.upeu.lamb.evaluacion.service.NotaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Nota", description = "CRUD 6 · Nota por componente de evaluacion (bloqueada con el acta CERRADA)")
@RestController
@RequestMapping("/api/v1/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @GetMapping
    public List<NotaResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return notaService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public NotaResponse obtener(@PathVariable Long id) {
        return notaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotaResponse crear(@Valid @RequestBody NotaRequest request) {
        return notaService.crear(request);
    }

    @PutMapping("/{id}")
    public NotaResponse actualizar(@PathVariable Long id, @Valid @RequestBody NotaRequest request) {
        return notaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        notaService.eliminar(id);
    }
}
