package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.ActaNotasResponse;
import pe.edu.upeu.lamb.evaluacion.dto.EstadisticaGrupoResponse;
import pe.edu.upeu.lamb.evaluacion.dto.HistorialActaResponse;
import pe.edu.upeu.lamb.evaluacion.dto.MeritoResponse;
import pe.edu.upeu.lamb.evaluacion.dto.NotaFinalResponse;
import pe.edu.upeu.lamb.evaluacion.dto.ResultadoPreguntaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.Acta;
import pe.edu.upeu.lamb.evaluacion.entity.EscalaCalificacion;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoActa;
import pe.edu.upeu.lamb.evaluacion.entity.Nota;
import pe.edu.upeu.lamb.evaluacion.entity.PreguntaEncuesta;
import pe.edu.upeu.lamb.evaluacion.entity.RespuestaEncuesta;
import pe.edu.upeu.lamb.evaluacion.entity.TipoPregunta;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.repository.NotaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.PreguntaEncuestaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.RespuestaEncuestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vistas calculadas de evaluacion: no tienen tabla ni CRUD, se arman con las notas, el acta,
 * los pesos y la escala vigente. Asi nunca quedan desactualizadas frente a una nota corregida.
 */
@Service
@RequiredArgsConstructor
public class VistaService {

    private final ActaService actaService;
    private final CalculoNotasService calculoNotasService;
    private final NotaRepository notaRepository;
    private final PreguntaEncuestaRepository preguntaEncuestaRepository;
    private final RespuestaEncuestaRepository respuestaEncuestaRepository;

    @Transactional(readOnly = true)
    public ActaNotasResponse actaNotas(Long idActa) {
        Acta acta = actaService.obtener(idActa);
        return ActaNotasResponse.builder()
                .idActa(acta.getId())
                .idGrupo(acta.getIdGrupo())
                .codigoCurso(acta.getCodigoCurso())
                .periodo(acta.getPeriodo())
                .idDocente(acta.getIdDocente())
                .estado(acta.getEstado())
                .escala(calculoNotasService.escalaVigente().getCodigo())
                .fechaCierre(acta.getFechaCierre())
                .notas(notasFinales(acta.getIdGrupo()))
                .build();
    }

    // El acta guarda la fecha de cada transicion (registro y cierre): con eso se reconstruye su historial
    @Transactional(readOnly = true)
    public List<HistorialActaResponse> historialActa(Long idActa) {
        Acta acta = actaService.obtener(idActa);
        List<HistorialActaResponse> historial = new ArrayList<>();
        if (acta.getEstado() == EstadoActa.RECHAZADA) {
            historial.add(new HistorialActaResponse(EstadoActa.RECHAZADA, acta.getFechaRegistro(), acta.getMotivo()));
            return historial;
        }
        historial.add(new HistorialActaResponse(EstadoActa.ABIERTA, acta.getFechaRegistro(), "acta abierta por el evento grupo.abierto"));
        if (acta.getEstado() == EstadoActa.CERRADA) {
            historial.add(new HistorialActaResponse(EstadoActa.CERRADA, acta.getFechaCierre(), "notas finales publicadas (acta.cerrada)"));
        }
        return historial;
    }

