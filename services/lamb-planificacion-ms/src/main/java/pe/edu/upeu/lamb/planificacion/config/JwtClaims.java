package pe.edu.upeu.lamb.planificacion.config;

import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import org.springframework.security.oauth2.jwt.Jwt;

// idPersona es un claim propio de Keycloak (atributo del usuario), separado del sub
public final class JwtClaims {

    private JwtClaims() {
    }

    public static Long idPersona(Jwt jwt) {
        Number idPersona = jwt == null ? null : jwt.getClaim("idPersona");
        if (idPersona == null) {
            throw new BusinessRuleException("El token no trae idPersona: el usuario no esta vinculado a una persona");
        }
        return idPersona.longValue();
    }
}
