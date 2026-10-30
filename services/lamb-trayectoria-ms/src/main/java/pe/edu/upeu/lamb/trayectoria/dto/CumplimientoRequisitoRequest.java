package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CumplimientoRequisitoRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotBlank @Size(max = 30) String tipoRequisito,
        Long idRequisitoExterno,
        @Size(max = 50) String codigoExterno,
        @Size(max = 500) String descripcion,
        Boolean cumplido,
        @Size(max = 200) String valorCumplimiento,
        @Min(0) Integer creditosReconocidos,
        LocalDate fechaCumplimiento,
        @Size(max = 500) String evidenciaUrl,
        @Size(max = 500) String observacion
) {
}
