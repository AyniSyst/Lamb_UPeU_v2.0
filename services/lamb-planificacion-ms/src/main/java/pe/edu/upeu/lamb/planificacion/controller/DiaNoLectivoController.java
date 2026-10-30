package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.DiaNoLectivoRequest;
import pe.edu.upeu.lamb.planificacion.dto.DiaNoLectivoResponse;
import pe.edu.upeu.lamb.planificacion.service.DiaNoLectivoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Dia no lectivo", description = "CRUD 5 · Dia no lectivo / feriado")
@RestController
@RequestMapping("/api/v1/dias-no-lectivos")
@RequiredArgsConstructor
public class DiaNoLectivoController {

    private final DiaNoLectivoService diaNoLectivoService;

    @GetMapping
    public List<DiaNoLectivoResponse> listar(@RequestParam(required = false) Long idCalendario) {
        return diaNoLectivoService.listar(idCalendario);
    }

    @GetMapping("/{id}")
    public DiaNoLectivoResponse obtener(@PathVariable Long id) {
        return diaNoLectivoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiaNoLectivoResponse crear(@Valid @RequestBody DiaNoLectivoRequest request) {
        return diaNoLectivoService.crear(request);
    }

    @PutMapping("/{id}")
    public DiaNoLectivoResponse actualizar(@PathVariable Long id, @Valid @RequestBody DiaNoLectivoRequest request) {
        return diaNoLectivoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        diaNoLectivoService.eliminar(id);
    }
}
