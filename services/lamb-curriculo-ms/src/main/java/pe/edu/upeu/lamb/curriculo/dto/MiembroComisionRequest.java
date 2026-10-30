package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.RolComision;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MiembroComisionRequest {

    @NotNull
    private Long idComision;

    @NotNull
    private Long idPersona;

    @NotNull
    private RolComision rol;

    @NotNull
    private LocalDate fechaIncorporacion;
}
