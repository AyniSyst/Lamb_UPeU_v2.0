package pe.edu.upeu.lamb.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
public class SecurityConfig {

    // Rutas de gestion academica (coordinacion/admision/curriculo/evaluacion/trayectoria)
    private static final String[] ACADEMICO = {
            "/api/v1/planes-estudio/**", "/api/v1/cursos/**", "/api/v1/secciones/**", "/api/v1/postulaciones/**"
    };

    // Rutas institucionales y de personas: lectura abierta a cualquier autenticado, escritura restringida
    private static final String[] INSTITUCIONAL_PERSONAS = {
            "/api/v1/facultades/**", "/api/v1/programas/**", "/api/v1/persona/**"
    };

    // realm_access.roles (formato Keycloak) -> ROLE_<rol> para hasRole(...)
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
            Object roles = realmAccess == null ? null : realmAccess.get("roles");
            if (!(roles instanceof Collection<?> lista)) {
                return List.<GrantedAuthority>of();
            }
            return lista.stream()
                    .map(rol -> (GrantedAuthority) new SimpleGrantedAuthority("ROLE_" + rol))
                    .toList();
        });
        return converter;
    }

    // CORS centralizado aqui: los microservicios internos no necesitan conocer el origen del frontend.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Trace-ID"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // Primera linea: reglas por ruta, metodo y rol. Spring aplica la primera regla que coincide.
    // Segunda linea (JwtAuthenticationConverter + oauth2ResourceServer): la valida cada microservicio por su cuenta.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationConverter jwtAuthenticationConverter,
                                                   CorsConfigurationSource corsConfigurationSource) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**", "/error").permitAll()
                        .requestMatchers(HttpMethod.GET, ACADEMICO).authenticated()
                        .requestMatchers(ACADEMICO).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers("/api/v1/actas/**").hasAnyRole("ADMIN", "COORDINADOR", "DOCENTE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/trayectorias/me").authenticated()
                        .requestMatchers("/api/v1/trayectorias/**").hasAnyRole("ADMIN", "COORDINADOR", "DOCENTE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/notas/**").authenticated()
                        .requestMatchers("/api/v1/notas/**").hasAnyRole("ADMIN", "DOCENTE")
                        .requestMatchers(HttpMethod.GET, INSTITUCIONAL_PERSONAS).authenticated()
                        .requestMatchers(INSTITUCIONAL_PERSONAS).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(HttpMethod.GET, "/api/v1/matriculas/**").authenticated()
                        .requestMatchers("/api/v1/matriculas/**").hasAnyRole("ADMIN", "COORDINADOR", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/pagos/**").authenticated()
                        .requestMatchers("/api/v1/pagos/**").hasAnyRole("ADMIN", "COORDINADOR")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
        return http.build();
    }
}
