package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.ConceptoPagoRequest;
import pe.edu.upeu.ingresospagos.dto.ConceptoPagoResponse;
import pe.edu.upeu.ingresospagos.dto.RecargoMoraRequest;
import pe.edu.upeu.ingresospagos.dto.RecargoMoraResponse;
import pe.edu.upeu.ingresospagos.dto.TarifarioProgramaRequest;
import pe.edu.upeu.ingresospagos.dto.TarifarioProgramaResponse;
import pe.edu.upeu.ingresospagos.service.ConceptoPagoService;
import pe.edu.upeu.ingresospagos.service.RecargoMoraService;
import pe.edu.upeu.ingresospagos.service.TarifarioProgramaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos-pagos/cargos")
@RequiredArgsConstructor
public class CargosController {

    private final ConceptoPagoService conceptoPagoService;
    private final TarifarioProgramaService tarifarioProgramaService;
    private final RecargoMoraService recargoMoraService;

    // --- conceptos-pago ---

    @GetMapping("/conceptos-pago")
    public List<ConceptoPagoResponse> listarConceptosPago() {
        return conceptoPagoService.findAll();
    }

    @GetMapping("/conceptos-pago/{id}")
    public ConceptoPagoResponse obtenerConceptoPago(@PathVariable Long id) {
        return conceptoPagoService.findById(id);
    }

    @PostMapping("/conceptos-pago")
    @ResponseStatus(HttpStatus.CREATED)
    public ConceptoPagoResponse crearConceptoPago(@Valid @RequestBody ConceptoPagoRequest request) {
        return conceptoPagoService.create(request);
    }

    @PutMapping("/conceptos-pago/{id}")
    public ConceptoPagoResponse actualizarConceptoPago(@PathVariable Long id, @Valid @RequestBody ConceptoPagoRequest request) {
        return conceptoPagoService.update(id, request);
    }

    @DeleteMapping("/conceptos-pago/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarConceptoPago(@PathVariable Long id) {
        conceptoPagoService.delete(id);
    }

    // --- tarifarios-programa ---

    @GetMapping("/tarifarios-programa")
    public List<TarifarioProgramaResponse> listarTarifariosPrograma() {
        return tarifarioProgramaService.listar();
    }

    @GetMapping("/tarifarios-programa/{id}")
    public TarifarioProgramaResponse obtenerTarifarioPrograma(@PathVariable Long id) {
        return tarifarioProgramaService.obtener(id);
    }

    @PostMapping("/tarifarios-programa")
    @ResponseStatus(HttpStatus.CREATED)
    public TarifarioProgramaResponse crearTarifarioPrograma(@Valid @RequestBody TarifarioProgramaRequest request) {
        return tarifarioProgramaService.crear(request);
    }

    @PutMapping("/tarifarios-programa/{id}")
    public TarifarioProgramaResponse actualizarTarifarioPrograma(@PathVariable Long id, @Valid @RequestBody TarifarioProgramaRequest request) {
        return tarifarioProgramaService.actualizar(id, request);
    }

    @DeleteMapping("/tarifarios-programa/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTarifarioPrograma(@PathVariable Long id) {
        tarifarioProgramaService.eliminar(id);
    }

    // --- recargos-mora ---

    @GetMapping("/recargos-mora")
    public List<RecargoMoraResponse> listarRecargosMora() {
        return recargoMoraService.findAll();
    }

    @GetMapping("/recargos-mora/{id}")
    public RecargoMoraResponse obtenerRecargoMora(@PathVariable Long id) {
        return recargoMoraService.findById(id);
    }

    @PostMapping("/recargos-mora")
    @ResponseStatus(HttpStatus.CREATED)
    public RecargoMoraResponse crearRecargoMora(@Valid @RequestBody RecargoMoraRequest request) {
        return recargoMoraService.create(request);
    }

    @PutMapping("/recargos-mora/{id}")
    public RecargoMoraResponse actualizarRecargoMora(@PathVariable Long id, @Valid @RequestBody RecargoMoraRequest request) {
        return recargoMoraService.update(id, request);
    }

    @DeleteMapping("/recargos-mora/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarRecargoMora(@PathVariable Long id) {
        recargoMoraService.delete(id);
    }
}
