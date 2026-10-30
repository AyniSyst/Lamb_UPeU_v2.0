package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.PeriodoAcademicoRequest;
import pe.edu.upeu.lamb.planificacion.dto.PeriodoAcademicoResponse;
import pe.edu.upeu.lamb.planificacion.service.PeriodoAcademicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Periodo academico", description = "CRUD 1 · Periodo academico (2026-I, 2026-II, verano)")
@RestController
@RequestMapping("/api/v1/periodos-academicos")
@RequiredArgsConstructor
public class PeriodoAcademicoController {

    private final PeriodoAcademicoService periodoAcademicoService;

    @GetMapping
    public List<PeriodoAcademicoResponse> listar(@RequestParam(required = false) Long idTipoPeriodo) {
        return periodoAcademicoService.listar(idTipoPeriodo);
    }

    @GetMapping("/{id}")
    public PeriodoAcademicoResponse obtener(@PathVariable Long id) {
        return periodoAcademicoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PeriodoAcademicoResponse crear(@Valid @RequestBody PeriodoAcademicoRequest request) {
        return periodoAcademicoService.crear(request);
    }

    @PutMapping("/{id}")
    public PeriodoAcademicoResponse actualizar(@PathVariable Long id, @Valid @RequestBody PeriodoAcademicoRequest request) {
        return periodoAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        periodoAcademicoService.eliminar(id);
    }
}
