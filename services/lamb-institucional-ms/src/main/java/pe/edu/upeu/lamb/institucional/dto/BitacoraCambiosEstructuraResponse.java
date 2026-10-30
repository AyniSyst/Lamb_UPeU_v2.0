package pe.edu.upeu.lamb.institucional.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BitacoraCambiosEstructuraResponse {
    private Long id;
    private String entidadAfectada;
    private Long idEntidadAfectada;
    private String accion;
    private String detalle;
    private Instant fechaCambio;
}
