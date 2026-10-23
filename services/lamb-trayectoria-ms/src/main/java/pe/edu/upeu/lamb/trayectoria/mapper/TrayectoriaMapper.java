package pe.edu.upeu.lamb.trayectoria.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.trayectoria.dto.HistorialCursoResponse;
import pe.edu.upeu.lamb.trayectoria.dto.TrayectoriaDataResponse;
import pe.edu.upeu.lamb.trayectoria.dto.TrayectoriaResponse;
import pe.edu.upeu.lamb.trayectoria.entity.HistorialCurso;
import pe.edu.upeu.lamb.trayectoria.entity.Trayectoria;

import java.util.List;

@Component
public class TrayectoriaMapper {

    public TrayectoriaDataResponse toResponse(Trayectoria trayectoria) {
        return TrayectoriaDataResponse.builder()
                .id(trayectoria.getId())
                .idPersona(trayectoria.getIdPersona())
                .idPrograma(trayectoria.getIdPrograma())
                .idPostulacion(trayectoria.getIdPostulacion())
                .periodoIngreso(trayectoria.getPeriodoIngreso())
                .estado(trayectoria.getEstado())
                .creditosAprobados(trayectoria.getCreditosAprobados())
                .promedioPonderado(trayectoria.getPromedioPonderado())
                .fechaRegistro(trayectoria.getFechaRegistro())
                .build();
    }

    public HistorialCursoResponse toResponse(HistorialCurso historial) {
        return HistorialCursoResponse.builder()
                .id(historial.getId())
                .idPersona(historial.getIdPersona())
                .idSeccion(historial.getIdSeccion())
                .idActa(historial.getIdActa())
                .codigoCurso(historial.getCodigoCurso())
                .periodo(historial.getPeriodo())
                .creditos(historial.getCreditos())
                .notaFinal(historial.getNotaFinal())
                .aprobado(historial.isAprobado())
                .fechaRegistro(historial.getFechaRegistro())
                .build();
    }

    public TrayectoriaResponse toResponse(Trayectoria trayectoria, List<HistorialCurso> historial) {
        return new TrayectoriaResponse(
                toResponse(trayectoria),
                historial.stream().map(this::toResponse).toList()
        );
    }
}
