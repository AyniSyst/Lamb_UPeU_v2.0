package pe.edu.upeu.lamb.institucional.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.institucional.dto.*;
import pe.edu.upeu.lamb.institucional.service.*;

import java.util.List;

/**
 * Dominio "Facultad y Escuela" (CRUD unificado). Subrecursos: facultad,
 * escuela profesional, departamento academico, autoridad de facultad,
 * cambio de unidad academica, unidad administrativa y organigrama
 * institucional.
 */
@RestController
@RequestMapping("/api/v1/facultad-escuela")
@RequiredArgsConstructor
@Tag(name = "facultad-escuela-controller")
public class FacultadEscuelaController {

    private final FacultadService facultadService;
    private final EscuelaProfesionalService escuelaProfesionalService;
    private final DepartamentoAcademicoService departamentoAcademicoService;
    private final AutoridadFacultadService autoridadFacultadService;
    private final CambioUnidadAcademicaService cambioUnidadAcademicaService;
    private final UnidadAdministrativaService unidadAdministrativaService;
    private final OrganigramaInstitucionalService organigramaInstitucionalService;

    // --- Facultad (item 12, existente migrado) ---

    @GetMapping("/facultades")
    @Operation(summary = "Listar facultades")
    public List<FacultadResponse> listarFacultades() {
        return facultadService.listar();
    }

    @GetMapping("/facultades/{id}")
    @Operation(summary = "Obtener una facultad")
    public FacultadResponse obtenerFacultad(@PathVariable Long id) {
        return facultadService.obtener(id);
    }

    @PostMapping("/facultades")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una facultad")
    public FacultadResponse crearFacultad(@Valid @RequestBody FacultadRequest request) {
        return facultadService.crear(request);
    }

    // --- Escuela profesional (item 13) ---

    @GetMapping("/escuelas-profesionales")
    @Operation(summary = "Listar escuelas profesionales")
    public List<EscuelaProfesionalResponse> listarEscuelasProfesionales() {
        return escuelaProfesionalService.listar();
    }

    @GetMapping("/escuelas-profesionales/{id}")
    @Operation(summary = "Obtener una escuela profesional")
    public EscuelaProfesionalResponse obtenerEscuelaProfesional(@PathVariable Long id) {
        return escuelaProfesionalService.obtener(id);
    }

    @PostMapping("/escuelas-profesionales")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una escuela profesional")
    public EscuelaProfesionalResponse crearEscuelaProfesional(@Valid @RequestBody EscuelaProfesionalRequest request) {
        return escuelaProfesionalService.crear(request);
    }

    @PutMapping("/escuelas-profesionales/{id}")
    public EscuelaProfesionalResponse actualizarEscuelaProfesional(@PathVariable Long id, @Valid @RequestBody EscuelaProfesionalRequest request) {
        return escuelaProfesionalService.actualizar(id, request);
    }

    @DeleteMapping("/escuelas-profesionales/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEscuelaProfesional(@PathVariable Long id) {
        escuelaProfesionalService.eliminar(id);
    }

    // --- Departamento academico (item 14) ---

    @GetMapping("/departamentos-academicos")
    @Operation(summary = "Listar departamentos academicos")
    public List<DepartamentoAcademicoResponse> listarDepartamentosAcademicos() {
        return departamentoAcademicoService.listar();
    }

    @GetMapping("/departamentos-academicos/{id}")
    @Operation(summary = "Obtener un departamento academico")
    public DepartamentoAcademicoResponse obtenerDepartamentoAcademico(@PathVariable Long id) {
        return departamentoAcademicoService.obtener(id);
    }

    @PostMapping("/departamentos-academicos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un departamento academico")
    public DepartamentoAcademicoResponse crearDepartamentoAcademico(@Valid @RequestBody DepartamentoAcademicoRequest request) {
        return departamentoAcademicoService.crear(request);
    }

