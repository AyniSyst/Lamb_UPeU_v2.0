package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.config.JwtClaims;
import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioAulaRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioAulaResponse;
import pe.edu.upeu.lamb.planificacion.service.SolicitudCambioAulaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Solicitud de cambio de aula", description = "CRUD 32 · Solicitud de cambio de aula (al aprobarse cambia el ambiente, con la misma validacion de cruces)")
@RestController
@RequestMapping("/api/v1/solicitudes-cambio-aula")
@RequiredArgsConstructor
public class SolicitudCambioAulaController {

    private final SolicitudCambioAulaService solicitudCambioAulaService;

    @GetMapping
    public List<SolicitudCambioAulaResponse> listar(@RequestParam(required = false) Long idHorario) {
        return solicitudCambioAulaService.listar(idHorario);
    }

    @GetMapping("/{id}")
    public SolicitudCambioAulaResponse obtener(@PathVariable Long id) {
        return solicitudCambioAulaService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudCambioAulaResponse crear(@Valid @RequestBody SolicitudCambioAulaRequest request, @AuthenticationPrincipal Jwt jwt) {
        return solicitudCambioAulaService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public SolicitudCambioAulaResponse actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudCambioAulaRequest request) {
        return solicitudCambioAulaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        solicitudCambioAulaService.eliminar(id);
    }

    @PostMapping("/{id}/responder")
    public SolicitudCambioAulaResponse responder(@PathVariable Long id,
                                 @Valid @RequestBody RespuestaSolicitudRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {
        return solicitudCambioAulaService.responder(id, request, JwtClaims.idPersona(jwt));
    }
}