    @Transactional(readOnly = true)
    public NotaFinalResponse notaFinal(Long idGrupo, Long idPersona) {
        return notasFinales(idGrupo).stream()
                .filter(n -> n.getIdPersona().equals(idPersona))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("El estudiante " + idPersona + " no tiene notas en el grupo " + idGrupo));
    }

    // Promedio del grupo + estadistica de aprobados y desaprobados
    @Transactional(readOnly = true)
    public EstadisticaGrupoResponse estadisticaGrupo(Long idGrupo) {
        List<NotaFinalResponse> notas = notasFinales(idGrupo);
        if (notas.isEmpty()) {
            throw new ResourceNotFoundException("El grupo " + idGrupo + " no tiene notas registradas");
        }
        BigDecimal suma = notas.stream().map(NotaFinalResponse::getNotaFinal).reduce(BigDecimal.ZERO, BigDecimal::add);
        int aprobados = (int) notas.stream().filter(NotaFinalResponse::isAprobado).count();
        return EstadisticaGrupoResponse.builder()
                .idGrupo(idGrupo)
                .estudiantes(notas.size())
                .promedio(suma.divide(BigDecimal.valueOf(notas.size()), 2, RoundingMode.HALF_UP))
                .aprobados(aprobados)
                .desaprobados(notas.size() - aprobados)
                .porcentajeAprobados(BigDecimal.valueOf(aprobados * 100L)
                        .divide(BigDecimal.valueOf(notas.size()), 2, RoundingMode.HALF_UP))
                .build();
    }

    /**
     * Cuadro de meritos del periodo: promedio de las notas finales de cada estudiante en todos sus grupos.
     * Tercio superior = el primer tercio de la lista (redondeado hacia arriba).
     */
    @Transactional(readOnly = true)
    public List<MeritoResponse> cuadroMeritos(String periodo) {
        Map<Long, List<BigDecimal>> porEstudiante = new HashMap<>();
        notaRepository.findByPeriodo(periodo).stream()
                .map(Nota::getIdGrupo)
                .distinct()
                .forEach(idGrupo -> calculoNotasService.notasFinales(idGrupo)
                        .forEach((idPersona, nota) -> porEstudiante.computeIfAbsent(idPersona, k -> new ArrayList<>()).add(nota)));

        List<MeritoResponse> filas = new ArrayList<>();
        porEstudiante.forEach((idPersona, notas) -> filas.add(MeritoResponse.builder()
                .idPersona(idPersona)
                .cursos(notas.size())
                .promedio(notas.stream().reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(notas.size()), 2, RoundingMode.HALF_UP))
                .build()));
        filas.sort(Comparator.comparing(MeritoResponse::getPromedio).reversed().thenComparing(MeritoResponse::getIdPersona));
        int tercio = (int) Math.ceil(filas.size() / 3.0);
        for (int i = 0; i < filas.size(); i++) {
            filas.get(i).setPosicion(i + 1);
            filas.get(i).setTercioSuperior(i < tercio);
        }
        return filas;
    }

    @Transactional(readOnly = true)
    public List<ResultadoPreguntaResponse> resultadoEvaluacionDocente(Long idEncuesta, Long idDocente) {
        List<ResultadoPreguntaResponse> resultado = new ArrayList<>();
        for (PreguntaEncuesta pregunta : preguntaEncuestaRepository.findByEncuestaId(idEncuesta)) {
            List<RespuestaEncuesta> respuestas = respuestaEncuestaRepository.findByPreguntaIdAndIdDocente(pregunta.getId(), idDocente);
            resultado.add(ResultadoPreguntaResponse.builder()
                    .idPregunta(pregunta.getId())
                    .orden(pregunta.getOrden())
                    .enunciado(pregunta.getEnunciado())
                    .tipo(pregunta.getTipo())
                    .respuestas(respuestas.size())
                    .resultado(resumir(pregunta.getTipo(), respuestas))
                    .build());
        }
        resultado.sort(Comparator.comparing(ResultadoPreguntaResponse::getOrden));
        return resultado;
    }

    private BigDecimal resumir(TipoPregunta tipo, List<RespuestaEncuesta> respuestas) {
        if (tipo == TipoPregunta.ABIERTA || respuestas.isEmpty()) {
            return null;
        }
        long suma = respuestas.stream().mapToLong(RespuestaEncuesta::getValorEscala).sum();
        BigDecimal promedio = BigDecimal.valueOf(suma).divide(BigDecimal.valueOf(respuestas.size()), 2, RoundingMode.HALF_UP);
        // En SI_NO el valor es 0 o 1: el promedio por 100 es el porcentaje de "si"
        return tipo == TipoPregunta.SI_NO ? promedio.multiply(BigDecimal.valueOf(100)) : promedio;
    }

    private List<NotaFinalResponse> notasFinales(Long idGrupo) {
        EscalaCalificacion escala = calculoNotasService.escalaVigente();
        return calculoNotasService.notasFinales(idGrupo).entrySet().stream()
                .map(e -> new NotaFinalResponse(idGrupo, e.getKey(), e.getValue(), calculoNotasService.aprobado(e.getValue(), escala)))
                .toList();
    }
}
