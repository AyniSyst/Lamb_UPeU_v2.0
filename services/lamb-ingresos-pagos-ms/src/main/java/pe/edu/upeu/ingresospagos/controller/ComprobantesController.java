package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.SerieComprobanteRequest;
import pe.edu.upeu.ingresospagos.dto.SerieComprobanteResponse;
import pe.edu.upeu.ingresospagos.dto.TipoComprobanteRequest;
import pe.edu.upeu.ingresospagos.dto.TipoComprobanteResponse;
import pe.edu.upeu.ingresospagos.service.SerieComprobanteService;
import pe.edu.upeu.ingresospagos.service.TipoComprobanteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos-pagos/comprobantes")
@RequiredArgsConstructor
public class ComprobantesController {

    private final TipoComprobanteService tipoComprobanteService;
    private final SerieComprobanteService serieComprobanteService;

    // --- tipos-comprobante ---

    @GetMapping("/tipos-comprobante")
    public List<TipoComprobanteResponse> listarTiposComprobante() {
        return tipoComprobanteService.findAll();
    }

    @GetMapping("/tipos-comprobante/{id}")
    public TipoComprobanteResponse obtenerTipoComprobante(@PathVariable Long id) {
        return tipoComprobanteService.findById(id);
    }

    @PostMapping("/tipos-comprobante")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoComprobanteResponse crearTipoComprobante(@Valid @RequestBody TipoComprobanteRequest request) {
        return tipoComprobanteService.create(request);
    }

    @PutMapping("/tipos-comprobante/{id}")
    public TipoComprobanteResponse actualizarTipoComprobante(@PathVariable Long id, @Valid @RequestBody TipoComprobanteRequest request) {
        return tipoComprobanteService.update(id, request);
    }

    @DeleteMapping("/tipos-comprobante/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTipoComprobante(@PathVariable Long id) {
        tipoComprobanteService.delete(id);
    }

    // --- series-comprobante ---

    @GetMapping("/series-comprobante")
    public List<SerieComprobanteResponse> listarSeriesComprobante() {
        return serieComprobanteService.listar();
    }

    @GetMapping("/series-comprobante/{id}")
    public SerieComprobanteResponse obtenerSerieComprobante(@PathVariable Long id) {
        return serieComprobanteService.obtener(id);
    }

    @PostMapping("/series-comprobante")
    @ResponseStatus(HttpStatus.CREATED)
    public SerieComprobanteResponse crearSerieComprobante(@Valid @RequestBody SerieComprobanteRequest request) {
        return serieComprobanteService.crear(request);
    }

    @PutMapping("/series-comprobante/{id}")
    public SerieComprobanteResponse actualizarSerieComprobante(@PathVariable Long id, @Valid @RequestBody SerieComprobanteRequest request) {
        return serieComprobanteService.actualizar(id, request);
    }

    @DeleteMapping("/series-comprobante/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarSerieComprobante(@PathVariable Long id) {
        serieComprobanteService.eliminar(id);
    }
}
