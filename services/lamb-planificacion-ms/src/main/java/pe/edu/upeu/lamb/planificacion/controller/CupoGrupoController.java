package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.CupoGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.CupoGrupoResponse;
import pe.edu.upeu.lamb.planificacion.service.CupoGrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cupos por grupo", description = "CRUD 9 · Cupos por grupo segun tipo de estudiante (la suma no supera el cupo del grupo)")
@RestController
@RequestMapping("/api/v1/cupos-grupo")
@RequiredArgsConstructor
public class CupoGrupoController {

    private final CupoGrupoService cupoGrupoService;

    @GetMapping
    public List<CupoGrupoResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return cupoGrupoService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public CupoGrupoResponse obtener(@PathVariable Long id) {
        return cupoGrupoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CupoGrupoResponse crear(@Valid @RequestBody CupoGrupoRequest request) {
        return cupoGrupoService.crear(request);
    }

    @PutMapping("/{id}")
    public CupoGrupoResponse actualizar(@PathVariable Long id, @Valid @RequestBody CupoGrupoRequest request) {
        return cupoGrupoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cupoGrupoService.eliminar(id);
    }
}
