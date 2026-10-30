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
public class AmbienteResponse {
    private Long id;
    private String nombre;
    private Long idPabellon;
    private Long idSede;
    private String tipoAmbiente;
    private Integer capacidad;
}
