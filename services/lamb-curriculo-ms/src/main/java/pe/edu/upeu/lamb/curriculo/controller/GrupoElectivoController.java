package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.GrupoElectivoRequest;
import pe.edu.upeu.lamb.curriculo.dto.GrupoElectivoResponse;
import pe.edu.upeu.lamb.curriculo.service.GrupoElectivoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grupo de electivos", description = "CRUD 21 · Grupo de electivos (elegir N de M cursos del plan)")
@RestController
@RequestMapping("/api/v1/grupos-electivos")
@RequiredArgsConstructor
public class GrupoElectivoController {

    private final GrupoElectivoService grupoElectivoService;

    @GetMapping
    public List<GrupoElectivoResponse> listar(@RequestParam(required = false) Long idPlan) {
        return grupoElectivoService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public GrupoElectivoResponse obtener(@PathVariable Long id) {
        return grupoElectivoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoElectivoResponse crear(@Valid @RequestBody GrupoElectivoRequest request) {
        return grupoElectivoService.crear(request);
    }

    @PutMapping("/{id}")
    public GrupoElectivoResponse actualizar(@PathVariable Long id, @Valid @RequestBody GrupoElectivoRequest request) {
        return grupoElectivoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        grupoElectivoService.eliminar(id);
    }
}
