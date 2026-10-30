package pe.edu.upeu.lamb.personas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.personas.dto.BajaAnonimizacionRequest;
import pe.edu.upeu.lamb.personas.dto.BajaAnonimizacionResponse;
import pe.edu.upeu.lamb.personas.dto.EstadoPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.EstadoPersonaResponse;
import pe.edu.upeu.lamb.personas.dto.FotografiaPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.FotografiaPersonaResponse;
import pe.edu.upeu.lamb.personas.dto.FusionPersonasRequest;
import pe.edu.upeu.lamb.personas.dto.FusionPersonasResponse;
import pe.edu.upeu.lamb.personas.dto.HistorialEstadoPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.HistorialEstadoPersonaResponse;
import pe.edu.upeu.lamb.personas.dto.PersonaRequest;
import pe.edu.upeu.lamb.personas.dto.PersonaResponse;
import pe.edu.upeu.lamb.personas.dto.VinculoInstitucionRequest;
import pe.edu.upeu.lamb.personas.dto.VinculoInstitucionResponse;
import pe.edu.upeu.lamb.personas.service.BajaAnonimizacionService;
import pe.edu.upeu.lamb.personas.service.EstadoPersonaService;
import pe.edu.upeu.lamb.personas.service.FotografiaPersonaService;
import pe.edu.upeu.lamb.personas.service.FusionPersonasService;
import pe.edu.upeu.lamb.personas.service.HistorialEstadoPersonaService;
import pe.edu.upeu.lamb.personas.service.PersonaService;
import pe.edu.upeu.lamb.personas.service.VinculoInstitucionService;

import java.util.List;

/**
 * Dominio "Persona" (CRUD unificado, nucleo del modulo). Subrecursos:
 * persona, tipo de persona (catalogo natural/juridica expuesto mediante el
 * enum TipoPersona en Persona), vinculo con la institucion, estado de la
 * persona, historial de estados, fusion de personas duplicadas, fotografia
 * de la persona y baja/anonimizacion de datos.
 */
@RestController
@RequestMapping("/api/v1/persona")
@RequiredArgsConstructor
@Tag(name = "persona-controller")
public class PersonaController {

    private final PersonaService personaService;
    private final VinculoInstitucionService vinculoInstitucionService;
    private final EstadoPersonaService estadoPersonaService;
    private final HistorialEstadoPersonaService historialEstadoPersonaService;
    private final FusionPersonasService fusionPersonasService;
    private final FotografiaPersonaService fotografiaPersonaService;
    private final BajaAnonimizacionService bajaAnonimizacionService;

    // --- Persona (nucleo) ---

    @GetMapping("/personas")
    @Operation(summary = "Listar personas")
    public List<PersonaResponse> listarPersonas() {
        return personaService.listar();
    }

    @GetMapping("/personas/{id}")
    @Operation(summary = "Obtener una persona")
    public PersonaResponse obtenerPersona(@PathVariable Long id) {
        return personaService.obtener(id);
    }

    @PostMapping("/personas")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una persona")
    public PersonaResponse crearPersona(@Valid @RequestBody PersonaRequest request) {
        return personaService.crear(request);
    }

    @PutMapping("/personas/{id}")
    @Operation(summary = "Actualizar una persona")
    public PersonaResponse actualizarPersona(@PathVariable Long id, @Valid @RequestBody PersonaRequest request) {
        return personaService.actualizar(id, request);
    }

    @DeleteMapping("/personas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar una persona")
    public void eliminarPersona(@PathVariable Long id) {
        personaService.eliminar(id);
    }

    // --- Vinculo con la institucion ---

    @GetMapping("/vinculos-institucion")
    @Operation(summary = "Listar vinculos con la institucion")
    public List<VinculoInstitucionResponse> listarVinculosInstitucion() {
        return vinculoInstitucionService.listar();
    }

    @GetMapping("/vinculos-institucion/{id}")
    @Operation(summary = "Obtener un vinculo con la institucion")
    public VinculoInstitucionResponse obtenerVinculoInstitucion(@PathVariable Long id) {
        return vinculoInstitucionService.obtener(id);
    }

