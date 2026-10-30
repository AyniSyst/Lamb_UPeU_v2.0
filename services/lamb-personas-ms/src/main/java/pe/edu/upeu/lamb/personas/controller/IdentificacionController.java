package pe.edu.upeu.lamb.personas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.personas.dto.*;
import pe.edu.upeu.lamb.personas.service.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/identificacion")
@Tag(name = "identificacion-controller")
@RequiredArgsConstructor
public class IdentificacionController {

    private final TipoDocumentoIdentidadService tipoDocumentoIdentidadService;
    private final DocumentoIdentidadService documentoIdentidadService;
    private final CodigoInstitucionalService codigoInstitucionalService;
    private final HistorialDocumentoIdentidadService historialDocumentoIdentidadService;
    private final ValidacionIdentidadService validacionIdentidadService;
    private final IdentificacionTributariaService identificacionTributariaService;
    private final CorreccionIdentificacionService correccionIdentificacionService;

    // ---- TipoDocumentoIdentidad ----
    @GetMapping("/tipos-documento")
    @Operation(summary = "Listar tipos de documento de identidad")
    public List<TipoDocumentoIdentidadResponse> listarTiposDocumento() {
        return tipoDocumentoIdentidadService.listar();
    }

    @GetMapping("/tipos-documento/{id}")
    @Operation(summary = "Obtener tipo de documento de identidad por id")
    public TipoDocumentoIdentidadResponse obtenerTipoDocumento(@PathVariable Long id) {
        return tipoDocumentoIdentidadService.obtener(id);
    }

    @PostMapping("/tipos-documento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear tipo de documento de identidad")
    public TipoDocumentoIdentidadResponse crearTipoDocumento(@Valid @RequestBody TipoDocumentoIdentidadRequest request) {
        return tipoDocumentoIdentidadService.crear(request);
    }

    @PutMapping("/tipos-documento/{id}")
    public TipoDocumentoIdentidadResponse actualizarTipoDocumento(@PathVariable Long id, @Valid @RequestBody TipoDocumentoIdentidadRequest request) {
        return tipoDocumentoIdentidadService.actualizar(id, request);
    }

    @DeleteMapping("/tipos-documento/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTipoDocumento(@PathVariable Long id) {
        tipoDocumentoIdentidadService.eliminar(id);
    }

    // ---- DocumentoIdentidad ----
    @GetMapping("/documentos")
    @Operation(summary = "Listar documentos de identidad")
    public List<DocumentoIdentidadResponse> listarDocumentos() {
        return documentoIdentidadService.listar();
    }

    @GetMapping("/documentos/{id}")
    @Operation(summary = "Obtener documento de identidad por id")
    public DocumentoIdentidadResponse obtenerDocumento(@PathVariable Long id) {
        return documentoIdentidadService.obtener(id);
    }

    @PostMapping("/documentos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear documento de identidad")
    public DocumentoIdentidadResponse crearDocumento(@Valid @RequestBody DocumentoIdentidadRequest request) {
        return documentoIdentidadService.crear(request);
    }

    @PutMapping("/documentos/{id}")
    public DocumentoIdentidadResponse actualizarDocumento(@PathVariable Long id, @Valid @RequestBody DocumentoIdentidadRequest request) {
        return documentoIdentidadService.actualizar(id, request);
    }

    @DeleteMapping("/documentos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDocumento(@PathVariable Long id) {
        documentoIdentidadService.eliminar(id);
    }

    // ---- CodigoInstitucional ----
    @GetMapping("/codigos-institucionales")
    @Operation(summary = "Listar codigos institucionales")
    public List<CodigoInstitucionalResponse> listarCodigos() {
        return codigoInstitucionalService.listar();
    }

    @GetMapping("/codigos-institucionales/{id}")
    @Operation(summary = "Obtener codigo institucional por id")
    public CodigoInstitucionalResponse obtenerCodigo(@PathVariable Long id) {
        return codigoInstitucionalService.obtener(id);
    }

    @PostMapping("/codigos-institucionales")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear codigo institucional")
    public CodigoInstitucionalResponse crearCodigo(@Valid @RequestBody CodigoInstitucionalRequest request) {
        return codigoInstitucionalService.crear(request);
    }

    @PutMapping("/codigos-institucionales/{id}")
    public CodigoInstitucionalResponse actualizarCodigo(@PathVariable Long id, @Valid @RequestBody CodigoInstitucionalRequest request) {
        return codigoInstitucionalService.actualizar(id, request);
    }

    @DeleteMapping("/codigos-institucionales/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCodigo(@PathVariable Long id) {
        codigoInstitucionalService.eliminar(id);
    }

