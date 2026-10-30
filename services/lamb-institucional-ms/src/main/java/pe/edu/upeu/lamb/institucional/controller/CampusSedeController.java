package pe.edu.upeu.lamb.institucional.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.institucional.dto.AmbienteRequest;
import pe.edu.upeu.lamb.institucional.dto.AmbienteResponse;
import pe.edu.upeu.lamb.institucional.dto.CampusRequest;
import pe.edu.upeu.lamb.institucional.dto.CampusResponse;
import pe.edu.upeu.lamb.institucional.dto.DireccionSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.DireccionSedeResponse;
import pe.edu.upeu.lamb.institucional.dto.PabellonRequest;
import pe.edu.upeu.lamb.institucional.dto.PabellonResponse;
import pe.edu.upeu.lamb.institucional.dto.SedeRequest;
import pe.edu.upeu.lamb.institucional.dto.SedeResponse;
import pe.edu.upeu.lamb.institucional.dto.TipoSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoSedeResponse;
import pe.edu.upeu.lamb.institucional.service.AmbienteService;
import pe.edu.upeu.lamb.institucional.service.CampusService;
import pe.edu.upeu.lamb.institucional.service.DireccionSedeService;
import pe.edu.upeu.lamb.institucional.service.PabellonService;
import pe.edu.upeu.lamb.institucional.service.SedeService;
import pe.edu.upeu.lamb.institucional.service.TipoSedeService;

import java.util.List;

/**
 * Dominio "Campus y Sede" (CRUD unificado). Subrecursos: campus, sede, tipo
 * de sede, direccion de sede, pabellon y ambiente.
 */
@RestController
@RequestMapping("/api/v1/campus-sede")
@RequiredArgsConstructor
@Tag(name = "campus-sede-controller")
public class CampusSedeController {

    private final CampusService campusService;
    private final SedeService sedeService;
    private final TipoSedeService tipoSedeService;
    private final DireccionSedeService direccionSedeService;
    private final PabellonService pabellonService;
    private final AmbienteService ambienteService;

    // --- Campus (item 6) ---

    @GetMapping("/campus")
    @Operation(summary = "Listar campus")
    public List<CampusResponse> listarCampus() {
        return campusService.listar();
    }

    @GetMapping("/campus/{id}")
    @Operation(summary = "Obtener un campus")
    public CampusResponse obtenerCampus(@PathVariable Long id) {
        return campusService.obtener(id);
    }

    @PostMapping("/campus")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un campus")
    public CampusResponse crearCampus(@Valid @RequestBody CampusRequest request) {
        return campusService.crear(request);
    }

    // --- Sede (item 7) ---

    @GetMapping("/sedes")
    @Operation(summary = "Listar sedes")
    public List<SedeResponse> listarSedes() {
        return sedeService.listar();
    }

    @GetMapping("/sedes/{id}")
    @Operation(summary = "Obtener una sede")
    public SedeResponse obtenerSede(@PathVariable Long id) {
        return sedeService.obtener(id);
    }

    @PostMapping("/sedes")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una sede")
    public SedeResponse crearSede(@Valid @RequestBody SedeRequest request) {
        return sedeService.crear(request);
    }

    // --- Tipo de sede (item 8) ---

    @GetMapping("/tipos-sede")
    @Operation(summary = "Listar tipos de sede")
    public List<TipoSedeResponse> listarTiposSede() {
        return tipoSedeService.listar();
    }

    @GetMapping("/tipos-sede/{id}")
    @Operation(summary = "Obtener un tipo de sede")
    public TipoSedeResponse obtenerTipoSede(@PathVariable Long id) {
        return tipoSedeService.obtener(id);
    }

    @PostMapping("/tipos-sede")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un tipo de sede")
    public TipoSedeResponse crearTipoSede(@Valid @RequestBody TipoSedeRequest request) {
        return tipoSedeService.crear(request);
    }

    // --- Direccion de sede (item 9) ---

    @GetMapping("/direcciones-sede")
    @Operation(summary = "Listar direcciones de sede")
    public List<DireccionSedeResponse> listarDireccionesSede() {
        return direccionSedeService.listar();
    }

    @GetMapping("/direcciones-sede/{id}")
    @Operation(summary = "Obtener una direccion de sede")
    public DireccionSedeResponse obtenerDireccionSede(@PathVariable Long id) {
        return direccionSedeService.obtener(id);
    }

    @PostMapping("/direcciones-sede")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una direccion de sede")
    public DireccionSedeResponse crearDireccionSede(@Valid @RequestBody DireccionSedeRequest request) {
        return direccionSedeService.crear(request);
    }

    // --- Pabellon (item 10, opcional) ---

    @GetMapping("/pabellones")
    @Operation(summary = "Listar pabellones")
    public List<PabellonResponse> listarPabellones() {
        return pabellonService.listar();
    }

    @GetMapping("/pabellones/{id}")
    @Operation(summary = "Obtener un pabellon")
    public PabellonResponse obtenerPabellon(@PathVariable Long id) {
        return pabellonService.obtener(id);
    }

    @PostMapping("/pabellones")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un pabellon")
    public PabellonResponse crearPabellon(@Valid @RequestBody PabellonRequest request) {
        return pabellonService.crear(request);
    }

    // --- Ambiente (item 11) ---

    @GetMapping("/ambientes")
    @Operation(summary = "Listar ambientes")
    public List<AmbienteResponse> listarAmbientes() {
        return ambienteService.listar();
    }

    @GetMapping("/ambientes/{id}")
    @Operation(summary = "Obtener un ambiente")
    public AmbienteResponse obtenerAmbiente(@PathVariable Long id) {
        return ambienteService.obtener(id);
    }

    @PostMapping("/ambientes")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un ambiente")
    public AmbienteResponse crearAmbiente(@Valid @RequestBody AmbienteRequest request) {
        return ambienteService.crear(request);
    }
}
