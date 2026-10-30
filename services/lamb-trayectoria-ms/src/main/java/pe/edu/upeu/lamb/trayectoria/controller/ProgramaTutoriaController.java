package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.ProgramaTutoriaRequest;
import pe.edu.upeu.lamb.trayectoria.dto.ProgramaTutoriaResponse;
import pe.edu.upeu.lamb.trayectoria.service.TutoriaService;

import java.util.List;

@Tag(name = "Tutoria - Programas", description = "CRUD 4 - Catalogo de programas de tutoria")
@RestController
@RequestMapping("/api/v1/programas-tutoria")
@RequiredArgsConstructor
public class ProgramaTutoriaController {

    private final TutoriaService service;

    @GetMapping
    public List<ProgramaTutoriaResponse> listar() {
        return service.listarProgramas();
    }

    @GetMapping("/{id}")
    public ProgramaTutoriaResponse obtener(@PathVariable Long id) {
        return service.obtenerPrograma(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramaTutoriaResponse crear(@Valid @RequestBody ProgramaTutoriaRequest r) {
        return service.crearPrograma(r);
    }

    @PutMapping("/{id}")
    public ProgramaTutoriaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ProgramaTutoriaRequest r) {
        return service.actualizarPrograma(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarPrograma(id);
    }
}
