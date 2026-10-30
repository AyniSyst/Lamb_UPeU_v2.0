package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.NotaCreditoDebitoRequest;
import pe.edu.upeu.ingresospagos.dto.NotaCreditoDebitoResponse;
import pe.edu.upeu.ingresospagos.service.NotaCreditoDebitoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos-pagos/devoluciones")
@RequiredArgsConstructor
public class DevolucionesController {

    private final NotaCreditoDebitoService notaCreditoDebitoService;

    // --- notas-credito-debito ---

    @GetMapping("/notas-credito-debito")
    public List<NotaCreditoDebitoResponse> listarNotasCreditoDebito() {
        return notaCreditoDebitoService.listar();
    }

    @GetMapping("/notas-credito-debito/{id}")
    public NotaCreditoDebitoResponse obtenerNotaCreditoDebito(@PathVariable Long id) {
        return notaCreditoDebitoService.obtener(id);
    }

    @PostMapping("/notas-credito-debito")
    @ResponseStatus(HttpStatus.CREATED)
    public NotaCreditoDebitoResponse crearNotaCreditoDebito(@Valid @RequestBody NotaCreditoDebitoRequest request) {
        return notaCreditoDebitoService.crear(request);
    }

    @PutMapping("/notas-credito-debito/{id}")
    public NotaCreditoDebitoResponse actualizarNotaCreditoDebito(@PathVariable Long id, @Valid @RequestBody NotaCreditoDebitoRequest request) {
        return notaCreditoDebitoService.actualizar(id, request);
    }

    @DeleteMapping("/notas-credito-debito/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarNotaCreditoDebito(@PathVariable Long id) {
        notaCreditoDebitoService.eliminar(id);
    }
}
