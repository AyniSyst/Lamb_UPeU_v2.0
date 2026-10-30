package pe.edu.upeu.lamb.trayectoria.dto;

import java.time.LocalDate;

public record CumplimientoRequisitoResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        String tipoRequisito,
        Long idRequisitoExterno,
        String codigoExterno,
        String descripcion,
        boolean cumplido,
        String valorCumplimiento,
        Integer creditosReconocidos,
        LocalDate fechaCumplimiento,
        String evidenciaUrl,
        String observacion
) {
}
