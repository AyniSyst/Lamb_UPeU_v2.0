package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import pe.edu.upeu.lamb.admision.entity.TipoCaso;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasoEspecialRequest {
    @NotNull private Long idPostulante;
    @NotNull private TipoCaso tipoCaso;
    @NotBlank @Size(max = 1000) private String descripcion;
    @NotNull private Boolean requiereAjuste;
    @Size(max = 500) private String ajusteOtorgado;
    @Size(max = 20) private String nivelSocioeconomico;
}
