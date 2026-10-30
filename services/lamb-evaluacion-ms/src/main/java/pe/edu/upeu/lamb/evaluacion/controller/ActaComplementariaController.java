package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.ActaComplementariaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ActaComplementariaResponse;
import pe.edu.upeu.lamb.evaluacion.service.ActaComplementariaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Acta complementaria", description = "CRUD 14 · Acta complementaria (examen sustitutorio)")
@RestController
@RequestMapping("/api/v1/actas-complementarias")
@RequiredArgsConstructor
public class ActaComplementariaController {

    private final ActaComplementariaService actaComplementariaService;

    @GetMapping
    public List<ActaComplementariaResponse> listar(@RequestParam(required = false) Long idActa) {
        return actaComplementariaService.listar(idActa);
    }

    @GetMapping("/{id}")
    public ActaComplementariaResponse obtener(@PathVariable Long id) {
        return actaComplementariaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActaComplementariaResponse crear(@Valid @RequestBody ActaComplementariaRequest request) {
        return actaComplementariaService.crear(request);
    }

    @PutMapping("/{id}")
    public ActaComplementariaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ActaComplementariaRequest request) {
        return actaComplementariaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        actaComplementariaService.eliminar(id);
    }
}
