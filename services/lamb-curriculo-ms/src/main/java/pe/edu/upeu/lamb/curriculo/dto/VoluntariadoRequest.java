package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoVoluntariado;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoluntariadoRequest {

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotBlank
    @Size(max = 150)
    private String organizacion;

    @NotNull
    private TipoVoluntariado tipo;

    @NotNull
    @Min(1)
    @Max(500)
    private Integer horasRequeridas;

    @Size(max = 500)
    private String descripcion;

    @NotNull
    private Boolean activo;
}
