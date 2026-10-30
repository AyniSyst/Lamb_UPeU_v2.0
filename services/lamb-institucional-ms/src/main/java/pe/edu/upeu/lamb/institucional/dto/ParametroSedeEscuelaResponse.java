package pe.edu.upeu.lamb.institucional.dto;

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
public class ParametroSedeEscuelaResponse {
    private Long id;
    private String clave;
    private String valor;
    private Long idSede;
    private Long idEscuela;
}
