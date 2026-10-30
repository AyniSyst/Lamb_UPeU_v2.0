package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.RegistroAsistenciaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RegistroAsistenciaResponse;
import pe.edu.upeu.lamb.evaluacion.service.RegistroAsistenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Registro de asistencia", description = "CRUD 9 · Registro de asistencia")
@RestController
@RequestMapping("/api/v1/registros-asistencia")
@RequiredArgsConstructor
public class RegistroAsistenciaController {

    private final RegistroAsistenciaService registroAsistenciaService;

    @GetMapping
    public List<RegistroAsistenciaResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return registroAsistenciaService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public RegistroAsistenciaResponse obtener(@PathVariable Long id) {
        return registroAsistenciaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroAsistenciaResponse crear(@Valid @RequestBody RegistroAsistenciaRequest request) {
        return registroAsistenciaService.crear(request);
    }

    @PutMapping("/{id}")
    public RegistroAsistenciaResponse actualizar(@PathVariable Long id, @Valid @RequestBody RegistroAsistenciaRequest request) {
        return registroAsistenciaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        registroAsistenciaService.eliminar(id);
    }
}
