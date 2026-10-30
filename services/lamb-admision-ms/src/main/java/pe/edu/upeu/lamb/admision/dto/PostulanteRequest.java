package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostulanteRequest {
    @NotNull private Long idPersona;
    @NotNull private Long idConvocatoria;
    @NotNull private Long idPrograma;
    @NotNull private Long idModalidad;
    @NotBlank @Size(max = 30) private String codigoPostulante;
    @Size(max = 200) private String colegioProcedencia;
    @Min(1950) @Max(2100) private Integer anioEgresoColegio;
    @DecimalMin("0.0") @DecimalMax("20.0") private BigDecimal promedioColegio;
    @Size(max = 20) private String telefonoContacto;
    @Email @Size(max = 100) private String correoContacto;
    @Size(max = 150) private String nombreApoderado;
    @Size(max = 20) private String telefonoApoderado;
}
