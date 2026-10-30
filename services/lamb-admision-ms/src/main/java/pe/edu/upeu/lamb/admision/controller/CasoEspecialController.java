package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.dto.CasoEspecialRequest;
import pe.edu.upeu.lamb.admision.dto.CasoEspecialResponse;
import pe.edu.upeu.lamb.admision.dto.ResolverCasoRequest;
import pe.edu.upeu.lamb.admision.entity.TipoCaso;
import pe.edu.upeu.lamb.admision.service.CasoEspecialService;

import java.util.List;

@Tag(name = "Casos especiales y excepciones", description = "CRUD 7 - Ficha socioeconomica, discapacidad, prorroga, convalidacion, anulacion, carga masiva")
@RestController
@RequestMapping("/api/v1/casos-especiales")
@RequiredArgsConstructor
public class CasoEspecialController {
    private final CasoEspecialService service;

    @GetMapping
    public List<CasoEspecialResponse> listar(@RequestParam(required = false) Long idPostulante,
                                             @RequestParam(required = false) TipoCaso tipoCaso) {
        return service.listar(idPostulante, tipoCaso);
    }

    @GetMapping("/{id}")
    public CasoEspecialResponse obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CasoEspecialResponse crear(@Valid @RequestBody CasoEspecialRequest r) { return service.crear(r); }

    @PutMapping("/{id}")
    public CasoEspecialResponse actualizar(@PathVariable Long id, @Valid @RequestBody CasoEspecialRequest r) {
        return service.actualizar(id, r);
    }

    @PostMapping("/{id}/resolver")
    public CasoEspecialResponse resolver(@PathVariable Long id, @Valid @RequestBody ResolverCasoRequest r,
                                         @AuthenticationPrincipal Jwt jwt) {
        return service.resolver(id, r, extraerIdPersona(jwt));
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
