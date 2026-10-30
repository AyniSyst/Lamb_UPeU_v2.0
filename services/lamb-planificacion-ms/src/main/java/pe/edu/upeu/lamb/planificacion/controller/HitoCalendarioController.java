package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.HitoCalendarioRequest;
import pe.edu.upeu.lamb.planificacion.dto.HitoCalendarioResponse;
import pe.edu.upeu.lamb.planificacion.service.HitoCalendarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hito del calendario", description = "CRUD 4 · Hito del calendario (inicio, examenes, cierre de notas)")
@RestController
@RequestMapping("/api/v1/hitos-calendario")
@RequiredArgsConstructor
public class HitoCalendarioController {

    private final HitoCalendarioService hitoCalendarioService;

    @GetMapping
    public List<HitoCalendarioResponse> listar(@RequestParam(required = false) Long idCalendario) {
        return hitoCalendarioService.listar(idCalendario);
    }

    @GetMapping("/{id}")
    public HitoCalendarioResponse obtener(@PathVariable Long id) {
        return hitoCalendarioService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HitoCalendarioResponse crear(@Valid @RequestBody HitoCalendarioRequest request) {
        return hitoCalendarioService.crear(request);
    }

    @PutMapping("/{id}")
    public HitoCalendarioResponse actualizar(@PathVariable Long id, @Valid @RequestBody HitoCalendarioRequest request) {
        return hitoCalendarioService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        hitoCalendarioService.eliminar(id);
    }
}
