package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.CalendarioAcademicoRequest;
import pe.edu.upeu.lamb.planificacion.dto.CalendarioAcademicoResponse;
import pe.edu.upeu.lamb.planificacion.service.CalendarioAcademicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Calendario academico", description = "CRUD 3 · Calendario academico del periodo por sede")
@RestController
@RequestMapping("/api/v1/calendarios-academicos")
@RequiredArgsConstructor
public class CalendarioAcademicoController {

    private final CalendarioAcademicoService calendarioAcademicoService;

    @GetMapping
    public List<CalendarioAcademicoResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return calendarioAcademicoService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public CalendarioAcademicoResponse obtener(@PathVariable Long id) {
        return calendarioAcademicoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalendarioAcademicoResponse crear(@Valid @RequestBody CalendarioAcademicoRequest request) {
        return calendarioAcademicoService.crear(request);
    }

    @PutMapping("/{id}")
    public CalendarioAcademicoResponse actualizar(@PathVariable Long id, @Valid @RequestBody CalendarioAcademicoRequest request) {
        return calendarioAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        calendarioAcademicoService.eliminar(id);
    }
}
