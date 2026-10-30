package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.config.JwtClaims;
import pe.edu.upeu.lamb.evaluacion.dto.SolicitudAperturaNotasRequest;
import pe.edu.upeu.lamb.evaluacion.dto.SolicitudAperturaNotasResponse;
import pe.edu.upeu.lamb.evaluacion.service.SolicitudAperturaNotasService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Solicitud de apertura para notas rezagadas", description = "CRUD 15 · Solicitud de apertura del sistema para notas rezagadas (se responde con el CRUD 16)")
@RestController
@RequestMapping("/api/v1/solicitudes-apertura-notas")
@RequiredArgsConstructor
public class SolicitudAperturaNotasController {

    private final SolicitudAperturaNotasService solicitudAperturaNotasService;

    @GetMapping
    public List<SolicitudAperturaNotasResponse> listar(@RequestParam(required = false) Long idGrupo) {
        return solicitudAperturaNotasService.listar(idGrupo);
    }

    @GetMapping("/{id}")
    public SolicitudAperturaNotasResponse obtener(@PathVariable Long id) {
        return solicitudAperturaNotasService.obtener(id);
    }

    // El solicitante sale del JWT (claim idPersona), no del cuerpo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudAperturaNotasResponse crear(@Valid @RequestBody SolicitudAperturaNotasRequest request, @AuthenticationPrincipal Jwt jwt) {
        return solicitudAperturaNotasService.crear(request, JwtClaims.idPersona(jwt));
    }

    @PutMapping("/{id}")
    public SolicitudAperturaNotasResponse actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudAperturaNotasRequest request) {
        return solicitudAperturaNotasService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        solicitudAperturaNotasService.eliminar(id);
    }

}
