package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponderInscripcionRequest {

    @NotNull
    private Boolean aprobada;

    @Size(max = 500)
    private String observacion;
}
