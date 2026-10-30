package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.entity.ConfiguracionEvaluacion;
import pe.edu.upeu.lamb.evaluacion.entity.EscalaCalificacion;
import pe.edu.upeu.lamb.evaluacion.entity.Nota;
import pe.edu.upeu.lamb.evaluacion.entity.TipoRedondeo;
import pe.edu.upeu.lamb.evaluacion.repository.ConfiguracionEvaluacionRepository;
import pe.edu.upeu.lamb.evaluacion.repository.EscalaCalificacionRepository;
import pe.edu.upeu.lamb.evaluacion.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Nota final del curso: la usan el cierre del acta (acta.cerrada) y las vistas calculadas.
 * Con pesos por componente (CRUD 2) es un promedio ponderado; sin pesos, el promedio simple de siempre.
 * La nota aprobatoria y el redondeo salen de la escala vigente (CRUD 3).
 */
@Service
@RequiredArgsConstructor
public class CalculoNotasService {

    // Escala vigesimal por defecto si aun no se registro ninguna escala vigente
    private static final EscalaCalificacion ESCALA_POR_DEFECTO = EscalaCalificacion.builder()
            .codigo("VIGESIMAL")
            .notaMinima(BigDecimal.ZERO)
            .notaMaxima(new BigDecimal("20"))
            .notaAprobatoria(new BigDecimal("10.5"))
            .decimales(2)
            .redondeo(TipoRedondeo.HALF_UP)
            .vigente(true)
            .build();

    private final NotaRepository notaRepository;
    private final ConfiguracionEvaluacionRepository configuracionEvaluacionRepository;
    private final EscalaCalificacionRepository escalaCalificacionRepository;

    @Transactional(readOnly = true)
    public EscalaCalificacion escalaVigente() {
        return escalaCalificacionRepository.findFirstByVigenteTrue().orElse(ESCALA_POR_DEFECTO);
    }

    public boolean aprobado(BigDecimal notaFinal, EscalaCalificacion escala) {
        return notaFinal.compareTo(escala.getNotaAprobatoria()) >= 0;
    }

    // idPersona -> nota final, ordenado por idPersona
    @Transactional(readOnly = true)
    public Map<Long, BigDecimal> notasFinales(Long idGrupo) {
        EscalaCalificacion escala = escalaVigente();
        Map<Long, BigDecimal> pesos = configuracionEvaluacionRepository.findByIdGrupo(idGrupo).stream()
                .collect(Collectors.toMap(c -> c.getComponente().getId(), ConfiguracionEvaluacion::getPeso));
        Map<Long, List<Nota>> porEstudiante = notaRepository.findByIdGrupo(idGrupo).stream()
                .collect(Collectors.groupingBy(Nota::getIdPersona, TreeMap::new, Collectors.toList()));
        Map<Long, BigDecimal> finales = new TreeMap<>();
        porEstudiante.forEach((idPersona, notas) -> finales.put(idPersona, calcular(notas, pesos, escala)));
        return finales;
    }

    private BigDecimal calcular(List<Nota> notas, Map<Long, BigDecimal> pesos, EscalaCalificacion escala) {
        RoundingMode modo = RoundingMode.valueOf(escala.getRedondeo().name());
        if (pesos.isEmpty()) {
            BigDecimal suma = notas.stream().map(Nota::getCalificacion).reduce(BigDecimal.ZERO, BigDecimal::add);
            return suma.divide(BigDecimal.valueOf(notas.size()), escala.getDecimales(), modo);
        }
        // Varias notas del mismo componente se promedian primero; luego se pondera por el peso del componente
        Map<Long, List<Nota>> porComponente = notas.stream()
                .collect(Collectors.groupingBy(n -> n.getComponente().getId()));
        BigDecimal acumulado = BigDecimal.ZERO;
        BigDecimal pesoTotal = BigDecimal.ZERO;
        for (Map.Entry<Long, BigDecimal> peso : pesos.entrySet()) {
            List<Nota> delComponente = porComponente.getOrDefault(peso.getKey(), List.of());
            BigDecimal promedio = delComponente.isEmpty()
                    ? BigDecimal.ZERO
                    : delComponente.stream().map(Nota::getCalificacion).reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(BigDecimal.valueOf(delComponente.size()), 4, RoundingMode.HALF_UP);
            acumulado = acumulado.add(promedio.multiply(peso.getValue()));
            pesoTotal = pesoTotal.add(peso.getValue());
        }
        return acumulado.divide(pesoTotal, escala.getDecimales(), modo);
    }
}
