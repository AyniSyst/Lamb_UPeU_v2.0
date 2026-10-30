package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.config.JwtClaims;
import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudAperturaGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudAperturaGrupoResponse;
import pe.edu.upeu.lamb.planificacion.service.SolicitudAperturaGrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Solicitud de apertura de grupo", description = "CRUD 28 · Solicitud de apertura de grupo")
@RestController
@RequestMapping("/api/v1/solicitudes-apertura-grupo")
@RequiredArgsConstructor
public class SolicitudAperturaGrupoController {

    private final SolicitudAperturaGrupoService solicitudAperturaGrupoService;

    @GetMapping
    public List<SolicitudAperturaGrupoResponse> listar(@RequestParam(required = false) Long idOferta) {
        return solicitudAperturaGrupoService.listar(idOferta);
    }

    @GetMapping("/{id}")
    public SolicitudAperturaGrupoResponse obtener(@PathVariable Long id) {
        return solicitudAperturaGrupoService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudAperturaGrupoResponse crear(@Valid @RequestBody SolicitudAperturaGrupoRequest request, @AuthenticationPrincipal Jwt jwt) {
        return solicitudAperturaGrupoService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public SolicitudAperturaGrupoResponse actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudAperturaGrupoRequest request) {
        return solicitudAperturaGrupoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        solicitudAperturaGrupoService.eliminar(id);
    }

    @PostMapping("/{id}/responder")
    public SolicitudAperturaGrupoResponse responder(@PathVariable Long id,
                                 @Valid @RequestBody RespuestaSolicitudRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {
        return solicitudAperturaGrupoService.responder(id, request, JwtClaims.idPersona(jwt));
    }
}
