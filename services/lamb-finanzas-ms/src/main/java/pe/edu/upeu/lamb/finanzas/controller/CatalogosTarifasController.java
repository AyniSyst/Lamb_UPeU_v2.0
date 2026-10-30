package pe.edu.upeu.lamb.finanzas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.finanzas.dto.CategoriaConceptoRequest;
import pe.edu.upeu.lamb.finanzas.dto.CategoriaConceptoResponse;
import pe.edu.upeu.lamb.finanzas.dto.ConceptoPagoRequest;
import pe.edu.upeu.lamb.finanzas.dto.ConceptoPagoResponse;
import pe.edu.upeu.lamb.finanzas.dto.TarifarioEscuelaCicloRequest;
import pe.edu.upeu.lamb.finanzas.dto.TarifarioEscuelaCicloResponse;
import pe.edu.upeu.lamb.finanzas.service.CategoriaConceptoService;
import pe.edu.upeu.lamb.finanzas.service.ConceptoPagoService;
import pe.edu.upeu.lamb.finanzas.service.TarifarioEscuelaCicloService;

import java.util.List;

/**
 * Dominio "Catalogos y tarifas" (CRUD unificado). Subrecursos: categoria de
 * concepto, concepto de pago, tarifario por escuela y ciclo.
 */
@RestController
@RequestMapping("/api/v1/catalogos-tarifas")
@RequiredArgsConstructor
@Tag(name = "catalogos-y-tarifas-controller")
public class CatalogosTarifasController {

    private final CategoriaConceptoService categoriaConceptoService;
    private final ConceptoPagoService conceptoPagoService;
    private final TarifarioEscuelaCicloService tarifarioEscuelaCicloService;

    // --- Categoria de concepto ---

    @GetMapping("/categorias-concepto")
    @Operation(summary = "Listar categorias de concepto")
    public List<CategoriaConceptoResponse> listarCategoriasConcepto() {
        return categoriaConceptoService.listar();
    }

    @GetMapping("/categorias-concepto/{id}")
    @Operation(summary = "Obtener una categoria de concepto")
    public CategoriaConceptoResponse obtenerCategoriaConcepto(@PathVariable Long id) {
        return categoriaConceptoService.obtener(id);
    }

    @PostMapping("/categorias-concepto")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una categoria de concepto")
    public CategoriaConceptoResponse crearCategoriaConcepto(@Valid @RequestBody CategoriaConceptoRequest request) {
        return categoriaConceptoService.crear(request);
    }

    @PutMapping("/categorias-concepto/{id}")
    public CategoriaConceptoResponse actualizarCategoriaConcepto(@PathVariable Long id, @Valid @RequestBody CategoriaConceptoRequest request) {
        return categoriaConceptoService.actualizar(id, request);
    }

    @DeleteMapping("/categorias-concepto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCategoriaConcepto(@PathVariable Long id) {
        categoriaConceptoService.eliminar(id);
    }

    // --- Concepto de pago ---

    @GetMapping("/conceptos-pago")
    @Operation(summary = "Listar conceptos de pago")
    public List<ConceptoPagoResponse> listarConceptosPago() {
        return conceptoPagoService.listar();
    }

    @GetMapping("/conceptos-pago/{id}")
    @Operation(summary = "Obtener un concepto de pago")
    public ConceptoPagoResponse obtenerConceptoPago(@PathVariable Long id) {
        return conceptoPagoService.obtener(id);
    }

    @PostMapping("/conceptos-pago")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un concepto de pago")
    public ConceptoPagoResponse crearConceptoPago(@Valid @RequestBody ConceptoPagoRequest request) {
        return conceptoPagoService.crear(request);
    }

    @PutMapping("/conceptos-pago/{id}")
    public ConceptoPagoResponse actualizarConceptoPago(@PathVariable Long id, @Valid @RequestBody ConceptoPagoRequest request) {
        return conceptoPagoService.actualizar(id, request);
    }

    @DeleteMapping("/conceptos-pago/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarConceptoPago(@PathVariable Long id) {
        conceptoPagoService.eliminar(id);
    }

    // --- Tarifario por escuela y ciclo ---

    @GetMapping("/tarifarios-escuela-ciclo")
    @Operation(summary = "Listar tarifarios por escuela y ciclo")
    public List<TarifarioEscuelaCicloResponse> listarTarifarios() {
        return tarifarioEscuelaCicloService.listar();
    }

    @GetMapping("/tarifarios-escuela-ciclo/{id}")
    @Operation(summary = "Obtener un tarifario por escuela y ciclo")
    public TarifarioEscuelaCicloResponse obtenerTarifario(@PathVariable Long id) {
        return tarifarioEscuelaCicloService.obtener(id);
    }

    @PostMapping("/tarifarios-escuela-ciclo")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un tarifario por escuela y ciclo")
    public TarifarioEscuelaCicloResponse crearTarifario(@Valid @RequestBody TarifarioEscuelaCicloRequest request) {
        return tarifarioEscuelaCicloService.crear(request);
    }

    @PutMapping("/tarifarios-escuela-ciclo/{id}")
    public TarifarioEscuelaCicloResponse actualizarTarifario(@PathVariable Long id, @Valid @RequestBody TarifarioEscuelaCicloRequest request) {
        return tarifarioEscuelaCicloService.actualizar(id, request);
    }

    @DeleteMapping("/tarifarios-escuela-ciclo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTarifario(@PathVariable Long id) {
        tarifarioEscuelaCicloService.eliminar(id);
    }
}
