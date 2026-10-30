package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.TipoAmbienteRequest;
import pe.edu.upeu.lamb.planificacion.dto.TipoAmbienteResponse;
import pe.edu.upeu.lamb.planificacion.service.TipoAmbienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tipo de ambiente", description = "CRUD 16 · Tipo de ambiente (aula, laboratorio, auditorio)")
@RestController
@RequestMapping("/api/v1/tipos-ambiente")
@RequiredArgsConstructor
public class TipoAmbienteController {

    private final TipoAmbienteService tipoAmbienteService;

    @GetMapping
    public List<TipoAmbienteResponse> listar() {
        return tipoAmbienteService.listar();
    }

    @GetMapping("/{id}")
    public TipoAmbienteResponse obtener(@PathVariable Long id) {
        return tipoAmbienteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoAmbienteResponse crear(@Valid @RequestBody TipoAmbienteRequest request) {
        return tipoAmbienteService.crear(request);
    }

    @PutMapping("/{id}")
    public TipoAmbienteResponse actualizar(@PathVariable Long id, @Valid @RequestBody TipoAmbienteRequest request) {
        return tipoAmbienteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tipoAmbienteService.eliminar(id);
    }
}
