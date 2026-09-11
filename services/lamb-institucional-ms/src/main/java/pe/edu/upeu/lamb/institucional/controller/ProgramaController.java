package pe.edu.upeu.lamb.institucional.controller;

import pe.edu.upeu.lamb.institucional.dto.ProgramaRequest;
import pe.edu.upeu.lamb.institucional.dto.ProgramaResponse;
import pe.edu.upeu.lamb.institucional.service.ProgramaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/programas")
@RequiredArgsConstructor
public class ProgramaController {

    private final ProgramaService programaService;

    @GetMapping
    public List<ProgramaResponse> listar() {
        return programaService.listar();
    }

    @GetMapping("/{id}")
    public ProgramaResponse obtener(@PathVariable Long id) {
        return programaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramaResponse crear(@Valid @RequestBody ProgramaRequest request) {
        return programaService.crear(request);
    }

    @PutMapping("/{id}")
    public ProgramaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ProgramaRequest request) {
        return programaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        programaService.eliminar(id);
    }
}
