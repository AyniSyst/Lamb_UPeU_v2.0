package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.AulaVirtualGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.AulaVirtualGrupoResponse;
import pe.edu.upeu.lamb.planificacion.service.AulaVirtualGrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Aula virtual del grupo", description = "CRUD 19 · Aula virtual del grupo (enlace)")
@RestController
@RequestMapping("/api/v1/aulas-virtuales")
@RequiredArgsConstructor
public class AulaVirtualGrupoController {

    private final AulaVirtualGrupoService aulaVirtualGrupoService;

    @GetMapping
    public List<AulaVirtualGrupoResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return aulaVirtualGrupoService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public AulaVirtualGrupoResponse obtener(@PathVariable Long id) {
        return aulaVirtualGrupoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AulaVirtualGrupoResponse crear(@Valid @RequestBody AulaVirtualGrupoRequest request) {
        return aulaVirtualGrupoService.crear(request);
    }

    @PutMapping("/{id}")
    public AulaVirtualGrupoResponse actualizar(@PathVariable Long id, @Valid @RequestBody AulaVirtualGrupoRequest request) {
        return aulaVirtualGrupoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        aulaVirtualGrupoService.eliminar(id);
    }
}
