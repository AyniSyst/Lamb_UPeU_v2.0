package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.dto.SeguimientoRequest;
import pe.edu.upeu.lamb.admision.dto.SeguimientoResponse;
import pe.edu.upeu.lamb.admision.service.SeguimientoService;

import java.util.List;

@Tag(name = "Seguimiento y cierre", description = "CRUD 4 - Contactos y seguimiento al postulante (reclamos, apelaciones, cierre)")
@RestController
@RequestMapping("/api/v1/seguimientos")
@RequiredArgsConstructor
public class SeguimientoController {
    private final SeguimientoService service;

    @GetMapping
    public List<SeguimientoResponse> listar(@RequestParam(required = false) Long idPostulante) {
        return service.listar(idPostulante);
    }

    @GetMapping("/{id}")
    public SeguimientoResponse obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeguimientoResponse crear(@Valid @RequestBody SeguimientoRequest r, @AuthenticationPrincipal Jwt jwt) {
        return service.crear(r, extraerIdPersona(jwt));
    }

    @PutMapping("/{id}")
    public SeguimientoResponse actualizar(@PathVariable Long id, @Valid @RequestBody SeguimientoRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }

    private Long extraerIdPersona(Jwt jwt) {
        if (jwt == null) return null;
        Object claim = jwt.getClaim("idPersona");
        if (claim instanceof Number n) return n.longValue();
        if (claim instanceof String s) {
            try { return Long.parseLong(s); } catch (NumberFormatException ignored) { return null; }
        }
        return null;
    }
}
