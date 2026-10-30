package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.LogTransaccionPasarelaRequest;
import pe.edu.upeu.ingresospagos.dto.LogTransaccionPasarelaResponse;
import pe.edu.upeu.ingresospagos.service.LogTransaccionPasarelaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos-pagos/pasarelas")
@RequiredArgsConstructor
public class PasarelasController {

    private final LogTransaccionPasarelaService logTransaccionPasarelaService;

    // --- logs-transaccion-pasarela ---

    @GetMapping("/logs-transaccion-pasarela")
    public List<LogTransaccionPasarelaResponse> listarLogsTransaccionPasarela() {
        return logTransaccionPasarelaService.listar();
    }

    @GetMapping("/logs-transaccion-pasarela/{id}")
    public LogTransaccionPasarelaResponse obtenerLogTransaccionPasarela(@PathVariable Long id) {
        return logTransaccionPasarelaService.obtener(id);
    }

    @PostMapping("/logs-transaccion-pasarela")
    @ResponseStatus(HttpStatus.CREATED)
    public LogTransaccionPasarelaResponse crearLogTransaccionPasarela(@Valid @RequestBody LogTransaccionPasarelaRequest request) {
        return logTransaccionPasarelaService.crear(request);
    }

    @PutMapping("/logs-transaccion-pasarela/{id}")
    public LogTransaccionPasarelaResponse actualizarLogTransaccionPasarela(@PathVariable Long id, @Valid @RequestBody LogTransaccionPasarelaRequest request) {
        return logTransaccionPasarelaService.actualizar(id, request);
    }

    @DeleteMapping("/logs-transaccion-pasarela/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarLogTransaccionPasarela(@PathVariable Long id) {
        logTransaccionPasarelaService.eliminar(id);
    }
}
