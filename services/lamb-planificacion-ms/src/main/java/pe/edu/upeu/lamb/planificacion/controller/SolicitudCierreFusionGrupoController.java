package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.config.JwtClaims;
import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCierreFusionGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCierreFusionGrupoResponse;
import pe.edu.upeu.lamb.planificacion.service.SolicitudCierreFusionGrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Solicitud de cierre o fusion de grupos", description = "CRUD 29 · Solicitud de cierre o fusion de grupos")
@RestController
@RequestMapping("/api/v1/solicitudes-cierre-fusion")
@RequiredArgsConstructor
public class SolicitudCierreFusionGrupoController {

    private final SolicitudCierreFusionGrupoService solicitudCierreFusionGrupoService;

    @GetMapping
    public List<SolicitudCierreFusionGrupoResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return solicitudCierreFusionGrupoService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public SolicitudCierreFusionGrupoResponse obtener(@PathVariable Long id) {
        return solicitudCierreFusionGrupoService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudCierreFusionGrupoResponse crear(@Valid @RequestBody SolicitudCierreFusionGrupoRequest request, @AuthenticationPrincipal Jwt jwt) {
        return solicitudCierreFusionGrupoService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public SolicitudCierreFusionGrupoResponse actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudCierreFusionGrupoRequest request) {
        return solicitudCierreFusionGrupoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        solicitudCierreFusionGrupoService.eliminar(id);
    }

    @PostMapping("/{id}/responder")
    public SolicitudCierreFusionGrupoResponse responder(@PathVariable Long id,
                                 @Valid @RequestBody RespuestaSolicitudRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {
        return solicitudCierreFusionGrupoService.responder(id, request, JwtClaims.idPersona(jwt));
    }
}
