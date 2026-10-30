package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.DepartamentoAcademicoRequest;
import pe.edu.upeu.lamb.curriculo.dto.DepartamentoAcademicoResponse;
import pe.edu.upeu.lamb.curriculo.service.DepartamentoAcademicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Departamento academico", description = "CRUD 11 · Departamento academico responsable (jala el id del plan de estudios, CRUD 5)")
@RestController
@RequestMapping("/api/v1/departamentos-academicos")
@RequiredArgsConstructor
public class DepartamentoAcademicoController {

    private final DepartamentoAcademicoService departamentoAcademicoService;

    @GetMapping
    public List<DepartamentoAcademicoResponse> listar(@RequestParam(required = false) Long idPlan) {
        return departamentoAcademicoService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public DepartamentoAcademicoResponse obtener(@PathVariable Long id) {
        return departamentoAcademicoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartamentoAcademicoResponse crear(@Valid @RequestBody DepartamentoAcademicoRequest request) {
        return departamentoAcademicoService.crear(request);
    }

    @PutMapping("/{id}")
    public DepartamentoAcademicoResponse actualizar(@PathVariable Long id, @Valid @RequestBody DepartamentoAcademicoRequest request) {
        return departamentoAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        departamentoAcademicoService.eliminar(id);
    }
}
