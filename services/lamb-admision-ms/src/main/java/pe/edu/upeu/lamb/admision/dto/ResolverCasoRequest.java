package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResolverCasoRequest {
    @NotNull private Boolean aprobar;
    @Size(max = 500) private String ajusteOtorgado;
}
