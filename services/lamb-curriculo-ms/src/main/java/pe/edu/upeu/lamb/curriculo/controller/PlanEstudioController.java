package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.PlanCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanEstudioRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanEstudioResponse;
import pe.edu.upeu.lamb.curriculo.dto.PrerrequisitoRequest;
import pe.edu.upeu.lamb.curriculo.dto.VigenciaCursoResponse;
import pe.edu.upeu.lamb.curriculo.service.PlanEstudioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan de estudios", description = "CRUD 5 · Plan de estudios con sus cursos (PlanCurso) y prerrequisitos")
@RestController
@RequestMapping("/api/v1/planes-estudio")
@RequiredArgsConstructor
public class PlanEstudioController {

    private final PlanEstudioService planEstudioService;

    @GetMapping
    public List<PlanEstudioResponse> listar(@RequestParam(required = false) Long idPrograma) {
        return planEstudioService.listar(idPrograma);
    }

    @GetMapping("/{id}")
    public PlanEstudioResponse obtener(@PathVariable Long id) {
        return planEstudioService.obtener(id);
    }

    @GetMapping("/programa/{idPrograma}/vigente")
    public PlanEstudioResponse obtenerVigente(@PathVariable Long idPrograma) {
        return planEstudioService.obtenerVigente(idPrograma);
    }

    @GetMapping("/cursos/{codigoCurso}/vigencia")
    public VigenciaCursoResponse consultarVigencia(@PathVariable String codigoCurso) {
        return planEstudioService.consultarVigencia(codigoCurso);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanEstudioResponse crear(@Valid @RequestBody PlanEstudioRequest request) {
        return planEstudioService.crear(request);
    }

    @PutMapping("/{id}")
    public PlanEstudioResponse actualizar(@PathVariable Long id, @Valid @RequestBody PlanEstudioRequest request) {
        return planEstudioService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        planEstudioService.eliminar(id);
    }

    @PostMapping("/{id}/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public PlanEstudioResponse agregarCurso(@PathVariable Long id, @Valid @RequestBody PlanCursoRequest request) {
        return planEstudioService.agregarCurso(id, request);
    }

    @DeleteMapping("/{id}/cursos/{idPlanCurso}")
    public PlanEstudioResponse eliminarCurso(@PathVariable Long id, @PathVariable Long idPlanCurso) {
        return planEstudioService.eliminarCurso(id, idPlanCurso);
    }

    @PostMapping("/{id}/cursos/{idPlanCurso}/prerrequisitos")
    @ResponseStatus(HttpStatus.CREATED)
    public PlanEstudioResponse agregarPrerrequisito(@PathVariable Long id,
                                                    @PathVariable Long idPlanCurso,
                                                    @Valid @RequestBody PrerrequisitoRequest request) {
        return planEstudioService.agregarPrerrequisito(id, idPlanCurso, request);
    }

    @DeleteMapping("/{id}/cursos/{idPlanCurso}/prerrequisitos/{idPlanCursoRequisito}")
    public PlanEstudioResponse eliminarPrerrequisito(@PathVariable Long id,
                                                     @PathVariable Long idPlanCurso,
                                                     @PathVariable Long idPlanCursoRequisito) {
        return planEstudioService.eliminarPrerrequisito(id, idPlanCurso, idPlanCursoRequisito);
    }

    @PostMapping("/{id}/publicar")
    public PlanEstudioResponse publicar(@PathVariable Long id) {
        return planEstudioService.publicar(id);
    }
}
