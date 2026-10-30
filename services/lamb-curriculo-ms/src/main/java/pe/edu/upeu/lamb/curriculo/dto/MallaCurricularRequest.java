package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MallaCurricularRequest {

    @NotNull
    private Long idPlan;

    @NotBlank
    @Size(max = 200)
    private String nombreArchivo;

    @NotBlank
    @Size(max = 500)
    private String url;

    @NotNull
    @Min(1)
    private Integer version;

    @NotNull
    private LocalDate fechaCarga;
}
