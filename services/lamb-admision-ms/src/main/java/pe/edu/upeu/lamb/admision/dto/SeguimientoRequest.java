package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import pe.edu.upeu.lamb.admision.entity.CanalSeguimiento;
import pe.edu.upeu.lamb.admision.entity.ResultadoSeguimiento;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoRequest {
    @NotNull private Long idPostulante;
    @NotNull private CanalSeguimiento canal;
    @Size(max = 50) private String medioCaptacion;
    @NotBlank @Size(max = 200) private String asunto;
    @Size(max = 1000) private String detalle;
    @NotNull private ResultadoSeguimiento resultado;
    private Instant proximoContacto;
}
