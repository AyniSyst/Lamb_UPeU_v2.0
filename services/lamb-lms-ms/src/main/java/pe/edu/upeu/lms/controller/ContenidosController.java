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
public class ContenidosController {

    private final ContenidoService contenidoService;
    private final RecursoComplementarioService recursoComplementarioService;
    private final AnuncioCursoService anuncioCursoService;

    // ---- Contenidos ----

    @GetMapping("/contenidos")
    public List<ContenidoResponse> listarContenidos() {
        return contenidoService.listar();
    }

    @GetMapping("/contenidos/{id}")
    public ContenidoResponse obtenerContenido(@PathVariable Long id) {
        return contenidoService.obtener(id);
    }

    @PostMapping("/contenidos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContenidoResponse crearContenido(@Valid @RequestBody ContenidoRequest request) {
        return contenidoService.crear(request);
    }

    @PutMapping("/contenidos/{id}")
    public ContenidoResponse actualizarContenido(@PathVariable Long id, @Valid @RequestBody ContenidoRequest request) {
        return contenidoService.actualizar(id, request);
    }

    @DeleteMapping("/contenidos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarContenido(@PathVariable Long id) {
        contenidoService.eliminar(id);
    }

    // ---- Recursos complementarios ----

    @GetMapping("/recursos-complementarios")
    public List<RecursoComplementarioResponse> listarRecursosComplementarios() {
        return recursoComplementarioService.listar();
    }

    @GetMapping("/recursos-complementarios/{id}")
    public RecursoComplementarioResponse obtenerRecursoComplementario(@PathVariable Long id) {
        return recursoComplementarioService.obtener(id);
    }

    @PostMapping("/recursos-complementarios")
    @ResponseStatus(HttpStatus.CREATED)
    public RecursoComplementarioResponse crearRecursoComplementario(@Valid @RequestBody RecursoComplementarioRequest request) {
        return recursoComplementarioService.crear(request);
    }

    @PutMapping("/recursos-complementarios/{id}")
    public RecursoComplementarioResponse actualizarRecursoComplementario(@PathVariable Long id, @Valid @RequestBody RecursoComplementarioRequest request) {
        return recursoComplementarioService.actualizar(id, request);
    }

    @DeleteMapping("/recursos-complementarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarRecursoComplementario(@PathVariable Long id) {
        recursoComplementarioService.eliminar(id);
    }

    // ---- Anuncios de curso ----

    @GetMapping("/anuncios-curso")
    public List<AnuncioCursoResponse> listarAnunciosCurso() {
        return anuncioCursoService.listar();
    }

    @GetMapping("/anuncios-curso/{id}")
    public AnuncioCursoResponse obtenerAnuncioCurso(@PathVariable Long id) {
        return anuncioCursoService.obtener(id);
    }

    @PostMapping("/anuncios-curso")
    @ResponseStatus(HttpStatus.CREATED)
    public AnuncioCursoResponse crearAnuncioCurso(@Valid @RequestBody AnuncioCursoRequest request) {
        return anuncioCursoService.crear(request);
    }

    @PutMapping("/anuncios-curso/{id}")
    public AnuncioCursoResponse actualizarAnuncioCurso(@PathVariable Long id, @Valid @RequestBody AnuncioCursoRequest request) {
        return anuncioCursoService.actualizar(id, request);
    }

    @DeleteMapping("/anuncios-curso/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAnuncioCurso(@PathVariable Long id) {
        anuncioCursoService.eliminar(id);
    }
}
