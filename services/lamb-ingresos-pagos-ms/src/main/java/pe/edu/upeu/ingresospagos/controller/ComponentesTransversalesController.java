package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.BitacoraAuditoriaPagoRequest;
import pe.edu.upeu.ingresospagos.dto.BitacoraAuditoriaPagoResponse;
import pe.edu.upeu.ingresospagos.dto.PlantillaNotificacionRequest;
import pe.edu.upeu.ingresospagos.dto.PlantillaNotificacionResponse;
import pe.edu.upeu.ingresospagos.service.BitacoraAuditoriaPagoService;
import pe.edu.upeu.ingresospagos.service.PlantillaNotificacionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos-pagos/transversales")
@RequiredArgsConstructor
public class ComponentesTransversalesController {

    private final BitacoraAuditoriaPagoService bitacoraAuditoriaPagoService;
    private final PlantillaNotificacionService plantillaNotificacionService;

    // --- bitacoras-auditoria-pago ---

    @GetMapping("/bitacoras-auditoria-pago")
    public List<BitacoraAuditoriaPagoResponse> listarBitacorasAuditoriaPago() {
        return bitacoraAuditoriaPagoService.listar();
    }

    @GetMapping("/bitacoras-auditoria-pago/{id}")
    public BitacoraAuditoriaPagoResponse obtenerBitacoraAuditoriaPago(@PathVariable Long id) {
        return bitacoraAuditoriaPagoService.obtener(id);
    }

    @PostMapping("/bitacoras-auditoria-pago")
    @ResponseStatus(HttpStatus.CREATED)
    public BitacoraAuditoriaPagoResponse crearBitacoraAuditoriaPago(@Valid @RequestBody BitacoraAuditoriaPagoRequest request) {
        return bitacoraAuditoriaPagoService.crear(request);
    }

    @PutMapping("/bitacoras-auditoria-pago/{id}")
    public BitacoraAuditoriaPagoResponse actualizarBitacoraAuditoriaPago(@PathVariable Long id, @Valid @RequestBody BitacoraAuditoriaPagoRequest request) {
        return bitacoraAuditoriaPagoService.actualizar(id, request);
    }

    @DeleteMapping("/bitacoras-auditoria-pago/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarBitacoraAuditoriaPago(@PathVariable Long id) {
        bitacoraAuditoriaPagoService.eliminar(id);
    }

    // --- plantillas-notificacion ---

    @GetMapping("/plantillas-notificacion")
    public List<PlantillaNotificacionResponse> listarPlantillasNotificacion() {
        return plantillaNotificacionService.findAll();
    }

    @GetMapping("/plantillas-notificacion/{id}")
    public PlantillaNotificacionResponse obtenerPlantillaNotificacion(@PathVariable Long id) {
        return plantillaNotificacionService.findById(id);
    }

    @PostMapping("/plantillas-notificacion")
    @ResponseStatus(HttpStatus.CREATED)
    public PlantillaNotificacionResponse crearPlantillaNotificacion(@Valid @RequestBody PlantillaNotificacionRequest request) {
        return plantillaNotificacionService.create(request);
    }

    @PutMapping("/plantillas-notificacion/{id}")
    public PlantillaNotificacionResponse actualizarPlantillaNotificacion(@PathVariable Long id, @Valid @RequestBody PlantillaNotificacionRequest request) {
        return plantillaNotificacionService.update(id, request);
    }

    @DeleteMapping("/plantillas-notificacion/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPlantillaNotificacion(@PathVariable Long id) {
        plantillaNotificacionService.delete(id);
    }
}
