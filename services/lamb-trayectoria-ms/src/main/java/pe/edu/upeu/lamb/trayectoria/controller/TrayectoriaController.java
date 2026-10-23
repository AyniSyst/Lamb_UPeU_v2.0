package pe.edu.upeu.lamb.trayectoria.controller;

import pe.edu.upeu.lamb.trayectoria.dto.TrayectoriaDataResponse;
import pe.edu.upeu.lamb.trayectoria.dto.TrayectoriaResponse;
import pe.edu.upeu.lamb.trayectoria.exception.BusinessRuleException;
import pe.edu.upeu.lamb.trayectoria.service.TrayectoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// La trayectoria no se escribe por HTTP: se deriva de postulante.admitido y acta.cerrada
@RestController
@RequestMapping("/api/v1/trayectorias")
@RequiredArgsConstructor
public class TrayectoriaController {

    private final TrayectoriaService trayectoriaService;

    @GetMapping
    public List<TrayectoriaDataResponse> listar() {
        return trayectoriaService.listar();
    }

    @GetMapping("/persona/{idPersona}")
    public TrayectoriaResponse obtenerPorPersona(@PathVariable Long idPersona) {
        return trayectoriaService.obtenerPorPersona(idPersona);
    }

    // El estudiante ve solo la suya: el idPersona sale del JWT, no de la URL
    @GetMapping("/me")
    public TrayectoriaResponse miTrayectoria(@AuthenticationPrincipal Jwt jwt) {
        Number idPersona = jwt.getClaim("idPersona");
        if (idPersona == null) {
            throw new BusinessRuleException("El token no trae idPersona: el usuario no esta vinculado a una persona");
        }
        return trayectoriaService.obtenerPorPersona(idPersona.longValue());
    }
}
