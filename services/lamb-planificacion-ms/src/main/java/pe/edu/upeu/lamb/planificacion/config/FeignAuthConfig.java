package pe.edu.upeu.lamb.planificacion.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.edu.upeu.lamb.planificacion.filter.CorrelationIdFilter;

@Configuration
public class FeignAuthConfig {

    // curriculo tambien es resource server: se reenvia el JWT y el traceId de la peticion original
    @Bean
    public RequestInterceptor propagarTokenYTraza() {
        return template -> {
            if (!(RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes atributos)) {
                return;
            }
            String authorization = atributos.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization != null) {
                template.header(HttpHeaders.AUTHORIZATION, authorization);
            }
            String traceId = atributos.getRequest().getHeader(CorrelationIdFilter.TRACE_ID_HEADER);
            if (traceId != null) {
                template.header(CorrelationIdFilter.TRACE_ID_HEADER, traceId);
            }
        };
    }
}
