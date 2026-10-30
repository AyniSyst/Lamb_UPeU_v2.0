package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.*;
import pe.edu.upeu.lms.service.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/lms")
@RequiredArgsConstructor
public class EvaluacionesController {

    private final EvaluacionService evaluacionService;
    private final PreguntaService preguntaService;
    private final CalificacionService calificacionService;
    private final RubricaEvaluacionService rubricaEvaluacionService;
    private final PreguntaBancoService preguntaBancoService;

    // ---- Evaluaciones ----

    @GetMapping("/evaluaciones")
    public List<EvaluacionResponse> listarEvaluaciones() {
        return evaluacionService.listar();
    }

    @GetMapping("/evaluaciones/{id}")
    public EvaluacionResponse obtenerEvaluacion(@PathVariable Long id) {
        return evaluacionService.obtener(id);
    }

    @PostMapping("/evaluaciones")
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluacionResponse crearEvaluacion(@Valid @RequestBody EvaluacionRequest request) {
        return evaluacionService.crear(request);
    }

    @PutMapping("/evaluaciones/{id}")
    public EvaluacionResponse actualizarEvaluacion(@PathVariable Long id, @Valid @RequestBody EvaluacionRequest request) {
        return evaluacionService.actualizar(id, request);
    }

    @DeleteMapping("/evaluaciones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEvaluacion(@PathVariable Long id) {
        evaluacionService.eliminar(id);
    }

    // ---- Preguntas ----

    @GetMapping("/preguntas")
    public List<PreguntaResponse> listarPreguntas() {
        return preguntaService.listar();
    }

    @GetMapping("/preguntas/{id}")
    public PreguntaResponse obtenerPregunta(@PathVariable Long id) {
        return preguntaService.obtener(id);
    }

    @PostMapping("/preguntas")
    @ResponseStatus(HttpStatus.CREATED)
    public PreguntaResponse crearPregunta(@Valid @RequestBody PreguntaRequest request) {
        return preguntaService.crear(request);
    }

    @PutMapping("/preguntas/{id}")
    public PreguntaResponse actualizarPregunta(@PathVariable Long id, @Valid @RequestBody PreguntaRequest request) {
        return preguntaService.actualizar(id, request);
    }

    @DeleteMapping("/preguntas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPregunta(@PathVariable Long id) {
        preguntaService.eliminar(id);
    }

    // ---- Calificaciones ----

    @GetMapping("/calificaciones")
    public List<CalificacionResponse> listarCalificaciones() {
        return calificacionService.listar();
    }

    @GetMapping("/calificaciones/{id}")
    public CalificacionResponse obtenerCalificacion(@PathVariable Long id) {
        return calificacionService.obtener(id);
    }

    @PostMapping("/calificaciones")
    @ResponseStatus(HttpStatus.CREATED)
    public CalificacionResponse crearCalificacion(@Valid @RequestBody CalificacionRequest request) {
        return calificacionService.crear(request);
    }

    @PutMapping("/calificaciones/{id}")
    public CalificacionResponse actualizarCalificacion(@PathVariable Long id, @Valid @RequestBody CalificacionRequest request) {
        return calificacionService.actualizar(id, request);
    }

    @DeleteMapping("/calificaciones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCalificacion(@PathVariable Long id) {
        calificacionService.eliminar(id);
    }

    // ---- Rubricas de evaluacion ----

    @GetMapping("/rubricas-evaluacion")
    public List<RubricaEvaluacionResponse> listarRubricasEvaluacion() {
        return rubricaEvaluacionService.listar();
    }

    @GetMapping("/rubricas-evaluacion/{id}")
    public RubricaEvaluacionResponse obtenerRubricaEvaluacion(@PathVariable Long id) {
        return rubricaEvaluacionService.obtener(id);
    }

    @PostMapping("/rubricas-evaluacion")
    @ResponseStatus(HttpStatus.CREATED)
    public RubricaEvaluacionResponse crearRubricaEvaluacion(@Valid @RequestBody RubricaEvaluacionRequest request) {
        return rubricaEvaluacionService.crear(request);
    }

    @PutMapping("/rubricas-evaluacion/{id}")
    public RubricaEvaluacionResponse actualizarRubricaEvaluacion(@PathVariable Long id, @Valid @RequestBody RubricaEvaluacionRequest request) {
        return rubricaEvaluacionService.actualizar(id, request);
    }

    @DeleteMapping("/rubricas-evaluacion/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarRubricaEvaluacion(@PathVariable Long id) {
        rubricaEvaluacionService.eliminar(id);
    }

    // ---- Preguntas banco ----

    @GetMapping("/preguntas-banco")
    public List<PreguntaBancoResponse> listarPreguntasBanco() {
        return preguntaBancoService.listar();
    }

    @GetMapping("/preguntas-banco/{id}")
    public PreguntaBancoResponse obtenerPreguntaBanco(@PathVariable Long id) {
        return preguntaBancoService.obtener(id);
    }

    @PostMapping("/preguntas-banco")
    @ResponseStatus(HttpStatus.CREATED)
    public PreguntaBancoResponse crearPreguntaBanco(@Valid @RequestBody PreguntaBancoRequest request) {
        return preguntaBancoService.crear(request);
    }

    @PutMapping("/preguntas-banco/{id}")
    public PreguntaBancoResponse actualizarPreguntaBanco(@PathVariable Long id, @Valid @RequestBody PreguntaBancoRequest request) {
        return preguntaBancoService.actualizar(id, request);
    }

    @DeleteMapping("/preguntas-banco/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPreguntaBanco(@PathVariable Long id) {
        preguntaBancoService.eliminar(id);
    }
}
