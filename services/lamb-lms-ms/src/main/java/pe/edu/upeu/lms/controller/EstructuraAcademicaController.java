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
public class EstructuraAcademicaController {

    private final CursoService cursoService;
    private final DocenteService docenteService;
    private final ModalidadCursoService modalidadCursoService;
    private final PeriodoAcademicoService periodoAcademicoService;
    private final EstadoMatriculaCatalogoService estadoMatriculaCatalogoService;
    private final CategoriaCursoService categoriaCursoService;
    private final MallaCurricularService mallaCurricularService;
    private final PrerequisitoCursoService prerequisitoCursoService;
    private final SeccionCursoService seccionCursoService;
    private final HorarioClaseService horarioClaseService;
    private final SesionClaseService sesionClaseService;

    // ---- Cursos ----

    @GetMapping("/cursos")
    public List<CursoResponse> listarCursos() {
        log.info("Listando cursos");
        return cursoService.listar();
    }

    @GetMapping("/cursos/{id}")
    public CursoResponse obtenerCurso(@PathVariable Long id) {
        return cursoService.obtener(id);
    }

    @PostMapping("/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public CursoResponse crearCurso(@Valid @RequestBody CursoRequest request) {
        return cursoService.crear(request);
    }

    @PutMapping("/cursos/{id}")
    public CursoResponse actualizarCurso(@PathVariable Long id, @Valid @RequestBody CursoRequest request) {
        return cursoService.actualizar(id, request);
    }

    @DeleteMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCurso(@PathVariable Long id) {
        cursoService.eliminar(id);
    }

    // ---- Docentes ----

    @GetMapping("/docentes")
    public List<DocenteResponse> listarDocentes() {
        return docenteService.listar();
    }

    @GetMapping("/docentes/{id}")
    public DocenteResponse obtenerDocente(@PathVariable Long id) {
        return docenteService.obtener(id);
    }

    @PostMapping("/docentes")
    @ResponseStatus(HttpStatus.CREATED)
    public DocenteResponse crearDocente(@Valid @RequestBody DocenteRequest request) {
        return docenteService.crear(request);
    }

    @PutMapping("/docentes/{id}")
    public DocenteResponse actualizarDocente(@PathVariable Long id, @Valid @RequestBody DocenteRequest request) {
        return docenteService.actualizar(id, request);
    }

    @DeleteMapping("/docentes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDocente(@PathVariable Long id) {
        docenteService.eliminar(id);
    }

    // ---- Modalidades de curso ----

    @GetMapping("/modalidades-curso")
    public List<ModalidadCursoResponse> listarModalidadesCurso() {
        return modalidadCursoService.listar();
    }

    @GetMapping("/modalidades-curso/{id}")
    public ModalidadCursoResponse obtenerModalidadCurso(@PathVariable Long id) {
        return modalidadCursoService.obtener(id);
    }

    @PostMapping("/modalidades-curso")
    @ResponseStatus(HttpStatus.CREATED)
    public ModalidadCursoResponse crearModalidadCurso(@Valid @RequestBody ModalidadCursoRequest request) {
        return modalidadCursoService.crear(request);
    }

    @PutMapping("/modalidades-curso/{id}")
    public ModalidadCursoResponse actualizarModalidadCurso(@PathVariable Long id, @Valid @RequestBody ModalidadCursoRequest request) {
        return modalidadCursoService.actualizar(id, request);
    }

    @DeleteMapping("/modalidades-curso/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarModalidadCurso(@PathVariable Long id) {
        modalidadCursoService.eliminar(id);
    }

    // ---- Periodos academicos ----

    @GetMapping("/periodos-academicos")
    public List<PeriodoAcademicoResponse> listarPeriodosAcademicos() {
        return periodoAcademicoService.listar();
    }

    @GetMapping("/periodos-academicos/{id}")
    public PeriodoAcademicoResponse obtenerPeriodoAcademico(@PathVariable Long id) {
        return periodoAcademicoService.obtener(id);
    }

