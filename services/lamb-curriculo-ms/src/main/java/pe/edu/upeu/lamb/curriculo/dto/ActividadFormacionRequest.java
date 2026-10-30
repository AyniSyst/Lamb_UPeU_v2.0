package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoActividadFormacion;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActividadFormacionRequest {

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotNull
    private TipoActividadFormacion tipo;

    @NotNull
    @Min(0)
    @Max(4)
    private Integer creditos;

    @NotNull
    @Min(1)
    @Max(200)
    private Integer horas;

    @Size(max = 500)
    private String descripcion;
}
