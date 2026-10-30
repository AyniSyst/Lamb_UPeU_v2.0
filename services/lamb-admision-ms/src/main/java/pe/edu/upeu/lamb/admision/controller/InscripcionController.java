package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.dto.InscripcionRequest;
import pe.edu.upeu.lamb.admision.dto.InscripcionResponse;
import pe.edu.upeu.lamb.admision.dto.ResponderInscripcionRequest;
import pe.edu.upeu.lamb.admision.service.InscripcionService;

import java.util.List;

@Tag(name = "Inscripciones (Transaccional)", description = "CRUD 10 (admision) - Cabecera Inscripcion + detalle DocumentoPresentado con regla de negocio real")
@RestController
@RequestMapping("/api/v1/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    public List<InscripcionResponse> listar(@RequestParam(required = false) Long idPersona,
                                            @RequestParam(required = false) String periodo) {
        return inscripcionService.listar(idPersona, periodo);
    }

    @GetMapping("/{id}")
    public InscripcionResponse obtener(@PathVariable Long id) {
        return inscripcionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InscripcionResponse crear(@Valid @RequestBody InscripcionRequest request) {
        return inscripcionService.crear(request);
    }

    @PostMapping("/{id}/responder")
    public InscripcionResponse responder(@PathVariable Long id,
                                         @Valid @RequestBody ResponderInscripcionRequest request,
                                         @AuthenticationPrincipal Jwt jwt) {
        Long idRespondidoPor = extraerIdPersona(jwt);
        return inscripcionService.responder(id, request, idRespondidoPor);
    }

    @PostMapping("/{id}/anular")
    public InscripcionResponse anular(@PathVariable Long id,
                                      @RequestParam(required = false) String motivo) {
        return inscripcionService.anular(id, motivo);
    }

    private Long extraerIdPersona(Jwt jwt) {
        if (jwt == null) {
            return null;
        }
        Object claim = jwt.getClaim("idPersona");
        if (claim instanceof Number n) {
            return n.longValue();
        }
        if (claim instanceof String s) {
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
        return null;
    }
}
