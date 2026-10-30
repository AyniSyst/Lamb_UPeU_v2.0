package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.config.JwtClaims;
import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioHorarioRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioHorarioResponse;
import pe.edu.upeu.lamb.planificacion.service.SolicitudCambioHorarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Solicitud de cambio de horario", description = "CRUD 31 · Solicitud de cambio de horario (al aprobarse mueve el horario, con la misma validacion de cruces)")
@RestController
@RequestMapping("/api/v1/solicitudes-cambio-horario")
@RequiredArgsConstructor
public class SolicitudCambioHorarioController {

    private final SolicitudCambioHorarioService solicitudCambioHorarioService;

    @GetMapping
    public List<SolicitudCambioHorarioResponse> listar(@RequestParam(required = false) Long idHorario) {
        return solicitudCambioHorarioService.listar(idHorario);
    }

    @GetMapping("/{id}")
    public SolicitudCambioHorarioResponse obtener(@PathVariable Long id) {
        return solicitudCambioHorarioService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudCambioHorarioResponse crear(@Valid @RequestBody SolicitudCambioHorarioRequest request, @AuthenticationPrincipal Jwt jwt) {
        return solicitudCambioHorarioService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public SolicitudCambioHorarioResponse actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudCambioHorarioRequest request) {
        return solicitudCambioHorarioService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        solicitudCambioHorarioService.eliminar(id);
    }

    @PostMapping("/{id}/responder")
    public SolicitudCambioHorarioResponse responder(@PathVariable Long id,
                                 @Valid @RequestBody RespuestaSolicitudRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {
        return solicitudCambioHorarioService.responder(id, request, JwtClaims.idPersona(jwt));
    }
}
