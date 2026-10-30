package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.VinculoTipo;

@Getter
@Setter
public class VinculoInstitucionRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private VinculoTipo tipoVinculo;

    @Size(max = 255)
    private String observacion;
}
