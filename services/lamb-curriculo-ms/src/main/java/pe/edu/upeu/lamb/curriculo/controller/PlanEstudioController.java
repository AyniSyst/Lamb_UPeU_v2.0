package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.PlanCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanEstudioRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanEstudioResponse;
import pe.edu.upeu.lamb.curriculo.dto.PrerrequisitoRequest;
import pe.edu.upeu.lamb.curriculo.dto.VigenciaCursoResponse;
import pe.edu.upeu.lamb.curriculo.service.PlanEstudioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
