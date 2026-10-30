package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GrupoTallerRequest {

    @NotNull
    private Long idPeriodo;

    @NotNull
    private Long idCurso;

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotNull
    @Min(1)
    private Integer cupoMaximo;

    @NotEmpty
    private List<@NotNull Long> idProgramas;
}