    @PutMapping("/departamentos-academicos/{id}")
    public DepartamentoAcademicoResponse actualizarDepartamentoAcademico(@PathVariable Long id, @Valid @RequestBody DepartamentoAcademicoRequest request) {
        return departamentoAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/departamentos-academicos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDepartamentoAcademico(@PathVariable Long id) {
        departamentoAcademicoService.eliminar(id);
    }

    // --- Autoridad de facultad (item 15) ---

    @GetMapping("/autoridades-facultad")
    @Operation(summary = "Listar autoridades de facultad")
    public List<AutoridadFacultadResponse> listarAutoridadesFacultad() {
        return autoridadFacultadService.listar();
    }

    @GetMapping("/autoridades-facultad/{id}")
    @Operation(summary = "Obtener una autoridad de facultad")
    public AutoridadFacultadResponse obtenerAutoridadFacultad(@PathVariable Long id) {
        return autoridadFacultadService.obtener(id);
    }

    @PostMapping("/autoridades-facultad")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una autoridad de facultad")
    public AutoridadFacultadResponse crearAutoridadFacultad(@Valid @RequestBody AutoridadFacultadRequest request) {
        return autoridadFacultadService.crear(request);
    }

    @PutMapping("/autoridades-facultad/{id}")
    public AutoridadFacultadResponse actualizarAutoridadFacultad(@PathVariable Long id, @Valid @RequestBody AutoridadFacultadRequest request) {
        return autoridadFacultadService.actualizar(id, request);
    }

    @DeleteMapping("/autoridades-facultad/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAutoridadFacultad(@PathVariable Long id) {
        autoridadFacultadService.eliminar(id);
    }

    // --- Cambio de unidad academica (item 16) ---

    @GetMapping("/cambios-unidad-academica")
    @Operation(summary = "Listar cambios de unidad academica")
    public List<CambioUnidadAcademicaResponse> listarCambiosUnidadAcademica() {
        return cambioUnidadAcademicaService.listar();
    }

    @GetMapping("/cambios-unidad-academica/{id}")
    @Operation(summary = "Obtener un cambio de unidad academica")
    public CambioUnidadAcademicaResponse obtenerCambioUnidadAcademica(@PathVariable Long id) {
        return cambioUnidadAcademicaService.obtener(id);
    }

    @PostMapping("/cambios-unidad-academica")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un cambio de unidad academica")
    public CambioUnidadAcademicaResponse crearCambioUnidadAcademica(@Valid @RequestBody CambioUnidadAcademicaRequest request) {
        return cambioUnidadAcademicaService.crear(request);
    }

    @PutMapping("/cambios-unidad-academica/{id}")
    public CambioUnidadAcademicaResponse actualizarCambioUnidadAcademica(@PathVariable Long id, @Valid @RequestBody CambioUnidadAcademicaRequest request) {
        return cambioUnidadAcademicaService.actualizar(id, request);
    }

    @DeleteMapping("/cambios-unidad-academica/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCambioUnidadAcademica(@PathVariable Long id) {
        cambioUnidadAcademicaService.eliminar(id);
    }

    // --- Unidad administrativa (item 17, opcional) ---

    @GetMapping("/unidades-administrativas")
    @Operation(summary = "Listar unidades administrativas")
    public List<UnidadAdministrativaResponse> listarUnidadesAdministrativas() {
        return unidadAdministrativaService.listar();
    }

    @GetMapping("/unidades-administrativas/{id}")
    @Operation(summary = "Obtener una unidad administrativa")
    public UnidadAdministrativaResponse obtenerUnidadAdministrativa(@PathVariable Long id) {
        return unidadAdministrativaService.obtener(id);
    }

    @PostMapping("/unidades-administrativas")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una unidad administrativa")
    public UnidadAdministrativaResponse crearUnidadAdministrativa(@Valid @RequestBody UnidadAdministrativaRequest request) {
        return unidadAdministrativaService.crear(request);
    }

    @PutMapping("/unidades-administrativas/{id}")
    public UnidadAdministrativaResponse actualizarUnidadAdministrativa(@PathVariable Long id, @Valid @RequestBody UnidadAdministrativaRequest request) {
        return unidadAdministrativaService.actualizar(id, request);
    }

    @DeleteMapping("/unidades-administrativas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUnidadAdministrativa(@PathVariable Long id) {
        unidadAdministrativaService.eliminar(id);
    }

    // --- Organigrama institucional (item 18, opcional) ---

    @GetMapping("/organigramas")
    @Operation(summary = "Listar organigramas institucionales")
    public List<OrganigramaInstitucionalResponse> listarOrganigramas() {
        return organigramaInstitucionalService.listar();
    }

    @GetMapping("/organigramas/{id}")
    @Operation(summary = "Obtener un organigrama institucional")
    public OrganigramaInstitucionalResponse obtenerOrganigrama(@PathVariable Long id) {
        return organigramaInstitucionalService.obtener(id);
    }

    @PostMapping("/organigramas")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un organigrama institucional")
    public OrganigramaInstitucionalResponse crearOrganigrama(@Valid @RequestBody OrganigramaInstitucionalRequest request) {
        return organigramaInstitucionalService.crear(request);
    }

    @PutMapping("/organigramas/{id}")
    public OrganigramaInstitucionalResponse actualizarOrganigrama(@PathVariable Long id, @Valid @RequestBody OrganigramaInstitucionalRequest request) {
        return organigramaInstitucionalService.actualizar(id, request);
    }

    @DeleteMapping("/organigramas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarOrganigrama(@PathVariable Long id) {
        organigramaInstitucionalService.eliminar(id);
    }
}
