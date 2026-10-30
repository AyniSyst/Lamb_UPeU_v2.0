package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.config.JwtClaims;
import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioDocenteResponse;
import pe.edu.upeu.lamb.planificacion.service.SolicitudCambioDocenteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Solicitud de cambio de docente", description = "CRUD 30 · Solicitud de cambio de docente (al aprobarse cambia la asignacion, con la misma validacion de cruces)")
@RestController
@RequestMapping("/api/v1/solicitudes-cambio-docente")
@RequiredArgsConstructor
public class SolicitudCambioDocenteController {

    private final SolicitudCambioDocenteService solicitudCambioDocenteService;

    @GetMapping
    public List<SolicitudCambioDocenteResponse> listar(@RequestParam(required = false) Long idAsignacion) {
        return solicitudCambioDocenteService.listar(idAsignacion);
    }

    @GetMapping("/{id}")
    public SolicitudCambioDocenteResponse obtener(@PathVariable Long id) {
        return solicitudCambioDocenteService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudCambioDocenteResponse crear(@Valid @RequestBody SolicitudCambioDocenteRequest request, @AuthenticationPrincipal Jwt jwt) {
        return solicitudCambioDocenteService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public SolicitudCambioDocenteResponse actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudCambioDocenteRequest request) {
        return solicitudCambioDocenteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        solicitudCambioDocenteService.eliminar(id);
    }

    @PostMapping("/{id}/responder")
    public SolicitudCambioDocenteResponse responder(@PathVariable Long id,
                                 @Valid @RequestBody RespuestaSolicitudRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {
        return solicitudCambioDocenteService.responder(id, request, JwtClaims.idPersona(jwt));
    }
}
