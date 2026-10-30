package pe.edu.upeu.lamb.trayectoria.dto;

import java.time.LocalDate;
import java.util.List;

public record TutoriaResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        Long idTutor,
        Long idProgramaTutoria,
        String programaTutoria,
        String periodo,
        String estado,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        List<SesionTutoriaResponse> sesiones
) {
}
