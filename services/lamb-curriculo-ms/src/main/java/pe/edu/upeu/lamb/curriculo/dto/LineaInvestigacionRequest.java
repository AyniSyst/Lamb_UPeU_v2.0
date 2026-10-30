package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineaInvestigacionRequest {

    @NotNull
    private Long idPrograma;

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotBlank
    @Size(max = 200)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    @NotNull
    private Boolean activa;
}
