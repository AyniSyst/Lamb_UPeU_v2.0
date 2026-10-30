package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.CodigoInstitucionalTipo;

@Getter
@Setter
public class CodigoInstitucionalRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private CodigoInstitucionalTipo tipo;

    @NotBlank
    @Size(max = 20)
    private String codigo;
}
