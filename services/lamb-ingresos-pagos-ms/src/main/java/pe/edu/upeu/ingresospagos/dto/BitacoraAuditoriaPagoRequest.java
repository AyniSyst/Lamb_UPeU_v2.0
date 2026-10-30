package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.AccionBitacora;

@Getter
@Setter
public class BitacoraAuditoriaPagoRequest {

    @NotNull
    private Long pagoId;

    @NotNull
    private AccionBitacora accion;

    @NotBlank
    @Size(max = 36)
    private String idUsuario;

    @Size(max = 255)
    private String observaciones;
}
