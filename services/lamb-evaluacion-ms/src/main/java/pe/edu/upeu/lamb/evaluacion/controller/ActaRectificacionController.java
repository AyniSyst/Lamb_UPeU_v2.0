package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.config.JwtClaims;
import pe.edu.upeu.lamb.evaluacion.dto.ActaRectificacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ActaRectificacionResponse;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.evaluacion.service.ActaRectificacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Acta de rectificacion", description = "CRUD 13 · Acta de rectificacion de la nota final de un estudiante")
@RestController
@RequestMapping("/api/v1/actas-rectificacion")
@RequiredArgsConstructor
public class ActaRectificacionController {

    private final ActaRectificacionService actaRectificacionService;

    @GetMapping
    public List<ActaRectificacionResponse> listar(@RequestParam(required = false) Long idActa) {
        return actaRectificacionService.listar(idActa);
    }

    @GetMapping("/{id}")
    public ActaRectificacionResponse obtener(@PathVariable Long id) {
        return actaRectificacionService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActaRectificacionResponse crear(@Valid @RequestBody ActaRectificacionRequest request, @AuthenticationPrincipal Jwt jwt) {
        return actaRectificacionService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public ActaRectificacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody ActaRectificacionRequest request) {
        return actaRectificacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        actaRectificacionService.eliminar(id);
    }

    @PostMapping("/{id}/responder")
    public ActaRectificacionResponse responder(@PathVariable Long id,
                                 @Valid @RequestBody RespuestaSolicitudRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {
        return actaRectificacionService.responder(id, request, JwtClaims.idPersona(jwt));
    }
}
