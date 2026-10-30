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

@Tag(name = "relacion-responsable-controller")
@RestController
@RequestMapping("/api/v1/relacion-responsable")
@RequiredArgsConstructor
public class RelacionResponsableController {

    private final TipoParentescoService tipoParentescoService;
    private final RelacionFamiliarService relacionFamiliarService;
    private final ResponsablePagoService responsablePagoService;

    // ---- Tipo de parentesco ----
    @Operation(summary = "Listar tipos de parentesco")
    @GetMapping("/tipos-parentesco")
    public List<TipoParentescoResponse> listarTiposParentesco() {
        return tipoParentescoService.listar();
    }

    @Operation(summary = "Obtener tipo de parentesco por id")
    @GetMapping("/tipos-parentesco/{id}")
    public TipoParentescoResponse obtenerTipoParentesco(@PathVariable Long id) {
        return tipoParentescoService.obtener(id);
    }

    @Operation(summary = "Crear tipo de parentesco")
    @PostMapping("/tipos-parentesco")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoParentescoResponse crearTipoParentesco(@Valid @RequestBody TipoParentescoRequest request) {
        return tipoParentescoService.crear(request);
    }

    @PutMapping("/tipos-parentesco/{id}")
    public TipoParentescoResponse actualizarTipoParentesco(@PathVariable Long id, @Valid @RequestBody TipoParentescoRequest request) {
        return tipoParentescoService.actualizar(id, request);
    }

    @DeleteMapping("/tipos-parentesco/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTipoParentesco(@PathVariable Long id) {
        tipoParentescoService.eliminar(id);
    }

    // ---- Relacion familiar ----
    @Operation(summary = "Listar relaciones familiares")
    @GetMapping("/relaciones-familiares")
    public List<RelacionFamiliarResponse> listarRelacionesFamiliares() {
        return relacionFamiliarService.listar();
    }

    @Operation(summary = "Obtener relacion familiar por id")
    @GetMapping("/relaciones-familiares/{id}")
    public RelacionFamiliarResponse obtenerRelacionFamiliar(@PathVariable Long id) {
        return relacionFamiliarService.obtener(id);
    }

    @Operation(summary = "Crear relacion familiar")
    @PostMapping("/relaciones-familiares")
    @ResponseStatus(HttpStatus.CREATED)
    public RelacionFamiliarResponse crearRelacionFamiliar(@Valid @RequestBody RelacionFamiliarRequest request) {
        return relacionFamiliarService.crear(request);
    }

    @PutMapping("/relaciones-familiares/{id}")
    public RelacionFamiliarResponse actualizarRelacionFamiliar(@PathVariable Long id, @Valid @RequestBody RelacionFamiliarRequest request) {
        return relacionFamiliarService.actualizar(id, request);
    }

    @DeleteMapping("/relaciones-familiares/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarRelacionFamiliar(@PathVariable Long id) {
        relacionFamiliarService.eliminar(id);
    }

    // ---- Responsable de pago ----
    @Operation(summary = "Listar responsables de pago")
    @GetMapping("/responsables-pago")
    public List<ResponsablePagoResponse> listarResponsablesPago() {
        return responsablePagoService.listar();
    }

    @Operation(summary = "Obtener responsable de pago por id")
    @GetMapping("/responsables-pago/{id}")
    public ResponsablePagoResponse obtenerResponsablePago(@PathVariable Long id) {
        return responsablePagoService.obtener(id);
    }

    @Operation(summary = "Crear responsable de pago")
    @PostMapping("/responsables-pago")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponsablePagoResponse crearResponsablePago(@Valid @RequestBody ResponsablePagoRequest request) {
        return responsablePagoService.crear(request);
    }

    @PutMapping("/responsables-pago/{id}")
    public ResponsablePagoResponse actualizarResponsablePago(@PathVariable Long id, @Valid @RequestBody ResponsablePagoRequest request) {
        return responsablePagoService.actualizar(id, request);
    }

    @DeleteMapping("/responsables-pago/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarResponsablePago(@PathVariable Long id) {
        responsablePagoService.eliminar(id);
    }
}