    @PostMapping("/periodos-academicos")
    @ResponseStatus(HttpStatus.CREATED)
    public PeriodoAcademicoResponse crearPeriodoAcademico(@Valid @RequestBody PeriodoAcademicoRequest request) {
        return periodoAcademicoService.crear(request);
    }

    @PutMapping("/periodos-academicos/{id}")
    public PeriodoAcademicoResponse actualizarPeriodoAcademico(@PathVariable Long id, @Valid @RequestBody PeriodoAcademicoRequest request) {
        return periodoAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/periodos-academicos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPeriodoAcademico(@PathVariable Long id) {
        periodoAcademicoService.eliminar(id);
    }

    // ---- Estados de matricula (catalogo) ----

    @GetMapping("/estados-matricula")
    public List<EstadoMatriculaCatalogoResponse> listarEstadosMatricula() {
        return estadoMatriculaCatalogoService.listar();
    }

    @GetMapping("/estados-matricula/{id}")
    public EstadoMatriculaCatalogoResponse obtenerEstadoMatricula(@PathVariable Long id) {
        return estadoMatriculaCatalogoService.obtener(id);
    }

    @PostMapping("/estados-matricula")
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoMatriculaCatalogoResponse crearEstadoMatricula(@Valid @RequestBody EstadoMatriculaCatalogoRequest request) {
        return estadoMatriculaCatalogoService.crear(request);
    }

    @PutMapping("/estados-matricula/{id}")
    public EstadoMatriculaCatalogoResponse actualizarEstadoMatricula(@PathVariable Long id, @Valid @RequestBody EstadoMatriculaCatalogoRequest request) {
        return estadoMatriculaCatalogoService.actualizar(id, request);
    }

    @DeleteMapping("/estados-matricula/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEstadoMatricula(@PathVariable Long id) {
        estadoMatriculaCatalogoService.eliminar(id);
    }

    // ---- Categorias de curso ----

    @GetMapping("/categorias-curso")
    public List<CategoriaCursoResponse> listarCategoriasCurso() {
        return categoriaCursoService.listar();
    }

    @GetMapping("/categorias-curso/{id}")
    public CategoriaCursoResponse obtenerCategoriaCurso(@PathVariable Long id) {
        return categoriaCursoService.obtener(id);
    }

    @PostMapping("/categorias-curso")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaCursoResponse crearCategoriaCurso(@Valid @RequestBody CategoriaCursoRequest request) {
        return categoriaCursoService.crear(request);
    }

    @PutMapping("/categorias-curso/{id}")
    public CategoriaCursoResponse actualizarCategoriaCurso(@PathVariable Long id, @Valid @RequestBody CategoriaCursoRequest request) {
        return categoriaCursoService.actualizar(id, request);
    }

    @DeleteMapping("/categorias-curso/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCategoriaCurso(@PathVariable Long id) {
        categoriaCursoService.eliminar(id);
    }

    // ---- Mallas curriculares ----

    @GetMapping("/mallas-curriculares")
    public List<MallaCurricularResponse> listarMallasCurriculares() {
        return mallaCurricularService.listar();
    }

    @GetMapping("/mallas-curriculares/{id}")
    public MallaCurricularResponse obtenerMallaCurricular(@PathVariable Long id) {
        return mallaCurricularService.obtener(id);
    }

    @PostMapping("/mallas-curriculares")
    @ResponseStatus(HttpStatus.CREATED)
    public MallaCurricularResponse crearMallaCurricular(@Valid @RequestBody MallaCurricularRequest request) {
        return mallaCurricularService.crear(request);
    }

    @PutMapping("/mallas-curriculares/{id}")
    public MallaCurricularResponse actualizarMallaCurricular(@PathVariable Long id, @Valid @RequestBody MallaCurricularRequest request) {
        return mallaCurricularService.actualizar(id, request);
    }

    @DeleteMapping("/mallas-curriculares/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMallaCurricular(@PathVariable Long id) {
        mallaCurricularService.eliminar(id);
    }

    // ---- Prerequisitos de curso ----

    @GetMapping("/prerequisitos-curso")
    public List<PrerequisitoCursoResponse> listarPrerequisitosCurso() {
        return prerequisitoCursoService.listar();
    }

    @GetMapping("/prerequisitos-curso/{id}")
    public PrerequisitoCursoResponse obtenerPrerequisitoCurso(@PathVariable Long id) {
        return prerequisitoCursoService.obtener(id);
    }

    @PostMapping("/prerequisitos-curso")
    @ResponseStatus(HttpStatus.CREATED)
    public PrerequisitoCursoResponse crearPrerequisitoCurso(@Valid @RequestBody PrerequisitoCursoRequest request) {
        return prerequisitoCursoService.crear(request);
    }

    @PutMapping("/prerequisitos-curso/{id}")
    public PrerequisitoCursoResponse actualizarPrerequisitoCurso(@PathVariable Long id, @Valid @RequestBody PrerequisitoCursoRequest request) {
        return prerequisitoCursoService.actualizar(id, request);
    }

    @DeleteMapping("/prerequisitos-curso/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPrerequisitoCurso(@PathVariable Long id) {
        prerequisitoCursoService.eliminar(id);
    }

    // ---- Secciones de curso ----

    @GetMapping("/secciones-curso")
    public List<SeccionCursoResponse> listarSeccionesCurso() {
        return seccionCursoService.listar();
    }

    @GetMapping("/secciones-curso/{id}")
    public SeccionCursoResponse obtenerSeccionCurso(@PathVariable Long id) {
        return seccionCursoService.obtener(id);
    }

    @PostMapping("/secciones-curso")
    @ResponseStatus(HttpStatus.CREATED)
    public SeccionCursoResponse crearSeccionCurso(@Valid @RequestBody SeccionCursoRequest request) {
        return seccionCursoService.crear(request);
    }

    @PutMapping("/secciones-curso/{id}")
    public SeccionCursoResponse actualizarSeccionCurso(@PathVariable Long id, @Valid @RequestBody SeccionCursoRequest request) {
        return seccionCursoService.actualizar(id, request);
    }

    @DeleteMapping("/secciones-curso/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarSeccionCurso(@PathVariable Long id) {
        seccionCursoService.eliminar(id);
    }

    // ---- Horarios de clase ----

    @GetMapping("/horarios-clase")
    public List<HorarioClaseResponse> listarHorariosClase() {
        return horarioClaseService.listar();
    }

    @GetMapping("/horarios-clase/{id}")
    public HorarioClaseResponse obtenerHorarioClase(@PathVariable Long id) {
        return horarioClaseService.obtener(id);
    }

    @PostMapping("/horarios-clase")
    @ResponseStatus(HttpStatus.CREATED)
    public HorarioClaseResponse crearHorarioClase(@Valid @RequestBody HorarioClaseRequest request) {
        return horarioClaseService.crear(request);
    }

    @PutMapping("/horarios-clase/{id}")
    public HorarioClaseResponse actualizarHorarioClase(@PathVariable Long id, @Valid @RequestBody HorarioClaseRequest request) {
        return horarioClaseService.actualizar(id, request);
    }

    @DeleteMapping("/horarios-clase/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarHorarioClase(@PathVariable Long id) {
        horarioClaseService.eliminar(id);
    }

    // ---- Sesiones de clase ----

    @GetMapping("/sesiones-clase")
    public List<SesionClaseResponse> listarSesionesClase() {
        return sesionClaseService.listar();
    }

    @GetMapping("/sesiones-clase/{id}")
    public SesionClaseResponse obtenerSesionClase(@PathVariable Long id) {
        return sesionClaseService.obtener(id);
    }

    @PostMapping("/sesiones-clase")
    @ResponseStatus(HttpStatus.CREATED)
    public SesionClaseResponse crearSesionClase(@Valid @RequestBody SesionClaseRequest request) {
        return sesionClaseService.crear(request);
    }

    @PutMapping("/sesiones-clase/{id}")
    public SesionClaseResponse actualizarSesionClase(@PathVariable Long id, @Valid @RequestBody SesionClaseRequest request) {
        return sesionClaseService.actualizar(id, request);
    }

    @DeleteMapping("/sesiones-clase/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarSesionClase(@PathVariable Long id) {
        sesionClaseService.eliminar(id);
    }
}
