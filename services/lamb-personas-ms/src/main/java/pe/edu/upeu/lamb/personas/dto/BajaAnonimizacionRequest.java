package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.TipoBaja;

@Getter
@Setter
public class BajaAnonimizacionRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private TipoBaja tipo;

    @Size(max = 255)
    private String motivo;
}
