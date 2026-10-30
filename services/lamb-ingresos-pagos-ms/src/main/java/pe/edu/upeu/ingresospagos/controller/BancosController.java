package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.EntidadFinancieraRequest;
import pe.edu.upeu.ingresospagos.dto.EntidadFinancieraResponse;
import pe.edu.upeu.ingresospagos.dto.TipoCambioRequest;
import pe.edu.upeu.ingresospagos.dto.TipoCambioResponse;
import pe.edu.upeu.ingresospagos.service.EntidadFinancieraService;
import pe.edu.upeu.ingresospagos.service.TipoCambioService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos-pagos/bancos")
@RequiredArgsConstructor
public class BancosController {

    private final EntidadFinancieraService entidadFinancieraService;
    private final TipoCambioService tipoCambioService;

    // --- entidades-financieras ---

    @GetMapping("/entidades-financieras")
    public List<EntidadFinancieraResponse> listarEntidadesFinancieras() {
        return entidadFinancieraService.findAll();
    }

    @GetMapping("/entidades-financieras/{id}")
    public EntidadFinancieraResponse obtenerEntidadFinanciera(@PathVariable Long id) {
        return entidadFinancieraService.findById(id);
    }

    @PostMapping("/entidades-financieras")
    @ResponseStatus(HttpStatus.CREATED)
    public EntidadFinancieraResponse crearEntidadFinanciera(@Valid @RequestBody EntidadFinancieraRequest request) {
        return entidadFinancieraService.create(request);
    }

    @PutMapping("/entidades-financieras/{id}")
    public EntidadFinancieraResponse actualizarEntidadFinanciera(@PathVariable Long id, @Valid @RequestBody EntidadFinancieraRequest request) {
        return entidadFinancieraService.update(id, request);
    }

    @DeleteMapping("/entidades-financieras/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEntidadFinanciera(@PathVariable Long id) {
        entidadFinancieraService.delete(id);
    }

    // --- tipos-cambio ---

    @GetMapping("/tipos-cambio")
    public List<TipoCambioResponse> listarTiposCambio() {
        return tipoCambioService.findAll();
    }

    @GetMapping("/tipos-cambio/{id}")
    public TipoCambioResponse obtenerTipoCambio(@PathVariable Long id) {
        return tipoCambioService.findById(id);
    }

    @PostMapping("/tipos-cambio")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoCambioResponse crearTipoCambio(@Valid @RequestBody TipoCambioRequest request) {
        return tipoCambioService.create(request);
    }

    @PutMapping("/tipos-cambio/{id}")
    public TipoCambioResponse actualizarTipoCambio(@PathVariable Long id, @Valid @RequestBody TipoCambioRequest request) {
        return tipoCambioService.update(id, request);
    }

    @DeleteMapping("/tipos-cambio/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTipoCambio(@PathVariable Long id) {
        tipoCambioService.delete(id);
    }
}
