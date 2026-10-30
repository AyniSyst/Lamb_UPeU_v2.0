package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeccionCursoRequest {

    @NotNull
    private Long cursoId;

    @NotNull
    private Long docenteId;

    @NotNull
    private Long periodoAcademicoId;

    @NotBlank
    @Size(max = 10)
    private String nombreSeccion;

    @NotNull
    @Min(1)
    private Integer cupoMaximo;

    @NotNull
    private Boolean activo;
}
