package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.*;
import pe.edu.upeu.lms.service.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/lms")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final ListaEsperaService listaEsperaService;
    private final ConvalidacionCursoService convalidacionCursoService;

    // ---- Matriculas ----

    @PostMapping("/matriculas/enrolar")
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponse enrolar(@Valid @RequestBody MatriculaRequest request,
                                      @AuthenticationPrincipal Jwt jwt) {
        String idEstudiante = jwt.getSubject();
        return matriculaService.crear(request, idEstudiante);
    }

    @GetMapping("/matriculas")
    public List<MatriculaResponse> listar() {
        return matriculaService.listar();
    }

    @GetMapping("/matriculas/{id}")
    public MatriculaResponse findById(@PathVariable Long id) {
        return matriculaService.findById(id);
    }

    // ---- Listas de espera ----

    @GetMapping("/listas-espera")
    public List<ListaEsperaResponse> listarListasEspera() {
        return listaEsperaService.listar();
    }

    @GetMapping("/listas-espera/{id}")
    public ListaEsperaResponse obtenerListaEspera(@PathVariable Long id) {
        return listaEsperaService.obtener(id);
    }

    @PostMapping("/listas-espera")
    @ResponseStatus(HttpStatus.CREATED)
    public ListaEsperaResponse crearListaEspera(@Valid @RequestBody ListaEsperaRequest request) {
        return listaEsperaService.crear(request);
    }

    @PutMapping("/listas-espera/{id}")
    public ListaEsperaResponse actualizarListaEspera(@PathVariable Long id, @Valid @RequestBody ListaEsperaRequest request) {
        return listaEsperaService.actualizar(id, request);
    }

    @DeleteMapping("/listas-espera/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarListaEspera(@PathVariable Long id) {
        listaEsperaService.eliminar(id);
    }

    // ---- Convalidaciones de curso ----

    @GetMapping("/convalidaciones-curso")
    public List<ConvalidacionCursoResponse> listarConvalidacionesCurso() {
        return convalidacionCursoService.listar();
    }

    @GetMapping("/convalidaciones-curso/{id}")
    public ConvalidacionCursoResponse obtenerConvalidacionCurso(@PathVariable Long id) {
        return convalidacionCursoService.obtener(id);
    }

    @PostMapping("/convalidaciones-curso")
    @ResponseStatus(HttpStatus.CREATED)
    public ConvalidacionCursoResponse crearConvalidacionCurso(@Valid @RequestBody ConvalidacionCursoRequest request) {
        return convalidacionCursoService.crear(request);
    }

    @PutMapping("/convalidaciones-curso/{id}")
    public ConvalidacionCursoResponse actualizarConvalidacionCurso(@PathVariable Long id, @Valid @RequestBody ConvalidacionCursoRequest request) {
        return convalidacionCursoService.actualizar(id, request);
    }

    @DeleteMapping("/convalidaciones-curso/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarConvalidacionCurso(@PathVariable Long id) {
        convalidacionCursoService.eliminar(id);
    }
}
