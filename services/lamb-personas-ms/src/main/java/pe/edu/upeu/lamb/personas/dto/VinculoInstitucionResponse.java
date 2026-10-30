package pe.edu.upeu.lamb.personas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.VinculoTipo;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VinculoInstitucionResponse {
    private Long id;
    private Long idPersona;
    private VinculoTipo tipoVinculo;
    private String observacion;
    private Instant fechaRegistro;
}
