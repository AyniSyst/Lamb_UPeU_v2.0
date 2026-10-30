package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.SilaboGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.SilaboGrupoResponse;
import pe.edu.upeu.lamb.planificacion.service.SilaboGrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Silabo de grupo", description = "CRUD 25 · Silabo de grupo (parte del silabo base de curriculo)")
@RestController
@RequestMapping("/api/v1/silabos-grupo")
@RequiredArgsConstructor
public class SilaboGrupoController {

    private final SilaboGrupoService silaboGrupoService;

    @GetMapping
    public List<SilaboGrupoResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return silaboGrupoService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public SilaboGrupoResponse obtener(@PathVariable Long id) {
        return silaboGrupoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SilaboGrupoResponse crear(@Valid @RequestBody SilaboGrupoRequest request) {
        return silaboGrupoService.crear(request);
    }

    @PutMapping("/{id}")
    public SilaboGrupoResponse actualizar(@PathVariable Long id, @Valid @RequestBody SilaboGrupoRequest request) {
        return silaboGrupoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        silaboGrupoService.eliminar(id);
    }
}
