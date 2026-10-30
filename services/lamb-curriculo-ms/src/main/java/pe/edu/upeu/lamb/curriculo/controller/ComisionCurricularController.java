package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.ComisionCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.ComisionCurricularResponse;
import pe.edu.upeu.lamb.curriculo.service.ComisionCurricularService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comision curricular", description = "CRUD 44 · Comision curricular del programa")
@RestController
@RequestMapping("/api/v1/comisiones-curriculares")
@RequiredArgsConstructor
public class ComisionCurricularController {

    private final ComisionCurricularService comisionCurricularService;

    @GetMapping
    public List<ComisionCurricularResponse> listar() {
        return comisionCurricularService.listar();
    }

    @GetMapping("/{id}")
    public ComisionCurricularResponse obtener(@PathVariable Long id) {
        return comisionCurricularService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComisionCurricularResponse crear(@Valid @RequestBody ComisionCurricularRequest request) {
        return comisionCurricularService.crear(request);
    }

    @PutMapping("/{id}")
    public ComisionCurricularResponse actualizar(@PathVariable Long id, @Valid @RequestBody ComisionCurricularRequest request) {
        return comisionCurricularService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        comisionCurricularService.eliminar(id);
    }
}
