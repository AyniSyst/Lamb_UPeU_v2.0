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
}
