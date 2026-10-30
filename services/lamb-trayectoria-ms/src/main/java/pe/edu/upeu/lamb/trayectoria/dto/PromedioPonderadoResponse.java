package pe.edu.upeu.lamb.trayectoria.dto;

import java.math.BigDecimal;

public record PromedioPonderadoResponse(
        Long idPersona,
        BigDecimal promedioPonderado,
        Integer creditosConsiderados,
        Integer cursosConsiderados
) {
}
