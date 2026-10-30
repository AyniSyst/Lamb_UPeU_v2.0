package pe.edu.upeu.lamb.trayectoria.dto;

import java.math.BigDecimal;

public record RendimientoCursoResponse(
        String codigoCurso,
        Integer intentos,
        BigDecimal mejorNota,
        BigDecimal ultimaNota,
        boolean aprobado,
        Integer creditos
) {
}
