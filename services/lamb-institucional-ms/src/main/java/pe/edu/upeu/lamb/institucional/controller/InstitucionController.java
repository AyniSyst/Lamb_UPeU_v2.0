package pe.edu.upeu.lamb.institucional.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.institucional.dto.AutoridadInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.AutoridadInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.dto.ConvenioInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.ConvenioInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.dto.InstitucionRequest;
import pe.edu.upeu.lamb.institucional.dto.InstitucionResponse;
import pe.edu.upeu.lamb.institucional.dto.ResolucionLicenciamientoRequest;
import pe.edu.upeu.lamb.institucional.dto.ResolucionLicenciamientoResponse;
import pe.edu.upeu.lamb.institucional.dto.TipoInstitucionRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoInstitucionResponse;
import pe.edu.upeu.lamb.institucional.service.AutoridadInstitucionalService;
import pe.edu.upeu.lamb.institucional.service.ConvenioInstitucionalService;
import pe.edu.upeu.lamb.institucional.service.InstitucionService;
import pe.edu.upeu.lamb.institucional.service.ResolucionLicenciamientoService;
import pe.edu.upeu.lamb.institucional.service.TipoInstitucionService;

import java.util.List;

/**
 * Dominio "Institucion" (CRUD unificado). Subrecursos: institucion, tipo de
 * institucion, autoridad institucional, resolucion de licenciamiento y
 * convenio institucional.
 */
@RestController
@RequestMapping("/api/v1/institucion")
@RequiredArgsConstructor
@Tag(name = "institucion-controller")
public class InstitucionController {

    private final InstitucionService institucionService;
    private final TipoInstitucionService tipoInstitucionService;
    private final AutoridadInstitucionalService autoridadInstitucionalService;
    private final ResolucionLicenciamientoService resolucionLicenciamientoService;
    private final ConvenioInstitucionalService convenioInstitucionalService;

    // --- Institucion (item 1) ---

    @GetMapping("/instituciones")
    @Operation(summary = "Listar instituciones")
    public List<InstitucionResponse> listarInstituciones() {
        return institucionService.listar();
    }

    @GetMapping("/instituciones/{id}")
    @Operation(summary = "Obtener una institucion")
    public InstitucionResponse obtenerInstitucion(@PathVariable Long id) {
        return institucionService.obtener(id);
    }

    @PostMapping("/instituciones")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una institucion")
    public InstitucionResponse crearInstitucion(@Valid @RequestBody InstitucionRequest request) {
        return institucionService.crear(request);
    }

    @PutMapping("/instituciones/{id}")
    public InstitucionResponse actualizarInstitucion(@PathVariable Long id, @Valid @RequestBody InstitucionRequest request) {
        return institucionService.actualizar(id, request);
    }

    @DeleteMapping("/instituciones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarInstitucion(@PathVariable Long id) {
        institucionService.eliminar(id);
    }

    // --- Tipo de institucion (item 2) ---

    @GetMapping("/tipos-institucion")
    @Operation(summary = "Listar tipos de institucion")
    public List<TipoInstitucionResponse> listarTiposInstitucion() {
        return tipoInstitucionService.listar();
    }

    @GetMapping("/tipos-institucion/{id}")
    @Operation(summary = "Obtener un tipo de institucion")
    public TipoInstitucionResponse obtenerTipoInstitucion(@PathVariable Long id) {
        return tipoInstitucionService.obtener(id);
    }

    @PostMapping("/tipos-institucion")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un tipo de institucion")
    public TipoInstitucionResponse crearTipoInstitucion(@Valid @RequestBody TipoInstitucionRequest request) {
        return tipoInstitucionService.crear(request);
    }

    @PutMapping("/tipos-institucion/{id}")
    public TipoInstitucionResponse actualizarTipoInstitucion(@PathVariable Long id, @Valid @RequestBody TipoInstitucionRequest request) {
        return tipoInstitucionService.actualizar(id, request);
    }

