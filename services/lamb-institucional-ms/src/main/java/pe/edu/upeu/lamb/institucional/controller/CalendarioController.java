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
 * Dominio "Calendario" (CRUD unificado). Subrecursos: calendario academico,
 * tipo de evento, evento de calendario, feriado no laborable, calendario por
 * facultad/escuela, reprogramacion de evento, publicacion de calendario y
 * recordatorio de calendario.
 */
@RestController
@RequestMapping("/api/v1/calendario")
@RequiredArgsConstructor
@Tag(name = "calendario-controller")
public class CalendarioController {

    private final CalendarioAcademicoService calendarioAcademicoService;
    private final TipoEventoService tipoEventoService;
    private final EventoCalendarioService eventoCalendarioService;
    private final FeriadoNoLaborableService feriadoNoLaborableService;
    private final CalendarioFacultadEscuelaService calendarioFacultadEscuelaService;
    private final ReprogramacionEventoService reprogramacionEventoService;
    private final PublicacionCalendarioService publicacionCalendarioService;
    private final RecordatorioCalendarioService recordatorioCalendarioService;

    // --- Calendario academico (item 34) ---

    @GetMapping("/calendarios")
    @Operation(summary = "Listar calendarios academicos")
    public List<CalendarioAcademicoResponse> listarCalendarios() {
        return calendarioAcademicoService.listar();
    }

    @GetMapping("/calendarios/{id}")
    @Operation(summary = "Obtener un calendario academico")
    public CalendarioAcademicoResponse obtenerCalendario(@PathVariable Long id) {
        return calendarioAcademicoService.obtener(id);
    }

    @PostMapping("/calendarios")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un calendario academico")
    public CalendarioAcademicoResponse crearCalendario(@Valid @RequestBody CalendarioAcademicoRequest request) {
        return calendarioAcademicoService.crear(request);
    }

    // --- Tipo de evento (item 35) ---

    @GetMapping("/tipos-evento")
    @Operation(summary = "Listar tipos de evento")
    public List<TipoEventoResponse> listarTiposEvento() {
        return tipoEventoService.listar();
    }

    @GetMapping("/tipos-evento/{id}")
    @Operation(summary = "Obtener un tipo de evento")
    public TipoEventoResponse obtenerTipoEvento(@PathVariable Long id) {
        return tipoEventoService.obtener(id);
    }

    @PostMapping("/tipos-evento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un tipo de evento")
    public TipoEventoResponse crearTipoEvento(@Valid @RequestBody TipoEventoRequest request) {
        return tipoEventoService.crear(request);
    }

    // --- Evento de calendario (item 36) ---

    @GetMapping("/eventos")
    @Operation(summary = "Listar eventos de calendario")
    public List<EventoCalendarioResponse> listarEventos() {
        return eventoCalendarioService.listar();
    }

    @GetMapping("/eventos/{id}")
    @Operation(summary = "Obtener un evento de calendario")
    public EventoCalendarioResponse obtenerEvento(@PathVariable Long id) {
        return eventoCalendarioService.obtener(id);
    }

    @PostMapping("/eventos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un evento de calendario")
    public EventoCalendarioResponse crearEvento(@Valid @RequestBody EventoCalendarioRequest request) {
        return eventoCalendarioService.crear(request);
    }

    // --- Feriado no laborable (item 37) ---

    @GetMapping("/feriados")
    @Operation(summary = "Listar feriados no laborables")
    public List<FeriadoNoLaborableResponse> listarFeriados() {
        return feriadoNoLaborableService.listar();
    }

    @GetMapping("/feriados/{id}")
    @Operation(summary = "Obtener un feriado no laborable")
    public FeriadoNoLaborableResponse obtenerFeriado(@PathVariable Long id) {
        return feriadoNoLaborableService.obtener(id);
    }

    @PostMapping("/feriados")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un feriado no laborable")
    public FeriadoNoLaborableResponse crearFeriado(@Valid @RequestBody FeriadoNoLaborableRequest request) {
        return feriadoNoLaborableService.crear(request);
    }

    // --- Calendario por facultad/escuela (item 38) ---

    @GetMapping("/calendario-facultad-escuela")
    @Operation(summary = "Listar calendarios por facultad/escuela")
    public List<CalendarioFacultadEscuelaResponse> listarCalendarioFacultadEscuela() {
        return calendarioFacultadEscuelaService.listar();
    }

    @GetMapping("/calendario-facultad-escuela/{id}")
    @Operation(summary = "Obtener un calendario por facultad/escuela")
    public CalendarioFacultadEscuelaResponse obtenerCalendarioFacultadEscuela(@PathVariable Long id) {
        return calendarioFacultadEscuelaService.obtener(id);
    }

    @PostMapping("/calendario-facultad-escuela")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un calendario por facultad/escuela")
    public CalendarioFacultadEscuelaResponse crearCalendarioFacultadEscuela(@Valid @RequestBody CalendarioFacultadEscuelaRequest request) {
        return calendarioFacultadEscuelaService.crear(request);
    }

    // --- Reprogramacion de evento (item 39) ---

    @GetMapping("/reprogramaciones-evento")
    @Operation(summary = "Listar reprogramaciones de evento")
    public List<ReprogramacionEventoResponse> listarReprogramacionesEvento() {
        return reprogramacionEventoService.listar();
    }

    @GetMapping("/reprogramaciones-evento/{id}")
    @Operation(summary = "Obtener una reprogramacion de evento")
    public ReprogramacionEventoResponse obtenerReprogramacionEvento(@PathVariable Long id) {
        return reprogramacionEventoService.obtener(id);
    }

    @PostMapping("/reprogramaciones-evento")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una reprogramacion de evento")
    public ReprogramacionEventoResponse crearReprogramacionEvento(@Valid @RequestBody ReprogramacionEventoRequest request) {
        return reprogramacionEventoService.crear(request);
    }

    // --- Publicacion de calendario (item 40, opcional) ---

    @GetMapping("/publicaciones")
    @Operation(summary = "Listar publicaciones de calendario")
    public List<PublicacionCalendarioResponse> listarPublicaciones() {
        return publicacionCalendarioService.listar();
    }

    @GetMapping("/publicaciones/{id}")
    @Operation(summary = "Obtener una publicacion de calendario")
    public PublicacionCalendarioResponse obtenerPublicacion(@PathVariable Long id) {
        return publicacionCalendarioService.obtener(id);
    }

    @PostMapping("/publicaciones")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una publicacion de calendario")
    public PublicacionCalendarioResponse crearPublicacion(@Valid @RequestBody PublicacionCalendarioRequest request) {
        return publicacionCalendarioService.crear(request);
    }

    // --- Recordatorio de calendario (item 41, opcional) ---

    @GetMapping("/recordatorios")
    @Operation(summary = "Listar recordatorios de calendario")
    public List<RecordatorioCalendarioResponse> listarRecordatorios() {
        return recordatorioCalendarioService.listar();
    }

    @GetMapping("/recordatorios/{id}")
    @Operation(summary = "Obtener un recordatorio de calendario")
    public RecordatorioCalendarioResponse obtenerRecordatorio(@PathVariable Long id) {
        return recordatorioCalendarioService.obtener(id);
    }

    @PostMapping("/recordatorios")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un recordatorio de calendario")
    public RecordatorioCalendarioResponse crearRecordatorio(@Valid @RequestBody RecordatorioCalendarioRequest request) {
        return recordatorioCalendarioService.crear(request);
    }
}
