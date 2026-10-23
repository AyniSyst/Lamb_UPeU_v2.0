package pe.edu.upeu.lamb.trayectoria.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record HistorialCursoResponse(
        Long id,
        Long idPersona,
        Long idSeccion,
        Long idActa,
        String codigoCurso,
        String periodo,
        Integer creditos,
        BigDecimal notaFinal,
        boolean aprobado,
        LocalDateTime fechaRegistro
) {
}
