package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaSolicitudAperturaRequest {

    @NotNull
    private Long idSolicitud;

    @NotNull
    private Boolean aprobada;

    @Size(max = 255)
    private String observacion;
}