    // ---- HistorialDocumentoIdentidad ----
    @GetMapping("/historial-documentos")
    @Operation(summary = "Listar historial de documentos de identidad")
    public List<HistorialDocumentoIdentidadResponse> listarHistorialDocumentos() {
        return historialDocumentoIdentidadService.listar();
    }

    @GetMapping("/historial-documentos/{id}")
    @Operation(summary = "Obtener historial de documento de identidad por id")
    public HistorialDocumentoIdentidadResponse obtenerHistorialDocumento(@PathVariable Long id) {
        return historialDocumentoIdentidadService.obtener(id);
    }

    @PostMapping("/historial-documentos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear historial de documento de identidad")
    public HistorialDocumentoIdentidadResponse crearHistorialDocumento(@Valid @RequestBody HistorialDocumentoIdentidadRequest request) {
        return historialDocumentoIdentidadService.crear(request);
    }

    @PutMapping("/historial-documentos/{id}")
    public HistorialDocumentoIdentidadResponse actualizarHistorialDocumento(@PathVariable Long id, @Valid @RequestBody HistorialDocumentoIdentidadRequest request) {
        return historialDocumentoIdentidadService.actualizar(id, request);
    }

    @DeleteMapping("/historial-documentos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarHistorialDocumento(@PathVariable Long id) {
        historialDocumentoIdentidadService.eliminar(id);
    }

    // ---- ValidacionIdentidad ----
    @GetMapping("/validaciones")
    @Operation(summary = "Listar validaciones de identidad")
    public List<ValidacionIdentidadResponse> listarValidaciones() {
        return validacionIdentidadService.listar();
    }

    @GetMapping("/validaciones/{id}")
    @Operation(summary = "Obtener validacion de identidad por id")
    public ValidacionIdentidadResponse obtenerValidacion(@PathVariable Long id) {
        return validacionIdentidadService.obtener(id);
    }

    @PostMapping("/validaciones")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear validacion de identidad")
    public ValidacionIdentidadResponse crearValidacion(@Valid @RequestBody ValidacionIdentidadRequest request) {
        return validacionIdentidadService.crear(request);
    }

    @PutMapping("/validaciones/{id}")
    public ValidacionIdentidadResponse actualizarValidacion(@PathVariable Long id, @Valid @RequestBody ValidacionIdentidadRequest request) {
        return validacionIdentidadService.actualizar(id, request);
    }

    @DeleteMapping("/validaciones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarValidacion(@PathVariable Long id) {
        validacionIdentidadService.eliminar(id);
    }

    // ---- IdentificacionTributaria ----
    @GetMapping("/tributarias")
    @Operation(summary = "Listar identificaciones tributarias")
    public List<IdentificacionTributariaResponse> listarTributarias() {
        return identificacionTributariaService.listar();
    }

    @GetMapping("/tributarias/{id}")
    @Operation(summary = "Obtener identificacion tributaria por id")
    public IdentificacionTributariaResponse obtenerTributaria(@PathVariable Long id) {
        return identificacionTributariaService.obtener(id);
    }

    @PostMapping("/tributarias")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear identificacion tributaria")
    public IdentificacionTributariaResponse crearTributaria(@Valid @RequestBody IdentificacionTributariaRequest request) {
        return identificacionTributariaService.crear(request);
    }

    @PutMapping("/tributarias/{id}")
    public IdentificacionTributariaResponse actualizarTributaria(@PathVariable Long id, @Valid @RequestBody IdentificacionTributariaRequest request) {
        return identificacionTributariaService.actualizar(id, request);
    }

    @DeleteMapping("/tributarias/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTributaria(@PathVariable Long id) {
        identificacionTributariaService.eliminar(id);
    }

    // ---- CorreccionIdentificacion ----
    @GetMapping("/correcciones")
    @Operation(summary = "Listar correcciones de identificacion")
    public List<CorreccionIdentificacionResponse> listarCorrecciones() {
        return correccionIdentificacionService.listar();
    }

    @GetMapping("/correcciones/{id}")
    @Operation(summary = "Obtener correccion de identificacion por id")
    public CorreccionIdentificacionResponse obtenerCorreccion(@PathVariable Long id) {
        return correccionIdentificacionService.obtener(id);
    }

    @PostMapping("/correcciones")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear correccion de identificacion")
    public CorreccionIdentificacionResponse crearCorreccion(@Valid @RequestBody CorreccionIdentificacionRequest request) {
        return correccionIdentificacionService.crear(request);
    }

    @PutMapping("/correcciones/{id}")
    public CorreccionIdentificacionResponse actualizarCorreccion(@PathVariable Long id, @Valid @RequestBody CorreccionIdentificacionRequest request) {
        return correccionIdentificacionService.actualizar(id, request);
    }

    @DeleteMapping("/correcciones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCorreccion(@PathVariable Long id) {
        correccionIdentificacionService.eliminar(id);
    }
}
