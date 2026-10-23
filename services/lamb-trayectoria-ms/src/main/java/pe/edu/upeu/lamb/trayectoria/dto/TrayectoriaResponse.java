package pe.edu.upeu.lamb.trayectoria.dto;

import java.util.List;

/**
 * Trayectoria con su historial: lo que ve un estudiante o un coordinador.
 */
public record TrayectoriaResponse(TrayectoriaDataResponse trayectoria, List<HistorialCursoResponse> historial) {
}
