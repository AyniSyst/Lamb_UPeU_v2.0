package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTransaccionPasarelaRequest {

    @NotNull
    private Long pagoId;

    @NotBlank
    @Size(max = 30)
    private String proveedor;

    @NotBlank
    @Size(max = 100)
    private String referenciaExterna;

    @NotBlank
    @Size(max = 30)
    private String estado;

    @Size(max = 2000)
    private String payloadRespuesta;
}
