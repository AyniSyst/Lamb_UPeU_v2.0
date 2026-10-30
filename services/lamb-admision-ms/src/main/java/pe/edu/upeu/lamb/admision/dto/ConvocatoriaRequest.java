package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvocatoriaRequest {
    @NotBlank @Size(max = 30)
    private String codigo;
    @NotBlank @Size(max = 150)
    private String nombre;
    @NotBlank @Size(max = 20)
    private String periodo;
    @NotNull
    private LocalDate fechaInicio;
    @NotNull
    private LocalDate fechaFin;
    private LocalDate fechaExamen;
    @NotNull @Min(0)
    private Integer vacantesTotales;
    @Size(max = 500)
    private String observacion;
}
