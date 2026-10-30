package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoPracticasRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoPracticasResponse;
import pe.edu.upeu.lamb.curriculo.service.RequisitoPracticasService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Requisito de practicas", description = "CRUD 35 · Requisito de practicas preprofesionales")
@RestController
@RequestMapping("/api/v1/requisitos-practicas")
@RequiredArgsConstructor
public class RequisitoPracticasController {

    private final RequisitoPracticasService requisitoPracticasService;

    @GetMapping
    public List<RequisitoPracticasResponse> listar(@RequestParam(required = false) Long idPlan) {
        return requisitoPracticasService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public RequisitoPracticasResponse obtener(@PathVariable Long id) {
        return requisitoPracticasService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequisitoPracticasResponse crear(@Valid @RequestBody RequisitoPracticasRequest request) {
        return requisitoPracticasService.crear(request);
    }

    @PutMapping("/{id}")
    public RequisitoPracticasResponse actualizar(@PathVariable Long id, @Valid @RequestBody RequisitoPracticasRequest request) {
        return requisitoPracticasService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        requisitoPracticasService.eliminar(id);
    }
}
