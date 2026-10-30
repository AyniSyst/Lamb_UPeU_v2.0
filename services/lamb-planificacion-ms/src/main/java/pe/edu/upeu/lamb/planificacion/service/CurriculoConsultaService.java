package pe.edu.upeu.lamb.planificacion.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import pe.edu.upeu.lamb.planificacion.client.CurriculoClient;
import pe.edu.upeu.lamb.planificacion.dto.VigenciaCursoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Bean aparte a proposito: si consultarVigencia viviera en GrupoService, la llamada seria
 * auto-invocacion y el proxy de @CircuitBreaker nunca se activaria.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CurriculoConsultaService {

    private final CurriculoClient curriculoClient;

    @CircuitBreaker(name = "curriculo", fallbackMethod = "fallbackVigencia")
    public VigenciaCursoDto consultarVigencia(String codigoCurso) {
        log.info("[CURRICULO] Consultando vigencia del curso {} por Feign", codigoCurso);
        return curriculoClient.consultarVigencia(codigoCurso);
    }

    // null = no se pudo confirmar; el grupo queda PENDIENTE_VALIDACION en vez de un 500
    public VigenciaCursoDto fallbackVigencia(String codigoCurso, Throwable ex) {
        log.warn("[CURRICULO] Fallback activado para curso {}. Motivo: {}", codigoCurso, ex.getMessage());
        return null;
    }
}
