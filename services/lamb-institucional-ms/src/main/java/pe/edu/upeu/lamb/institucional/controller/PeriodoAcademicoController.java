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
 * Dominio "Periodo Academico" (CRUD unificado). Subrecursos: anio
 * academico, tipo de periodo, periodo academico, estado de periodo,
 * periodo-programa-sede y ampliacion/cierre de periodo.
 */
@RestController
@RequestMapping("/api/v1/periodo-academico")
@RequiredArgsConstructor
@Tag(name = "periodo-academico-controller")
public class PeriodoAcademicoController {

    private final AnioAcademicoService anioAcademicoService;
    private final TipoPeriodoService tipoPeriodoService;
    private final PeriodoAcademicoService periodoAcademicoService;
    private final EstadoPeriodoService estadoPeriodoService;
    private final PeriodoProgramaSedeService periodoProgramaSedeService;
    private final AmpliacionCierrePeriodoService ampliacionCierrePeriodoService;

    // --- Anio academico (item 28) ---

    @GetMapping("/anios-academicos")
    @Operation(summary = "Listar anios academicos")
    public List<AnioAcademicoResponse> listarAniosAcademicos() {
        return anioAcademicoService.listar();
    }

    @GetMapping("/anios-academicos/{id}")
    @Operation(summary = "Obtener un anio academico")
    public AnioAcademicoResponse obtenerAnioAcademico(@PathVariable Long id) {
        return anioAcademicoService.obtener(id);
    }

    @PostMapping("/anios-academicos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un anio academico")
    public AnioAcademicoResponse crearAnioAcademico(@Valid @RequestBody AnioAcademicoRequest request) {
        return anioAcademicoService.crear(request);
    }

