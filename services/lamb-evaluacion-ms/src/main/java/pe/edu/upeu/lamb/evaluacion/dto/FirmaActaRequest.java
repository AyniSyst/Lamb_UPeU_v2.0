package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.RolFirma;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FirmaActaRequest {

    @NotNull
    private Long idActa;

    @NotNull
    private Long idPersona;

    @NotNull
    private RolFirma rol;

    @NotNull
    private LocalDate fecha;
}
