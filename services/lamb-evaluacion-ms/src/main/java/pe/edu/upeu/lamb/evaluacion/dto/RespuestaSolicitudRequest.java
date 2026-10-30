package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// Respuesta comun a las solicitudes de evaluacion: aprobar o rechazar con una observacion
@Getter
@Setter
public class RespuestaSolicitudRequest {

    @NotNull
    private Boolean aprobada;

    @Size(max = 255)
    private String observacion;
}
