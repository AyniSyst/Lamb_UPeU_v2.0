package pe.edu.upeu.lamb.finanzas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.lamb.finanzas.entity.MetodoPago;

/** Metodo con el que se cancela un cargo PENDIENTE (por ejemplo, el de una matricula). */
@Getter
@Setter
public class PagarCargoRequest {

    @NotNull
    private MetodoPago metodo;
}
