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
@RequestMapping("/api/v1/documento-persona")
@RequiredArgsConstructor
@Tag(name = "Documento Persona", description = "Gestion de documentos de la persona y sus procesos asociados")
public class DocumentoPersonaController {

    private final TipoDocumentoService tipoDocumentoService;
    private final DocumentoPersonaService documentoPersonaService;
    private final EstadoDocumentoService estadoDocumentoService;
    private final RequisitoDocumentarioService requisitoDocumentarioService;
    private final HistorialRevisionDocumentoService historialRevisionDocumentoService;
    private final VigenciaVencimientoDocumentoService vigenciaVencimientoDocumentoService;
    private final ReemplazoVersionDocumentoService reemplazoVersionDocumentoService;
    private final ObservacionDocumentoService observacionDocumentoService;
    private final FirmaValidacionDocumentoService firmaValidacionDocumentoService;
    private final ExpedienteLegajoService expedienteLegajoService;

    // ===== Tipos de documento =====

    @GetMapping("/tipos-documento")
    @Operation(summary = "Listar tipos de documento")
    public List<TipoDocumentoResponse> listarTiposDocumento() {
        return tipoDocumentoService.listar();
    }

    @GetMapping("/tipos-documento/{id}")
    @Operation(summary = "Obtener tipo de documento por id")
    public TipoDocumentoResponse obtenerTipoDocumento(@PathVariable Long id) {
        return tipoDocumentoService.obtener(id);
    }

    @PostMapping("/tipos-documento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear tipo de documento")
    public TipoDocumentoResponse crearTipoDocumento(@Valid @RequestBody TipoDocumentoRequest request) {
        return tipoDocumentoService.crear(request);
    }

    // ===== Documentos de persona =====

    @GetMapping("/documentos-persona")
    @Operation(summary = "Listar documentos de persona")
    public List<DocumentoPersonaResponse> listarDocumentosPersona() {
        return documentoPersonaService.listar();
    }

    @GetMapping("/documentos-persona/{id}")
    @Operation(summary = "Obtener documento de persona por id")
    public DocumentoPersonaResponse obtenerDocumentoPersona(@PathVariable Long id) {
        return documentoPersonaService.obtener(id);
    }

    @PostMapping("/documentos-persona")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear documento de persona")
    public DocumentoPersonaResponse crearDocumentoPersona(@Valid @RequestBody DocumentoPersonaRequest request) {
        return documentoPersonaService.crear(request);
    }

    // ===== Estados de documento =====

    @GetMapping("/estados-documento")
    @Operation(summary = "Listar estados de documento")
    public List<EstadoDocumentoResponse> listarEstadosDocumento() {
        return estadoDocumentoService.listar();
    }

    @GetMapping("/estados-documento/{id}")
    @Operation(summary = "Obtener estado de documento por id")
    public EstadoDocumentoResponse obtenerEstadoDocumento(@PathVariable Long id) {
        return estadoDocumentoService.obtener(id);
    }

    @PostMapping("/estados-documento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear estado de documento")
    public EstadoDocumentoResponse crearEstadoDocumento(@Valid @RequestBody EstadoDocumentoRequest request) {
        return estadoDocumentoService.crear(request);
    }

    // ===== Requisitos documentarios =====

    @GetMapping("/requisitos-documentarios")
    @Operation(summary = "Listar requisitos documentarios")
    public List<RequisitoDocumentarioResponse> listarRequisitosDocumentarios() {
        return requisitoDocumentarioService.listar();
    }

    @GetMapping("/requisitos-documentarios/{id}")
    @Operation(summary = "Obtener requisito documentario por id")
    public RequisitoDocumentarioResponse obtenerRequisitoDocumentario(@PathVariable Long id) {
        return requisitoDocumentarioService.obtener(id);
    }

    @PostMapping("/requisitos-documentarios")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear requisito documentario")
    public RequisitoDocumentarioResponse crearRequisitoDocumentario(@Valid @RequestBody RequisitoDocumentarioRequest request) {
        return requisitoDocumentarioService.crear(request);
    }

    // ===== Historiales de revision de documento =====

    @GetMapping("/historiales-revision-documento")
    @Operation(summary = "Listar historiales de revision de documento")
    public List<HistorialRevisionDocumentoResponse> listarHistorialesRevisionDocumento() {
        return historialRevisionDocumentoService.listar();
    }

    @GetMapping("/historiales-revision-documento/{id}")
    @Operation(summary = "Obtener historial de revision de documento por id")
    public HistorialRevisionDocumentoResponse obtenerHistorialRevisionDocumento(@PathVariable Long id) {
        return historialRevisionDocumentoService.obtener(id);
    }

