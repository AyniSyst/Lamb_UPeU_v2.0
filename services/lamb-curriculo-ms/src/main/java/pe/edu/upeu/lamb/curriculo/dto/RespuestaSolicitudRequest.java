package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// Respuesta comun a la solicitud de modificacion curricular: aprobar o rechazar con una observacion
@Getter
@Setter
public class RespuestaSolicitudRequest {

    @NotNull
    private Boolean aprobada;

    @Size(max = 255)
    private String observacion;
}
