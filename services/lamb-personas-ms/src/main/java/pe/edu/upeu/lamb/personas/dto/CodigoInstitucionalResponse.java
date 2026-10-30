package pe.edu.upeu.lamb.personas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.CodigoInstitucionalTipo;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodigoInstitucionalResponse {
    private Long id;
    private Long idPersona;
    private CodigoInstitucionalTipo tipo;
    private String codigo;
    private Instant fechaRegistro;
}