    @PostMapping("/vinculos-institucion")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un vinculo con la institucion")
    public VinculoInstitucionResponse crearVinculoInstitucion(@Valid @RequestBody VinculoInstitucionRequest request) {
        return vinculoInstitucionService.crear(request);
    }

    // --- Estado de la persona ---

    @GetMapping("/estados")
    @Operation(summary = "Listar estados de persona")
    public List<EstadoPersonaResponse> listarEstados() {
        return estadoPersonaService.listar();
    }

    @GetMapping("/estados/{id}")
    @Operation(summary = "Obtener un estado de persona")
    public EstadoPersonaResponse obtenerEstado(@PathVariable Long id) {
        return estadoPersonaService.obtener(id);
    }

    @PostMapping("/estados")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un estado de persona")
    public EstadoPersonaResponse crearEstado(@Valid @RequestBody EstadoPersonaRequest request) {
        return estadoPersonaService.crear(request);
    }

    // --- Historial de estados de la persona ---

    @GetMapping("/historial-estados")
    @Operation(summary = "Listar historial de estados de persona")
    public List<HistorialEstadoPersonaResponse> listarHistorialEstados() {
        return historialEstadoPersonaService.listar();
    }

    @GetMapping("/historial-estados/{id}")
    @Operation(summary = "Obtener un registro del historial de estados")
    public HistorialEstadoPersonaResponse obtenerHistorialEstado(@PathVariable Long id) {
        return historialEstadoPersonaService.obtener(id);
    }

    @PostMapping("/historial-estados")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un registro del historial de estados")
    public HistorialEstadoPersonaResponse crearHistorialEstado(@Valid @RequestBody HistorialEstadoPersonaRequest request) {
        return historialEstadoPersonaService.crear(request);
    }

    // --- Fusion de personas duplicadas ---

    @GetMapping("/fusiones")
    @Operation(summary = "Listar fusiones de personas")
    public List<FusionPersonasResponse> listarFusiones() {
        return fusionPersonasService.listar();
    }

    @GetMapping("/fusiones/{id}")
    @Operation(summary = "Obtener una fusion de personas")
    public FusionPersonasResponse obtenerFusion(@PathVariable Long id) {
        return fusionPersonasService.obtener(id);
    }

    @PostMapping("/fusiones")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una fusion de personas")
    public FusionPersonasResponse crearFusion(@Valid @RequestBody FusionPersonasRequest request) {
        return fusionPersonasService.crear(request);
    }

    // --- Fotografia de la persona (opcional) ---

    @GetMapping("/fotografias")
    @Operation(summary = "Listar fotografias de persona")
    public List<FotografiaPersonaResponse> listarFotografias() {
        return fotografiaPersonaService.listar();
    }

    @GetMapping("/fotografias/{id}")
    @Operation(summary = "Obtener una fotografia de persona")
    public FotografiaPersonaResponse obtenerFotografia(@PathVariable Long id) {
        return fotografiaPersonaService.obtener(id);
    }

    @PostMapping("/fotografias")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una fotografia de persona")
    public FotografiaPersonaResponse crearFotografia(@Valid @RequestBody FotografiaPersonaRequest request) {
        return fotografiaPersonaService.crear(request);
    }

    // --- Baja logica o anonimizacion de datos (opcional) ---

    @GetMapping("/bajas-anonimizacion")
    @Operation(summary = "Listar bajas o anonimizaciones de persona")
    public List<BajaAnonimizacionResponse> listarBajasAnonimizacion() {
        return bajaAnonimizacionService.listar();
    }

    @GetMapping("/bajas-anonimizacion/{id}")
    @Operation(summary = "Obtener una baja o anonimizacion de persona")
    public BajaAnonimizacionResponse obtenerBajaAnonimizacion(@PathVariable Long id) {
        return bajaAnonimizacionService.obtener(id);
    }

    @PostMapping("/bajas-anonimizacion")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una baja o anonimizacion de persona")
    public BajaAnonimizacionResponse crearBajaAnonimizacion(@Valid @RequestBody BajaAnonimizacionRequest request) {
        return bajaAnonimizacionService.crear(request);
    }
}
