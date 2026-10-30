package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.CargaLectivaRequest;
import pe.edu.upeu.lamb.planificacion.dto.CargaLectivaResponse;
import pe.edu.upeu.lamb.planificacion.service.CargaLectivaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carga lectiva", description = "CRUD 21 · Carga lectiva maxima del docente en el periodo (limite que respeta la apertura de grupos)")
@RestController
@RequestMapping("/api/v1/cargas-lectivas")
@RequiredArgsConstructor
public class CargaLectivaController {

    private final CargaLectivaService cargaLectivaService;

    @GetMapping
    public List<CargaLectivaResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return cargaLectivaService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public CargaLectivaResponse obtener(@PathVariable Long id) {
        return cargaLectivaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CargaLectivaResponse crear(@Valid @RequestBody CargaLectivaRequest request) {
        return cargaLectivaService.crear(request);
    }

    @PutMapping("/{id}")
    public CargaLectivaResponse actualizar(@PathVariable Long id, @Valid @RequestBody CargaLectivaRequest request) {
        return cargaLectivaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cargaLectivaService.eliminar(id);
    }
}
