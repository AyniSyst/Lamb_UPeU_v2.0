package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.config.JwtClaims;
import pe.edu.upeu.lamb.curriculo.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.curriculo.dto.SolicitudModificacionCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.SolicitudModificacionCurricularResponse;
import pe.edu.upeu.lamb.curriculo.service.SolicitudModificacionCurricularService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Solicitud de modificacion curricular", description = "CRUD 46 · Solicitud de modificacion curricular")
@RestController
@RequestMapping("/api/v1/solicitudes-modificacion-curricular")
@RequiredArgsConstructor
public class SolicitudModificacionCurricularController {

    private final SolicitudModificacionCurricularService solicitudModificacionCurricularService;

    @GetMapping
    public List<SolicitudModificacionCurricularResponse> listar(@RequestParam(required = false) Long idPlan) {
        return solicitudModificacionCurricularService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public SolicitudModificacionCurricularResponse obtener(@PathVariable Long id) {
        return solicitudModificacionCurricularService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudModificacionCurricularResponse crear(@Valid @RequestBody SolicitudModificacionCurricularRequest request, @AuthenticationPrincipal Jwt jwt) {
        return solicitudModificacionCurricularService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public SolicitudModificacionCurricularResponse actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudModificacionCurricularRequest request) {
        return solicitudModificacionCurricularService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        solicitudModificacionCurricularService.eliminar(id);
    }

    @PostMapping("/{id}/responder")
    public SolicitudModificacionCurricularResponse responder(@PathVariable Long id,
                                 @Valid @RequestBody RespuestaSolicitudRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {
        return solicitudModificacionCurricularService.responder(id, request, JwtClaims.idPersona(jwt));
    }
}