    @PutMapping("/anios-academicos/{id}")
    public AnioAcademicoResponse actualizarAnioAcademico(@PathVariable Long id, @Valid @RequestBody AnioAcademicoRequest request) {
        return anioAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/anios-academicos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAnioAcademico(@PathVariable Long id) {
        anioAcademicoService.eliminar(id);
    }

    // --- Tipo de periodo (item 29) ---

    @GetMapping("/tipos-periodo")
    @Operation(summary = "Listar tipos de periodo")
    public List<TipoPeriodoResponse> listarTiposPeriodo() {
        return tipoPeriodoService.listar();
    }

    @GetMapping("/tipos-periodo/{id}")
    @Operation(summary = "Obtener un tipo de periodo")
    public TipoPeriodoResponse obtenerTipoPeriodo(@PathVariable Long id) {
        return tipoPeriodoService.obtener(id);
    }

    @PostMapping("/tipos-periodo")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un tipo de periodo")
    public TipoPeriodoResponse crearTipoPeriodo(@Valid @RequestBody TipoPeriodoRequest request) {
        return tipoPeriodoService.crear(request);
    }

    @PutMapping("/tipos-periodo/{id}")
    public TipoPeriodoResponse actualizarTipoPeriodo(@PathVariable Long id, @Valid @RequestBody TipoPeriodoRequest request) {
        return tipoPeriodoService.actualizar(id, request);
    }

    @DeleteMapping("/tipos-periodo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTipoPeriodo(@PathVariable Long id) {
        tipoPeriodoService.eliminar(id);
    }

    // --- Periodo academico (item 30) ---

    @GetMapping("/periodos")
    @Operation(summary = "Listar periodos academicos")
    public List<PeriodoAcademicoResponse> listarPeriodosAcademicos() {
        return periodoAcademicoService.listar();
    }

    @GetMapping("/periodos/{id}")
    @Operation(summary = "Obtener un periodo academico")
    public PeriodoAcademicoResponse obtenerPeriodoAcademico(@PathVariable Long id) {
        return periodoAcademicoService.obtener(id);
    }

    @PostMapping("/periodos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un periodo academico")
    public PeriodoAcademicoResponse crearPeriodoAcademico(@Valid @RequestBody PeriodoAcademicoRequest request) {
        return periodoAcademicoService.crear(request);
    }

    @PutMapping("/periodos/{id}")
    public PeriodoAcademicoResponse actualizarPeriodoAcademico(@PathVariable Long id, @Valid @RequestBody PeriodoAcademicoRequest request) {
        return periodoAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/periodos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPeriodoAcademico(@PathVariable Long id) {
        periodoAcademicoService.eliminar(id);
    }

    // --- Estado de periodo (item 31) ---

    @GetMapping("/estados-periodo")
    @Operation(summary = "Listar estados de periodo")
    public List<EstadoPeriodoResponse> listarEstadosPeriodo() {
        return estadoPeriodoService.listar();
    }

    @GetMapping("/estados-periodo/{id}")
    @Operation(summary = "Obtener un estado de periodo")
    public EstadoPeriodoResponse obtenerEstadoPeriodo(@PathVariable Long id) {
        return estadoPeriodoService.obtener(id);
    }

    @PostMapping("/estados-periodo")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un estado de periodo")
    public EstadoPeriodoResponse crearEstadoPeriodo(@Valid @RequestBody EstadoPeriodoRequest request) {
        return estadoPeriodoService.crear(request);
    }

    @PutMapping("/estados-periodo/{id}")
    public EstadoPeriodoResponse actualizarEstadoPeriodo(@PathVariable Long id, @Valid @RequestBody EstadoPeriodoRequest request) {
        return estadoPeriodoService.actualizar(id, request);
    }

    @DeleteMapping("/estados-periodo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEstadoPeriodo(@PathVariable Long id) {
        estadoPeriodoService.eliminar(id);
    }

    // --- Periodo-programa-sede (item 32) ---

    @GetMapping("/periodo-programa-sede")
    @Operation(summary = "Listar relaciones periodo-programa-sede")
    public List<PeriodoProgramaSedeResponse> listarPeriodoProgramaSede() {
        return periodoProgramaSedeService.listar();
    }

    @GetMapping("/periodo-programa-sede/{id}")
    @Operation(summary = "Obtener una relacion periodo-programa-sede")
    public PeriodoProgramaSedeResponse obtenerPeriodoProgramaSede(@PathVariable Long id) {
        return periodoProgramaSedeService.obtener(id);
    }

    @PostMapping("/periodo-programa-sede")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una relacion periodo-programa-sede")
    public PeriodoProgramaSedeResponse crearPeriodoProgramaSede(@Valid @RequestBody PeriodoProgramaSedeRequest request) {
        return periodoProgramaSedeService.crear(request);
    }

    @PutMapping("/periodo-programa-sede/{id}")
    public PeriodoProgramaSedeResponse actualizarPeriodoProgramaSede(@PathVariable Long id, @Valid @RequestBody PeriodoProgramaSedeRequest request) {
        return periodoProgramaSedeService.actualizar(id, request);
    }

    @DeleteMapping("/periodo-programa-sede/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPeriodoProgramaSede(@PathVariable Long id) {
        periodoProgramaSedeService.eliminar(id);
    }

    // --- Ampliacion/cierre de periodo (item 33) ---

    @GetMapping("/ampliaciones-cierre")
    @Operation(summary = "Listar ampliaciones/cierres de periodo")
    public List<AmpliacionCierrePeriodoResponse> listarAmpliacionesCierre() {
        return ampliacionCierrePeriodoService.listar();
    }

    @GetMapping("/ampliaciones-cierre/{id}")
    @Operation(summary = "Obtener una ampliacion/cierre de periodo")
    public AmpliacionCierrePeriodoResponse obtenerAmpliacionCierre(@PathVariable Long id) {
        return ampliacionCierrePeriodoService.obtener(id);
    }

    @PostMapping("/ampliaciones-cierre")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una ampliacion/cierre de periodo")
    public AmpliacionCierrePeriodoResponse crearAmpliacionCierre(@Valid @RequestBody AmpliacionCierrePeriodoRequest request) {
        return ampliacionCierrePeriodoService.crear(request);
    }

    @PutMapping("/ampliaciones-cierre/{id}")
    public AmpliacionCierrePeriodoResponse actualizarAmpliacionCierre(@PathVariable Long id, @Valid @RequestBody AmpliacionCierrePeriodoRequest request) {
        return ampliacionCierrePeriodoService.actualizar(id, request);
    }

    @DeleteMapping("/ampliaciones-cierre/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAmpliacionCierre(@PathVariable Long id) {
        ampliacionCierrePeriodoService.eliminar(id);
    }
}
