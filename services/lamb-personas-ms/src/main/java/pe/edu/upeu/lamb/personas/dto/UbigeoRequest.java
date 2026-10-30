package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UbigeoRequest {

    @NotBlank
    @Size(max = 60)
    private String pais;

    @Size(max = 60)
    private String departamento;

    @Size(max = 60)
    private String provincia;

    @Size(max = 60)
    private String distrito;
}
