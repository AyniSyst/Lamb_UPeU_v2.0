package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoMatriculaRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoMatriculaResponse;
import pe.edu.upeu.lamb.curriculo.service.RequisitoMatriculaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Requisito de matricula", description = "CRUD 19 · Requisito de matricula de un curso del plan (curso aprobado, creditos o ciclo minimo)")
@RestController
@RequestMapping("/api/v1/requisitos-matricula")
@RequiredArgsConstructor
public class RequisitoMatriculaController {

    private final RequisitoMatriculaService requisitoMatriculaService;

    @GetMapping
    public List<RequisitoMatriculaResponse> listar(@RequestParam(required = false) Long idPlanCurso) {
        return requisitoMatriculaService.listar(idPlanCurso);
    }

    @GetMapping("/{id}")
    public RequisitoMatriculaResponse obtener(@PathVariable Long id) {
        return requisitoMatriculaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequisitoMatriculaResponse crear(@Valid @RequestBody RequisitoMatriculaRequest request) {
        return requisitoMatriculaService.crear(request);
    }

    @PutMapping("/{id}")
    public RequisitoMatriculaResponse actualizar(@PathVariable Long id, @Valid @RequestBody RequisitoMatriculaRequest request) {
        return requisitoMatriculaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        requisitoMatriculaService.eliminar(id);
    }
}