    @DeleteMapping("/tipos-institucion/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTipoInstitucion(@PathVariable Long id) {
        tipoInstitucionService.eliminar(id);
    }

    // --- Autoridad institucional (item 3) ---

    @GetMapping("/autoridades")
    @Operation(summary = "Listar autoridades institucionales")
    public List<AutoridadInstitucionalResponse> listarAutoridades() {
        return autoridadInstitucionalService.listar();
    }

    @GetMapping("/autoridades/{id}")
    @Operation(summary = "Obtener una autoridad institucional")
    public AutoridadInstitucionalResponse obtenerAutoridad(@PathVariable Long id) {
        return autoridadInstitucionalService.obtener(id);
    }

    @PostMapping("/autoridades")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una autoridad institucional")
    public AutoridadInstitucionalResponse crearAutoridad(@Valid @RequestBody AutoridadInstitucionalRequest request) {
        return autoridadInstitucionalService.crear(request);
    }

    @PutMapping("/autoridades/{id}")
    public AutoridadInstitucionalResponse actualizarAutoridad(@PathVariable Long id, @Valid @RequestBody AutoridadInstitucionalRequest request) {
        return autoridadInstitucionalService.actualizar(id, request);
    }

    @DeleteMapping("/autoridades/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAutoridad(@PathVariable Long id) {
        autoridadInstitucionalService.eliminar(id);
    }

    // --- Resolucion de licenciamiento (item 4) ---

    @GetMapping("/resoluciones-licenciamiento")
    @Operation(summary = "Listar resoluciones de licenciamiento")
    public List<ResolucionLicenciamientoResponse> listarResolucionesLicenciamiento() {
        return resolucionLicenciamientoService.listar();
    }

    @GetMapping("/resoluciones-licenciamiento/{id}")
    @Operation(summary = "Obtener una resolucion de licenciamiento")
    public ResolucionLicenciamientoResponse obtenerResolucionLicenciamiento(@PathVariable Long id) {
        return resolucionLicenciamientoService.obtener(id);
    }

    @PostMapping("/resoluciones-licenciamiento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una resolucion de licenciamiento")
    public ResolucionLicenciamientoResponse crearResolucionLicenciamiento(@Valid @RequestBody ResolucionLicenciamientoRequest request) {
        return resolucionLicenciamientoService.crear(request);
    }

    @PutMapping("/resoluciones-licenciamiento/{id}")
    public ResolucionLicenciamientoResponse actualizarResolucionLicenciamiento(@PathVariable Long id, @Valid @RequestBody ResolucionLicenciamientoRequest request) {
        return resolucionLicenciamientoService.actualizar(id, request);
    }

    @DeleteMapping("/resoluciones-licenciamiento/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarResolucionLicenciamiento(@PathVariable Long id) {
        resolucionLicenciamientoService.eliminar(id);
    }

    // --- Convenio institucional (item 5, opcional) ---

    @GetMapping("/convenios")
    @Operation(summary = "Listar convenios institucionales")
    public List<ConvenioInstitucionalResponse> listarConvenios() {
        return convenioInstitucionalService.listar();
    }

    @GetMapping("/convenios/{id}")
    @Operation(summary = "Obtener un convenio institucional")
    public ConvenioInstitucionalResponse obtenerConvenio(@PathVariable Long id) {
        return convenioInstitucionalService.obtener(id);
    }

    @PostMapping("/convenios")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un convenio institucional")
    public ConvenioInstitucionalResponse crearConvenio(@Valid @RequestBody ConvenioInstitucionalRequest request) {
        return convenioInstitucionalService.crear(request);
    }

    @PutMapping("/convenios/{id}")
    public ConvenioInstitucionalResponse actualizarConvenio(@PathVariable Long id, @Valid @RequestBody ConvenioInstitucionalRequest request) {
        return convenioInstitucionalService.actualizar(id, request);
    }

    @DeleteMapping("/convenios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarConvenio(@PathVariable Long id) {
        convenioInstitucionalService.eliminar(id);
    }
}
