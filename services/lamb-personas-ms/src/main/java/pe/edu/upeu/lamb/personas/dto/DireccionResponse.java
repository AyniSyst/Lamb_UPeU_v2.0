package pe.edu.upeu.lamb.personas.dto;

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
public class DireccionResponse {

    private Long id;
    private Long idPersona;
    private Long idTipoDireccion;
    private Long idUbigeo;
    private String direccion;
    private Instant fechaRegistro;
}
