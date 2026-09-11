package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeccionRequest {

    @NotNull
    private Long cursoId;

    @NotBlank
    @Size(max = 20)
    private String periodo;

    @Size(max = 150)
    private String docente;

    @NotNull
    @Min(1)
    private Integer cupoMaximo;

    @Size(max = 100)
    private String horario;
}
