package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.config.JwtClaims;
import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudGrupoEquivalenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudGrupoEquivalenteResponse;
import pe.edu.upeu.lamb.planificacion.service.SolicitudGrupoEquivalenteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Solicitud para cursar en grupo equivalente", description = "CRUD 35 · Solicitud del estudiante para llevar el curso en un grupo habilitado por equivalencia (CRUD 34)")
@RestController
@RequestMapping("/api/v1/solicitudes-grupo-equivalente")
@RequiredArgsConstructor
public class SolicitudGrupoEquivalenteController {

    private final SolicitudGrupoEquivalenteService solicitudGrupoEquivalenteService;

    @GetMapping
    public List<SolicitudGrupoEquivalenteResponse> listar(@RequestParam(required = false) Long idGrupoHabilitado) {
        return solicitudGrupoEquivalenteService.listar(idGrupoHabilitado);
    }

    @GetMapping("/{id}")
    public SolicitudGrupoEquivalenteResponse obtener(@PathVariable Long id) {
        return solicitudGrupoEquivalenteService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudGrupoEquivalenteResponse crear(@Valid @RequestBody SolicitudGrupoEquivalenteRequest request, @AuthenticationPrincipal Jwt jwt) {
        return solicitudGrupoEquivalenteService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public SolicitudGrupoEquivalenteResponse actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudGrupoEquivalenteRequest request) {
        return solicitudGrupoEquivalenteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        solicitudGrupoEquivalenteService.eliminar(id);
    }

    @PostMapping("/{id}/responder")
    public SolicitudGrupoEquivalenteResponse responder(@PathVariable Long id,
                                 @Valid @RequestBody RespuestaSolicitudRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {
        return solicitudGrupoEquivalenteService.responder(id, request, JwtClaims.idPersona(jwt));
    }
}
