package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.EquipamientoAmbienteRequest;
import pe.edu.upeu.lamb.planificacion.dto.EquipamientoAmbienteResponse;
import pe.edu.upeu.lamb.planificacion.service.EquipamientoAmbienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Equipamiento del ambiente", description = "CRUD 18 · Equipamiento del ambiente")
@RestController
@RequestMapping("/api/v1/equipamientos-ambiente")
@RequiredArgsConstructor
public class EquipamientoAmbienteController {

    private final EquipamientoAmbienteService equipamientoAmbienteService;

    @GetMapping
    public List<EquipamientoAmbienteResponse> listar(@RequestParam(required = false) Long idAmbiente) {
        return equipamientoAmbienteService.listar(idAmbiente);
    }

    @GetMapping("/{id}")
    public EquipamientoAmbienteResponse obtener(@PathVariable Long id) {
        return equipamientoAmbienteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipamientoAmbienteResponse crear(@Valid @RequestBody EquipamientoAmbienteRequest request) {
        return equipamientoAmbienteService.crear(request);
    }

    @PutMapping("/{id}")
    public EquipamientoAmbienteResponse actualizar(@PathVariable Long id, @Valid @RequestBody EquipamientoAmbienteRequest request) {
        return equipamientoAmbienteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        equipamientoAmbienteService.eliminar(id);
    }
}
