package pe.edu.upeu.lamb.trayectoria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.trayectoria.dto.*;
import pe.edu.upeu.lamb.trayectoria.entity.CambioAcademico;
import pe.edu.upeu.lamb.trayectoria.entity.HistorialCurso;
import pe.edu.upeu.lamb.trayectoria.entity.Trayectoria;
import pe.edu.upeu.lamb.trayectoria.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.trayectoria.mapper.TrayectoriaMapper;
import pe.edu.upeu.lamb.trayectoria.repository.CambioAcademicoRepository;
import pe.edu.upeu.lamb.trayectoria.repository.HistorialCursoRepository;
import pe.edu.upeu.lamb.trayectoria.repository.TrayectoriaRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VistaTrayectoriaService {

    private final TrayectoriaRepository trayectoriaRepository;
    private final HistorialCursoRepository historialRepository;
    private final CambioAcademicoRepository cambioRepository;
    private final TrayectoriaMapper trayectoriaMapper;

    @Transactional(readOnly = true)
    public AvanceCurricularResponse avanceCurricular(Long idPersona) {
        Trayectoria t = buscarTrayectoria(idPersona);
        List<HistorialCurso> historial = historialRepository.findByIdPersonaOrderByPeriodoAscCodigoCursoAsc(idPersona);
        int aprobados = (int) historial.stream().filter(HistorialCurso::isAprobado).count();
        int desaprobados = historial.size() - aprobados;
        int creditosAprobados = t.getCreditosAprobados() == null
                ? historial.stream().filter(HistorialCurso::isAprobado).mapToInt(HistorialCurso::getCreditos).sum()
                : t.getCreditosAprobados();
        Integer totales = t.getCreditosTotalesPlan();
        if (totales == null || totales == 0) {
            totales = historial.stream().mapToInt(HistorialCurso::getCreditos).sum();
        }
        BigDecimal porcentaje = totales == 0
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(creditosAprobados)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(totales), 2, RoundingMode.HALF_UP);
        return new AvanceCurricularResponse(t.getId(), idPersona, totales, creditosAprobados,
                historial.size(), aprobados, desaprobados, porcentaje, t.getEstado());
    }

    @Transactional(readOnly = true)
    public List<HistorialCursoResponse> historialAcademico(Long idPersona) {
        buscarTrayectoria(idPersona);
        return historialRepository.findByIdPersonaOrderByPeriodoAscCodigoCursoAsc(idPersona)
                .stream().map(trayectoriaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PromedioPonderadoResponse promedioPonderado(Long idPersona) {
        buscarTrayectoria(idPersona);
        List<HistorialCurso> historial = historialRepository.findByIdPersonaOrderByPeriodoAscCodigoCursoAsc(idPersona);
        int creditos = historial.stream().mapToInt(HistorialCurso::getCreditos).sum();
        BigDecimal suma = historial.stream()
                .map(h -> h.getNotaFinal().multiply(BigDecimal.valueOf(h.getCreditos())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal promedio = creditos == 0 ? null : suma.divide(BigDecimal.valueOf(creditos), 2, RoundingMode.HALF_UP);
        return new PromedioPonderadoResponse(idPersona, promedio, creditos, historial.size());
    }

    @Transactional(readOnly = true)
    public List<RendimientoCursoResponse> rendimientoPorCurso(Long idPersona) {
        buscarTrayectoria(idPersona);
        List<HistorialCurso> historial = historialRepository.findByIdPersonaOrderByPeriodoAscCodigoCursoAsc(idPersona);
        Map<String, List<HistorialCurso>> porCurso = new LinkedHashMap<>();
        for (HistorialCurso h : historial) {
            porCurso.computeIfAbsent(h.getCodigoCurso(), k -> new java.util.ArrayList<>()).add(h);
        }
        return porCurso.entrySet().stream()
                .map(e -> {
                    List<HistorialCurso> intentos = e.getValue();
                    HistorialCurso ultimo = intentos.stream()
                            .max(Comparator.comparing(HistorialCurso::getFechaRegistro))
                            .orElse(intentos.get(intentos.size() - 1));
                    BigDecimal mejor = intentos.stream()
                            .map(HistorialCurso::getNotaFinal)
                            .max(Comparator.naturalOrder())
                            .orElse(null);
                    boolean aprobado = intentos.stream().anyMatch(HistorialCurso::isAprobado);
                    return new RendimientoCursoResponse(e.getKey(), intentos.size(), mejor,
                            ultimo.getNotaFinal(), aprobado, ultimo.getCreditos());
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public List<HistorialEstadoResponse> historialEstados(Long idPersona) {
        buscarTrayectoria(idPersona);
        return cambioRepository.findByIdPersonaOrderByFechaCambioDesc(idPersona).stream()
                .map(this::toHistorialEstado)
                .toList();
    }

    private HistorialEstadoResponse toHistorialEstado(CambioAcademico c) {
        return new HistorialEstadoResponse(c.getId(), c.getTipoCambio(), c.getEstadoAnterior(),
                c.getEstadoNuevo(), c.getMotivo(), c.getNumeroResolucion(), c.getFechaCambio());
    }

    private Trayectoria buscarTrayectoria(Long idPersona) {
        return trayectoriaRepository.findByIdPersona(idPersona)
                .orElseThrow(() -> new ResourceNotFoundException("La persona " + idPersona + " no tiene trayectoria academica"));
    }
}
