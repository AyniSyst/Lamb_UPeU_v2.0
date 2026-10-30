package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.HorarioGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.HorarioGrupoResponse;
import pe.edu.upeu.lamb.planificacion.service.HorarioGrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Horario de grupo", description = "CRUD 17 · Horario de grupo (detalle Grupo x Ambiente, sin cruces de aula ni de docente)")
@RestController
@RequestMapping("/api/v1/horarios-grupo")
@RequiredArgsConstructor
public class HorarioGrupoController {

    private final HorarioGrupoService horarioGrupoService;

    @GetMapping
    public List<HorarioGrupoResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return horarioGrupoService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public HorarioGrupoResponse obtener(@PathVariable Long id) {
        return horarioGrupoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorarioGrupoResponse crear(@Valid @RequestBody HorarioGrupoRequest request) {
        return horarioGrupoService.crear(request);
    }

    @PutMapping("/{id}")
    public HorarioGrupoResponse actualizar(@PathVariable Long id, @Valid @RequestBody HorarioGrupoRequest request) {
        return horarioGrupoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        horarioGrupoService.eliminar(id);
    }
}
