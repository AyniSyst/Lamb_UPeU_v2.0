package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoExtracurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoExtracurricularResponse;
import pe.edu.upeu.lamb.curriculo.service.RequisitoExtracurricularService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Requisito extracurricular", description = "CRUD 37 · Requisito de actividades extracurriculares (creditos exigidos por tipo de actividad)")
@RestController
@RequestMapping("/api/v1/requisitos-extracurriculares")
@RequiredArgsConstructor
public class RequisitoExtracurricularController {

    private final RequisitoExtracurricularService requisitoExtracurricularService;

    @GetMapping
    public List<RequisitoExtracurricularResponse> listar(@RequestParam(required = false) Long idPlan) {
        return requisitoExtracurricularService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public RequisitoExtracurricularResponse obtener(@PathVariable Long id) {
        return requisitoExtracurricularService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequisitoExtracurricularResponse crear(@Valid @RequestBody RequisitoExtracurricularRequest request) {
        return requisitoExtracurricularService.crear(request);
    }

    @PutMapping("/{id}")
    public RequisitoExtracurricularResponse actualizar(@PathVariable Long id, @Valid @RequestBody RequisitoExtracurricularRequest request) {
        return requisitoExtracurricularService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        requisitoExtracurricularService.eliminar(id);
    }
}
