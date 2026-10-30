package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.InhabilitacionInasistenciaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.InhabilitacionInasistenciaResponse;
import pe.edu.upeu.lamb.evaluacion.service.InhabilitacionInasistenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Inhabilitacion por inasistencia", description = "CRUD 11 · Inhabilitacion por inasistencia (30 %): el porcentaje lo calcula el servicio con los registros")
@RestController
@RequestMapping("/api/v1/inhabilitaciones-inasistencia")
@RequiredArgsConstructor
public class InhabilitacionInasistenciaController {

    private final InhabilitacionInasistenciaService inhabilitacionInasistenciaService;

    @GetMapping
    public List<InhabilitacionInasistenciaResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return inhabilitacionInasistenciaService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public InhabilitacionInasistenciaResponse obtener(@PathVariable Long id) {
        return inhabilitacionInasistenciaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InhabilitacionInasistenciaResponse crear(@Valid @RequestBody InhabilitacionInasistenciaRequest request) {
        return inhabilitacionInasistenciaService.crear(request);
    }

    @PutMapping("/{id}")
    public InhabilitacionInasistenciaResponse actualizar(@PathVariable Long id, @Valid @RequestBody InhabilitacionInasistenciaRequest request) {
        return inhabilitacionInasistenciaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        inhabilitacionInasistenciaService.eliminar(id);
    }
}
