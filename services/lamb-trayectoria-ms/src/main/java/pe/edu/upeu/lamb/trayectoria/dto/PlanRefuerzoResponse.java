package pe.edu.upeu.lamb.trayectoria.dto;

import java.time.LocalDate;
import java.util.List;

public record PlanRefuerzoResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        String periodo,
        String objetivo,
        String estado,
        Long idResponsable,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String observacion,
        List<CursoRefuerzoResponse> cursos
) {
}
