package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.EjeCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.EjeCurricularResponse;
import pe.edu.upeu.lamb.curriculo.service.EjeCurricularService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Eje curricular", description = "CRUD 22 · Eje / linea curricular")
@RestController
@RequestMapping("/api/v1/ejes-curriculares")
@RequiredArgsConstructor
public class EjeCurricularController {

    private final EjeCurricularService ejeCurricularService;

    @GetMapping
    public List<EjeCurricularResponse> listar(@RequestParam(required = false) Long idPlan) {
        return ejeCurricularService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public EjeCurricularResponse obtener(@PathVariable Long id) {
        return ejeCurricularService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EjeCurricularResponse crear(@Valid @RequestBody EjeCurricularRequest request) {
        return ejeCurricularService.crear(request);
    }

    @PutMapping("/{id}")
    public EjeCurricularResponse actualizar(@PathVariable Long id, @Valid @RequestBody EjeCurricularRequest request) {
        return ejeCurricularService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        ejeCurricularService.eliminar(id);
    }
}
