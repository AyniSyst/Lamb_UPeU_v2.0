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
public class SeguimientoController {

    private final AvanceContenidoService avanceContenidoService;
    private final AsistenciaService asistenciaService;
    private final EncuestaSatisfaccionService encuestaSatisfaccionService;
    private final InsigniaService insigniaService;
    private final InsigniaEstudianteService insigniaEstudianteService;

    // ---- Avances de contenido ----

    @GetMapping("/avances-contenido")
    public List<AvanceContenidoResponse> listarAvancesContenido() {
        return avanceContenidoService.listar();
    }

    @GetMapping("/avances-contenido/{id}")
    public AvanceContenidoResponse obtenerAvanceContenido(@PathVariable Long id) {
        return avanceContenidoService.obtener(id);
    }

    @PostMapping("/avances-contenido")
    @ResponseStatus(HttpStatus.CREATED)
    public AvanceContenidoResponse crearAvanceContenido(@Valid @RequestBody AvanceContenidoRequest request) {
        return avanceContenidoService.crear(request);
    }

    @PutMapping("/avances-contenido/{id}")
    public AvanceContenidoResponse actualizarAvanceContenido(@PathVariable Long id, @Valid @RequestBody AvanceContenidoRequest request) {
        return avanceContenidoService.actualizar(id, request);
    }

    @DeleteMapping("/avances-contenido/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAvanceContenido(@PathVariable Long id) {
        avanceContenidoService.eliminar(id);
    }

    // ---- Asistencias ----

    @GetMapping("/asistencias")
    public List<AsistenciaResponse> listarAsistencias() {
        return asistenciaService.listar();
    }

    @GetMapping("/asistencias/{id}")
    public AsistenciaResponse obtenerAsistencia(@PathVariable Long id) {
        return asistenciaService.obtener(id);
    }

    @PostMapping("/asistencias")
    @ResponseStatus(HttpStatus.CREATED)
    public AsistenciaResponse crearAsistencia(@Valid @RequestBody AsistenciaRequest request) {
        return asistenciaService.crear(request);
    }

    @PutMapping("/asistencias/{id}")
    public AsistenciaResponse actualizarAsistencia(@PathVariable Long id, @Valid @RequestBody AsistenciaRequest request) {
        return asistenciaService.actualizar(id, request);
    }

    @DeleteMapping("/asistencias/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAsistencia(@PathVariable Long id) {
        asistenciaService.eliminar(id);
    }

    // ---- Encuestas de satisfaccion ----

    @GetMapping("/encuestas-satisfaccion")
    public List<EncuestaSatisfaccionResponse> listarEncuestasSatisfaccion() {
        return encuestaSatisfaccionService.listar();
    }

    @GetMapping("/encuestas-satisfaccion/{id}")
    public EncuestaSatisfaccionResponse obtenerEncuestaSatisfaccion(@PathVariable Long id) {
        return encuestaSatisfaccionService.obtener(id);
    }

    @PostMapping("/encuestas-satisfaccion")
    @ResponseStatus(HttpStatus.CREATED)
    public EncuestaSatisfaccionResponse crearEncuestaSatisfaccion(@Valid @RequestBody EncuestaSatisfaccionRequest request) {
        return encuestaSatisfaccionService.crear(request);
    }

    @PutMapping("/encuestas-satisfaccion/{id}")
    public EncuestaSatisfaccionResponse actualizarEncuestaSatisfaccion(@PathVariable Long id, @Valid @RequestBody EncuestaSatisfaccionRequest request) {
        return encuestaSatisfaccionService.actualizar(id, request);
    }

    @DeleteMapping("/encuestas-satisfaccion/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEncuestaSatisfaccion(@PathVariable Long id) {
        encuestaSatisfaccionService.eliminar(id);
    }

    // ---- Insignias ----

    @GetMapping("/insignias")
    public List<InsigniaResponse> listarInsignias() {
        return insigniaService.listar();
    }

    @GetMapping("/insignias/{id}")
    public InsigniaResponse obtenerInsignia(@PathVariable Long id) {
        return insigniaService.obtener(id);
    }

    @PostMapping("/insignias")
    @ResponseStatus(HttpStatus.CREATED)
    public InsigniaResponse crearInsignia(@Valid @RequestBody InsigniaRequest request) {
        return insigniaService.crear(request);
    }

    @PutMapping("/insignias/{id}")
    public InsigniaResponse actualizarInsignia(@PathVariable Long id, @Valid @RequestBody InsigniaRequest request) {
        return insigniaService.actualizar(id, request);
    }

    @DeleteMapping("/insignias/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarInsignia(@PathVariable Long id) {
        insigniaService.eliminar(id);
    }

    // ---- Insignias de estudiante ----

    @GetMapping("/insignias-estudiante")
    public List<InsigniaEstudianteResponse> listarInsigniasEstudiante() {
        return insigniaEstudianteService.listar();
    }

    @GetMapping("/insignias-estudiante/{id}")
    public InsigniaEstudianteResponse obtenerInsigniaEstudiante(@PathVariable Long id) {
        return insigniaEstudianteService.obtener(id);
    }

    @PostMapping("/insignias-estudiante")
    @ResponseStatus(HttpStatus.CREATED)
    public InsigniaEstudianteResponse crearInsigniaEstudiante(@Valid @RequestBody InsigniaEstudianteRequest request) {
        return insigniaEstudianteService.crear(request);
    }

    @PutMapping("/insignias-estudiante/{id}")
    public InsigniaEstudianteResponse actualizarInsigniaEstudiante(@PathVariable Long id, @Valid @RequestBody InsigniaEstudianteRequest request) {
        return insigniaEstudianteService.actualizar(id, request);
    }

    @DeleteMapping("/insignias-estudiante/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarInsigniaEstudiante(@PathVariable Long id) {
        insigniaEstudianteService.eliminar(id);
    }
}
