package pe.edu.upeu.lamb.trayectoria.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.trayectoria.dto.*;
import pe.edu.upeu.lamb.trayectoria.exception.BusinessRuleException;
import pe.edu.upeu.lamb.trayectoria.service.VistaTrayectoriaService;

import java.util.List;

@Tag(name = "Vistas", description = "Vistas calculadas de trayectoria (solo lectura)")
@RestController
@RequestMapping("/api/v1/vistas")
@RequiredArgsConstructor
public class VistaTrayectoriaController {

    private final VistaTrayectoriaService service;

    @GetMapping("/avance-curricular")
    public AvanceCurricularResponse avanceCurricular(@RequestParam(required = false) Long idPersona,
                                                     @AuthenticationPrincipal Jwt jwt) {
        return service.avanceCurricular(resolverIdPersona(idPersona, jwt));
    }

    @GetMapping("/historial-academico")
    public List<HistorialCursoResponse> historialAcademico(@RequestParam(required = false) Long idPersona,
                                                           @AuthenticationPrincipal Jwt jwt) {
        return service.historialAcademico(resolverIdPersona(idPersona, jwt));
    }

    @GetMapping("/promedio-ponderado")
    public PromedioPonderadoResponse promedioPonderado(@RequestParam(required = false) Long idPersona,
                                                       @AuthenticationPrincipal Jwt jwt) {
        return service.promedioPonderado(resolverIdPersona(idPersona, jwt));
    }

    @GetMapping("/rendimiento-por-curso")
    public List<RendimientoCursoResponse> rendimientoPorCurso(@RequestParam(required = false) Long idPersona,
                                                              @AuthenticationPrincipal Jwt jwt) {
        return service.rendimientoPorCurso(resolverIdPersona(idPersona, jwt));
    }

    @GetMapping("/historial-estados")
    public List<HistorialEstadoResponse> historialEstados(@RequestParam(required = false) Long idPersona,
                                                          @AuthenticationPrincipal Jwt jwt) {
        return service.historialEstados(resolverIdPersona(idPersona, jwt));
    }

    private Long resolverIdPersona(Long idPersona, Jwt jwt) {
        if (idPersona != null) {
            return idPersona;
        }
        if (jwt == null) {
            throw new BusinessRuleException("Debe indicar idPersona o enviar un token con el claim idPersona");
        }
        Object claim = jwt.getClaim("idPersona");
        if (claim instanceof Number n) {
            return n.longValue();
        }
        if (claim instanceof String s) {
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException ignored) {
                throw new BusinessRuleException("El claim idPersona del token no es numerico");
            }
        }
        throw new BusinessRuleException("El token no trae idPersona: el usuario no esta vinculado a una persona");
    }
}
