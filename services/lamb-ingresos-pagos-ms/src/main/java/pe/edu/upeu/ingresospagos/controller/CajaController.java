package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.ArqueoCajaRequest;
import pe.edu.upeu.ingresospagos.dto.ArqueoCajaResponse;
import pe.edu.upeu.ingresospagos.dto.CajaRequest;
import pe.edu.upeu.ingresospagos.dto.CajaResponse;
import pe.edu.upeu.ingresospagos.dto.SesionCajaRequest;
import pe.edu.upeu.ingresospagos.dto.SesionCajaResponse;
import pe.edu.upeu.ingresospagos.service.ArqueoCajaService;
import pe.edu.upeu.ingresospagos.service.CajaService;
import pe.edu.upeu.ingresospagos.service.SesionCajaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos-pagos/caja")
@RequiredArgsConstructor
public class CajaController {

    private final CajaService cajaService;
    private final SesionCajaService sesionCajaService;
    private final ArqueoCajaService arqueoCajaService;

    // --- cajas ---

    @GetMapping("/cajas")
    public List<CajaResponse> listarCajas() {
        return cajaService.findAll();
    }

    @GetMapping("/cajas/{id}")
    public CajaResponse obtenerCaja(@PathVariable Long id) {
        return cajaService.findById(id);
    }

    @PostMapping("/cajas")
    @ResponseStatus(HttpStatus.CREATED)
    public CajaResponse crearCaja(@Valid @RequestBody CajaRequest request) {
        return cajaService.create(request);
    }

    @PutMapping("/cajas/{id}")
    public CajaResponse actualizarCaja(@PathVariable Long id, @Valid @RequestBody CajaRequest request) {
        return cajaService.update(id, request);
    }

    @DeleteMapping("/cajas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCaja(@PathVariable Long id) {
        cajaService.delete(id);
    }

    // --- sesiones-caja ---

    @GetMapping("/sesiones-caja")
    public List<SesionCajaResponse> listarSesionesCaja() {
        return sesionCajaService.listar();
    }

    @GetMapping("/sesiones-caja/{id}")
    public SesionCajaResponse obtenerSesionCaja(@PathVariable Long id) {
        return sesionCajaService.obtener(id);
    }

    @PostMapping("/sesiones-caja")
    @ResponseStatus(HttpStatus.CREATED)
    public SesionCajaResponse crearSesionCaja(@Valid @RequestBody SesionCajaRequest request) {
        return sesionCajaService.crear(request);
    }

    @PutMapping("/sesiones-caja/{id}")
    public SesionCajaResponse actualizarSesionCaja(@PathVariable Long id, @Valid @RequestBody SesionCajaRequest request) {
        return sesionCajaService.actualizar(id, request);
    }

    @DeleteMapping("/sesiones-caja/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarSesionCaja(@PathVariable Long id) {
        sesionCajaService.eliminar(id);
    }

    // --- arqueos-caja ---

    @GetMapping("/arqueos-caja")
    public List<ArqueoCajaResponse> listarArqueosCaja() {
        return arqueoCajaService.listar();
    }

    @GetMapping("/arqueos-caja/{id}")
    public ArqueoCajaResponse obtenerArqueoCaja(@PathVariable Long id) {
        return arqueoCajaService.obtener(id);
    }

    @PostMapping("/arqueos-caja")
    @ResponseStatus(HttpStatus.CREATED)
    public ArqueoCajaResponse crearArqueoCaja(@Valid @RequestBody ArqueoCajaRequest request) {
        return arqueoCajaService.crear(request);
    }

    @PutMapping("/arqueos-caja/{id}")
    public ArqueoCajaResponse actualizarArqueoCaja(@PathVariable Long id, @Valid @RequestBody ArqueoCajaRequest request) {
        return arqueoCajaService.actualizar(id, request);
    }

    @DeleteMapping("/arqueos-caja/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarArqueoCaja(@PathVariable Long id) {
        arqueoCajaService.eliminar(id);
    }
}
