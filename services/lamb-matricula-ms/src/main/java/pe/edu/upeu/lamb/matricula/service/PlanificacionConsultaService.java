package pe.edu.upeu.lamb.matricula.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import pe.edu.upeu.lamb.matricula.client.PlanificacionClient;
import pe.edu.upeu.lamb.matricula.dto.SeccionDisponibleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Bean aparte a proposito: si obtenerSeccion viviera en MatriculaService, la llamada seria
 * auto-invocacion y el proxy de @CircuitBreaker nunca se activaria.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlanificacionConsultaService {

    private final PlanificacionClient planificacionClient;

    @CircuitBreaker(name = "planificacion", fallbackMethod = "fallbackSeccion")
    public SeccionDisponibleDto obtenerSeccion(Long idSeccion) {
        log.info("[PLANIFICACION] Consultando seccion {} por Feign", idSeccion);
        return planificacionClient.obtenerSeccion(idSeccion);
    }

    // null = no se pudo confirmar (planificacion caida); la matricula se registra igual,
    // la seccion se valida despues en vez de bloquear la inscripcion por un fallo ajeno.
    public SeccionDisponibleDto fallbackSeccion(Long idSeccion, Throwable ex) {
        log.warn("[PLANIFICACION] Fallback activado para seccion {}. Motivo: {}", idSeccion, ex.getMessage());
        return null;
    }
}
