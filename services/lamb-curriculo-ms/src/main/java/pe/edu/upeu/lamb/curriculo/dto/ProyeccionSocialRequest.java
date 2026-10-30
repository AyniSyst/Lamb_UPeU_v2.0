package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.EstadoProyecto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProyeccionSocialRequest {

    @NotNull
    private Long idPrograma;

    @NotBlank
    @Size(max = 200)
    private String nombre;

    @Size(max = 1000)
    private String descripcion;

    @NotNull
    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @NotNull
    @Min(1)
    @Max(1000)
    private Integer horas;

    @NotNull
    private EstadoProyecto estado;
}
