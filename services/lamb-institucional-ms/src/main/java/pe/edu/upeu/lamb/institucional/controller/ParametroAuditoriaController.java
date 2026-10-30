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
 * Dominio "Parametro y Auditoria" (CRUD unificado). Subrecursos: parametro
 * institucional, parametro de sede/escuela y bitacora de cambios de
 * estructura. Los tres son opcionales segun la lista de 44 recursos.
 */
@RestController
@RequestMapping("/api/v1/parametro-auditoria")
@RequiredArgsConstructor
@Tag(name = "parametro-auditoria-controller")
public class ParametroAuditoriaController {

    private final ParametroInstitucionalService parametroInstitucionalService;
    private final ParametroSedeEscuelaService parametroSedeEscuelaService;
    private final BitacoraCambiosEstructuraService bitacoraCambiosEstructuraService;

    // --- Parametro institucional (item 42, opcional) ---

    @GetMapping("/parametros-institucionales")
    @Operation(summary = "Listar parametros institucionales")
    public List<ParametroInstitucionalResponse> listarParametrosInstitucionales() {
        return parametroInstitucionalService.listar();
    }

    @GetMapping("/parametros-institucionales/{id}")
    @Operation(summary = "Obtener un parametro institucional")
    public ParametroInstitucionalResponse obtenerParametroInstitucional(@PathVariable Long id) {
        return parametroInstitucionalService.obtener(id);
    }

    @PostMapping("/parametros-institucionales")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un parametro institucional")
    public ParametroInstitucionalResponse crearParametroInstitucional(@Valid @RequestBody ParametroInstitucionalRequest request) {
        return parametroInstitucionalService.crear(request);
    }

    // --- Parametro de sede/escuela (item 43, opcional) ---

    @GetMapping("/parametros-sede-escuela")
    @Operation(summary = "Listar parametros de sede/escuela")
    public List<ParametroSedeEscuelaResponse> listarParametrosSedeEscuela() {
        return parametroSedeEscuelaService.listar();
    }

    @GetMapping("/parametros-sede-escuela/{id}")
    @Operation(summary = "Obtener un parametro de sede/escuela")
    public ParametroSedeEscuelaResponse obtenerParametroSedeEscuela(@PathVariable Long id) {
        return parametroSedeEscuelaService.obtener(id);
    }

    @PostMapping("/parametros-sede-escuela")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un parametro de sede/escuela")
    public ParametroSedeEscuelaResponse crearParametroSedeEscuela(@Valid @RequestBody ParametroSedeEscuelaRequest request) {
        return parametroSedeEscuelaService.crear(request);
    }

    // --- Bitacora de cambios de estructura (item 44, opcional) ---

    @GetMapping("/bitacora-cambios-estructura")
    @Operation(summary = "Listar bitacora de cambios de estructura")
    public List<BitacoraCambiosEstructuraResponse> listarBitacoraCambiosEstructura() {
        return bitacoraCambiosEstructuraService.listar();
    }

    @GetMapping("/bitacora-cambios-estructura/{id}")
    @Operation(summary = "Obtener un registro de bitacora de cambios de estructura")
    public BitacoraCambiosEstructuraResponse obtenerBitacoraCambiosEstructura(@PathVariable Long id) {
        return bitacoraCambiosEstructuraService.obtener(id);
    }

    @PostMapping("/bitacora-cambios-estructura")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un registro de bitacora de cambios de estructura")
    public BitacoraCambiosEstructuraResponse crearBitacoraCambiosEstructura(@Valid @RequestBody BitacoraCambiosEstructuraRequest request) {
        return bitacoraCambiosEstructuraService.crear(request);
    }
}
