package pe.edu.upeu.ingresospagos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.EstadoCuentaResponse;
import pe.edu.upeu.ingresospagos.dto.MetodoPagoRequest;
import pe.edu.upeu.ingresospagos.dto.MetodoPagoResponse;
import pe.edu.upeu.ingresospagos.dto.PagoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import pe.edu.upeu.ingresospagos.service.EstudianteService;
import pe.edu.upeu.ingresospagos.service.MetodoPagoService;
import pe.edu.upeu.ingresospagos.service.PagoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos-pagos/pagos-dominio")
@RequiredArgsConstructor
public class PagosController {

    private final EstudianteService estudianteService;
    private final MetodoPagoService metodoPagoService;
    private final PagoService pagoService;

    // --- estudiantes ---

    @GetMapping("/estudiantes/{id}/estado-cuenta")
    public EstadoCuentaResponse obtenerEstadoCuenta(@PathVariable("id") String id) {
        return estudianteService.obtenerEstadoCuenta(id);
    }

    // --- metodos-pago ---

    @GetMapping("/metodos-pago")
    public List<MetodoPagoResponse> listarMetodosPago() {
        return metodoPagoService.findAll();
    }

    @GetMapping("/metodos-pago/{id}")
    public MetodoPagoResponse obtenerMetodoPago(@PathVariable Long id) {
        return metodoPagoService.findById(id);
    }

    @PostMapping("/metodos-pago")
    @ResponseStatus(HttpStatus.CREATED)
    public MetodoPagoResponse crearMetodoPago(@Valid @RequestBody MetodoPagoRequest request) {
        return metodoPagoService.create(request);
    }

    @PutMapping("/metodos-pago/{id}")
    public MetodoPagoResponse actualizarMetodoPago(@PathVariable Long id, @Valid @RequestBody MetodoPagoRequest request) {
        return metodoPagoService.update(id, request);
    }

    @DeleteMapping("/metodos-pago/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMetodoPago(@PathVariable Long id) {
        metodoPagoService.delete(id);
    }

    // --- pagos ---

    @GetMapping("/pagos")
    public List<PagoResponse> listarPagos() {
        return pagoService.listar();
    }

    @GetMapping("/pagos/{id}")
    public PagoResponse obtenerPago(@PathVariable Long id) {
        return pagoService.obtener(id);
    }

    @GetMapping("/pagos/por-matricula/{matriculaId}")
    public ResponseEntity<PagoResponse> buscarPagoPorMatricula(@PathVariable Long matriculaId) {
        return pagoService.buscarPorMatricula(matriculaId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
