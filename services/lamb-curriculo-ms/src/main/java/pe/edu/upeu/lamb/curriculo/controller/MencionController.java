package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.MencionRequest;
import pe.edu.upeu.lamb.curriculo.dto.MencionResponse;
import pe.edu.upeu.lamb.curriculo.service.MencionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mencion / especialidad", description = "CRUD 23 · Mencion / especialidad")
@RestController
@RequestMapping("/api/v1/menciones")
@RequiredArgsConstructor
public class MencionController {

    private final MencionService mencionService;

    @GetMapping
    public List<MencionResponse> listar(@RequestParam(required = false) Long idPlan) {
        return mencionService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public MencionResponse obtener(@PathVariable Long id) {
        return mencionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MencionResponse crear(@Valid @RequestBody MencionRequest request) {
        return mencionService.crear(request);
    }

    @PutMapping("/{id}")
    public MencionResponse actualizar(@PathVariable Long id, @Valid @RequestBody MencionRequest request) {
        return mencionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        mencionService.eliminar(id);
    }
}
