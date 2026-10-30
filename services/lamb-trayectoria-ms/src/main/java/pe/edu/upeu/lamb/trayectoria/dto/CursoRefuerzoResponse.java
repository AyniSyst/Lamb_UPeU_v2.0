package pe.edu.upeu.lamb.trayectoria.dto;

public record CursoRefuerzoResponse(
        Long id,
        Long idPlanRefuerzo,
        String codigoCurso,
        String nombreCurso,
        Integer numeroRepitencia,
        String actividadAsignada,
        boolean completado,
        String observacion
) {
}