    @PostMapping("/historiales-revision-documento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear historial de revision de documento")
    public HistorialRevisionDocumentoResponse crearHistorialRevisionDocumento(@Valid @RequestBody HistorialRevisionDocumentoRequest request) {
        return historialRevisionDocumentoService.crear(request);
    }

    // ===== Vigencia/vencimiento de documento =====

    @GetMapping("/vigencias-vencimiento-documento")
    @Operation(summary = "Listar vigencias/vencimientos de documento")
    public List<VigenciaVencimientoDocumentoResponse> listarVigenciasVencimientoDocumento() {
        return vigenciaVencimientoDocumentoService.listar();
    }

    @GetMapping("/vigencias-vencimiento-documento/{id}")
    @Operation(summary = "Obtener vigencia/vencimiento de documento por id")
    public VigenciaVencimientoDocumentoResponse obtenerVigenciaVencimientoDocumento(@PathVariable Long id) {
        return vigenciaVencimientoDocumentoService.obtener(id);
    }

    @PostMapping("/vigencias-vencimiento-documento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear vigencia/vencimiento de documento")
    public VigenciaVencimientoDocumentoResponse crearVigenciaVencimientoDocumento(@Valid @RequestBody VigenciaVencimientoDocumentoRequest request) {
        return vigenciaVencimientoDocumentoService.crear(request);
    }

    // ===== Reemplazo de version de documento =====

    @GetMapping("/reemplazos-version-documento")
    @Operation(summary = "Listar reemplazos de version de documento")
    public List<ReemplazoVersionDocumentoResponse> listarReemplazosVersionDocumento() {
        return reemplazoVersionDocumentoService.listar();
    }

    @GetMapping("/reemplazos-version-documento/{id}")
    @Operation(summary = "Obtener reemplazo de version de documento por id")
    public ReemplazoVersionDocumentoResponse obtenerReemplazoVersionDocumento(@PathVariable Long id) {
        return reemplazoVersionDocumentoService.obtener(id);
    }

    @PostMapping("/reemplazos-version-documento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear reemplazo de version de documento")
    public ReemplazoVersionDocumentoResponse crearReemplazoVersionDocumento(@Valid @RequestBody ReemplazoVersionDocumentoRequest request) {
        return reemplazoVersionDocumentoService.crear(request);
    }

    // ===== Observaciones de documento =====

    @GetMapping("/observaciones-documento")
    @Operation(summary = "Listar observaciones de documento")
    public List<ObservacionDocumentoResponse> listarObservacionesDocumento() {
        return observacionDocumentoService.listar();
    }

    @GetMapping("/observaciones-documento/{id}")
    @Operation(summary = "Obtener observacion de documento por id")
    public ObservacionDocumentoResponse obtenerObservacionDocumento(@PathVariable Long id) {
        return observacionDocumentoService.obtener(id);
    }

    @PostMapping("/observaciones-documento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear observacion de documento")
    public ObservacionDocumentoResponse crearObservacionDocumento(@Valid @RequestBody ObservacionDocumentoRequest request) {
        return observacionDocumentoService.crear(request);
    }

    // ===== Firma/validacion de documento =====

    @GetMapping("/firmas-validacion-documento")
    @Operation(summary = "Listar firmas de validacion de documento")
    public List<FirmaValidacionDocumentoResponse> listarFirmasValidacionDocumento() {
        return firmaValidacionDocumentoService.listar();
    }

    @GetMapping("/firmas-validacion-documento/{id}")
    @Operation(summary = "Obtener firma de validacion de documento por id")
    public FirmaValidacionDocumentoResponse obtenerFirmaValidacionDocumento(@PathVariable Long id) {
        return firmaValidacionDocumentoService.obtener(id);
    }

    @PostMapping("/firmas-validacion-documento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear firma de validacion de documento")
    public FirmaValidacionDocumentoResponse crearFirmaValidacionDocumento(@Valid @RequestBody FirmaValidacionDocumentoRequest request) {
        return firmaValidacionDocumentoService.crear(request);
    }

    // ===== Expedientes/legajos =====

    @GetMapping("/expedientes-legajo")
    @Operation(summary = "Listar expedientes/legajos")
    public List<ExpedienteLegajoResponse> listarExpedientesLegajo() {
        return expedienteLegajoService.listar();
    }

    @GetMapping("/expedientes-legajo/{id}")
    @Operation(summary = "Obtener expediente/legajo por id")
    public ExpedienteLegajoResponse obtenerExpedienteLegajo(@PathVariable Long id) {
        return expedienteLegajoService.obtener(id);
    }

    @PostMapping("/expedientes-legajo")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear expediente/legajo")
    public ExpedienteLegajoResponse crearExpedienteLegajo(@Valid @RequestBody ExpedienteLegajoRequest request) {
        return expedienteLegajoService.crear(request);
    }
}
