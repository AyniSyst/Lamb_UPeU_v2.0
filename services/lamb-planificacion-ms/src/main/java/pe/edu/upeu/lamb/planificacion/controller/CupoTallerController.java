package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.CupoTallerRequest;
import pe.edu.upeu.lamb.planificacion.dto.CupoTallerResponse;
import pe.edu.upeu.lamb.planificacion.service.CupoTallerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cupos por talleres", description = "CRUD 10 · Cupos de un grupo de talleres por programa (la suma no supera el cupo del taller)")
@RestController
@RequestMapping("/api/v1/cupos-taller")
@RequiredArgsConstructor
public class CupoTallerController {

    private final CupoTallerService cupoTallerService;

    @GetMapping
    public List<CupoTallerResponse> listar(@RequestParam(required = false) Long idGrupoTaller) {
        return cupoTallerService.listar(idGrupoTaller);
    }

    @GetMapping("/{id}")
    public CupoTallerResponse obtener(@PathVariable Long id) {
        return cupoTallerService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CupoTallerResponse crear(@Valid @RequestBody CupoTallerRequest request) {
        return cupoTallerService.crear(request);
    }

    @PutMapping("/{id}")
    public CupoTallerResponse actualizar(@PathVariable Long id, @Valid @RequestBody CupoTallerRequest request) {
        return cupoTallerService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cupoTallerService.eliminar(id);
    }
}
