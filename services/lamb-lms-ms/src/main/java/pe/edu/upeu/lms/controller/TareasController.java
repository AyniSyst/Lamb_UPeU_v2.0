package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.*;
import pe.edu.upeu.lms.service.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/lms")
@RequiredArgsConstructor
public class TareasController {

    private final TareaService tareaService;
    private final EntregaTareaService entregaTareaService;

    // ---- Tareas ----

    @GetMapping("/tareas")
    public List<TareaResponse> listarTareas() {
        return tareaService.listar();
    }

    @GetMapping("/tareas/{id}")
    public TareaResponse obtenerTarea(@PathVariable Long id) {
        return tareaService.obtener(id);
    }

    @PostMapping("/tareas")
    @ResponseStatus(HttpStatus.CREATED)
    public TareaResponse crearTarea(@Valid @RequestBody TareaRequest request) {
        return tareaService.crear(request);
    }

    @PutMapping("/tareas/{id}")
    public TareaResponse actualizarTarea(@PathVariable Long id, @Valid @RequestBody TareaRequest request) {
        return tareaService.actualizar(id, request);
    }

    @DeleteMapping("/tareas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTarea(@PathVariable Long id) {
        tareaService.eliminar(id);
    }

    // ---- Entregas de tarea ----

    @GetMapping("/entregas-tarea")
    public List<EntregaTareaResponse> listarEntregasTarea() {
        return entregaTareaService.listar();
    }

    @GetMapping("/entregas-tarea/{id}")
    public EntregaTareaResponse obtenerEntregaTarea(@PathVariable Long id) {
        return entregaTareaService.obtener(id);
    }

    @PostMapping("/entregas-tarea")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaTareaResponse crearEntregaTarea(@Valid @RequestBody EntregaTareaRequest request) {
        return entregaTareaService.crear(request);
    }

    @PutMapping("/entregas-tarea/{id}")
    public EntregaTareaResponse actualizarEntregaTarea(@PathVariable Long id, @Valid @RequestBody EntregaTareaRequest request) {
        return entregaTareaService.actualizar(id, request);
    }

    @DeleteMapping("/entregas-tarea/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEntregaTarea(@PathVariable Long id) {
        entregaTareaService.eliminar(id);
    }
}
