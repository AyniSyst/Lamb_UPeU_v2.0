package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudAperturaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudAperturaResponse;
import pe.edu.upeu.lamb.evaluacion.service.RespuestaSolicitudAperturaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import pe.edu.upeu.lamb.evaluacion.config.JwtClaims;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Respuesta a la solicitud de apertura", description = "CRUD 16 · Respuesta / aprobacion de la solicitud de apertura (quien la aprueba y cuando)")
@RestController
@RequestMapping("/api/v1/respuestas-solicitud-apertura")
@RequiredArgsConstructor
public class RespuestaSolicitudAperturaController {

    private final RespuestaSolicitudAperturaService respuestaSolicitudAperturaService;

    @GetMapping
    public List<RespuestaSolicitudAperturaResponse> listar(@RequestParam(required = false) Long idSolicitud) {
        return respuestaSolicitudAperturaService.listar(idSolicitud);
    }

    @GetMapping("/{id}")
    public RespuestaSolicitudAperturaResponse obtener(@PathVariable Long id) {
        return respuestaSolicitudAperturaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaSolicitudAperturaResponse crear(@Valid @RequestBody RespuestaSolicitudAperturaRequest request,
                                                    @AuthenticationPrincipal Jwt jwt) {
        // Quien responde sale del JWT (claim idPersona), no del cuerpo
        return respuestaSolicitudAperturaService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public RespuestaSolicitudAperturaResponse actualizar(@PathVariable Long id, @Valid @RequestBody RespuestaSolicitudAperturaRequest request) {
        return respuestaSolicitudAperturaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        respuestaSolicitudAperturaService.eliminar(id);
    }
}
