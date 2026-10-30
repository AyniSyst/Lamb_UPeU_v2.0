package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BitacoraCambiosEstructuraRequest {

    @NotBlank
    private String entidadAfectada;

    private Long idEntidadAfectada;

    @NotBlank
    private String accion;

    private String detalle;

    private Instant fechaCambio;
}
