package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamenAdmisionRequest {
    @NotNull private Long idConvocatoria;
    @NotBlank @Size(max = 30) private String codigo;
    @NotBlank @Size(max = 150) private String nombre;
    @NotNull private Instant fechaExamen;
    @NotNull @Min(15) @Max(600) private Integer duracionMinutos;
    private Long idAula;
    @NotNull @Min(1) private Integer aforoMaximo;
}
