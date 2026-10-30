package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.config.JwtClaims;
import pe.edu.upeu.lamb.evaluacion.dto.JustificacionInasistenciaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.JustificacionInasistenciaResponse;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.evaluacion.service.JustificacionInasistenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Justificacion de inasistencia", description = "CRUD 10 · Justificacion de inasistencia (al aprobarse la falta pasa a JUSTIFICADA)")
@RestController
@RequestMapping("/api/v1/justificaciones-inasistencia")
@RequiredArgsConstructor
public class JustificacionInasistenciaController {

    private final JustificacionInasistenciaService justificacionInasistenciaService;

    @GetMapping
    public List<JustificacionInasistenciaResponse> listar(@RequestParam(required = false) Long idRegistro) {
        return justificacionInasistenciaService.listar(idRegistro);
    }

    @GetMapping("/{id}")
    public JustificacionInasistenciaResponse obtener(@PathVariable Long id) {
        return justificacionInasistenciaService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JustificacionInasistenciaResponse crear(@Valid @RequestBody JustificacionInasistenciaRequest request, @AuthenticationPrincipal Jwt jwt) {
        return justificacionInasistenciaService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public JustificacionInasistenciaResponse actualizar(@PathVariable Long id, @Valid @RequestBody JustificacionInasistenciaRequest request) {
        return justificacionInasistenciaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        justificacionInasistenciaService.eliminar(id);
    }

    @PostMapping("/{id}/responder")
    public JustificacionInasistenciaResponse responder(@PathVariable Long id,
                                 @Valid @RequestBody RespuestaSolicitudRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {
        return justificacionInasistenciaService.responder(id, request, JwtClaims.idPersona(jwt));
    }
}
