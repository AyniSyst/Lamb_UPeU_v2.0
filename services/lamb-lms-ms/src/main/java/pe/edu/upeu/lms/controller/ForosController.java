package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.*;
import pe.edu.upeu.lms.service.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/lms")
@RequiredArgsConstructor
public class ForosController {

    private final ForoHiloService foroHiloService;
    private final RespuestaForoService respuestaForoService;

    // ---- Foros - hilos ----

    @GetMapping("/foros-hilo")
    public List<ForoHiloResponse> listarForosHilo() {
        return foroHiloService.listar();
    }

    @GetMapping("/foros-hilo/{id}")
    public ForoHiloResponse obtenerForoHilo(@PathVariable Long id) {
        return foroHiloService.obtener(id);
    }

    @PostMapping("/foros-hilo")
    @ResponseStatus(HttpStatus.CREATED)
    public ForoHiloResponse crearForoHilo(@Valid @RequestBody ForoHiloRequest request) {
        return foroHiloService.crear(request);
    }

    @PutMapping("/foros-hilo/{id}")
    public ForoHiloResponse actualizarForoHilo(@PathVariable Long id, @Valid @RequestBody ForoHiloRequest request) {
        return foroHiloService.actualizar(id, request);
    }

    @DeleteMapping("/foros-hilo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarForoHilo(@PathVariable Long id) {
        foroHiloService.eliminar(id);
    }

    // ---- Respuestas de foro ----

    @GetMapping("/respuestas-foro")
    public List<RespuestaForoResponse> listarRespuestasForo() {
        return respuestaForoService.listar();
    }

    @GetMapping("/respuestas-foro/{id}")
    public RespuestaForoResponse obtenerRespuestaForo(@PathVariable Long id) {
        return respuestaForoService.obtener(id);
    }

    @PostMapping("/respuestas-foro")
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaForoResponse crearRespuestaForo(@Valid @RequestBody RespuestaForoRequest request) {
        return respuestaForoService.crear(request);
    }

    @PutMapping("/respuestas-foro/{id}")
    public RespuestaForoResponse actualizarRespuestaForo(@PathVariable Long id, @Valid @RequestBody RespuestaForoRequest request) {
        return respuestaForoService.actualizar(id, request);
    }

    @DeleteMapping("/respuestas-foro/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarRespuestaForo(@PathVariable Long id) {
        respuestaForoService.eliminar(id);
    }
}
