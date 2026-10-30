package pe.edu.upeu.lamb.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RubricaResponse {
    private Long id;
    private Long idComponente;
    private String codigoComponente;
    private String nombre;
    private String descripcion;
}
