package pe.edu.upeu.lamb.planificacion.dto;

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
public class PabellonResponse {
    private Long id;
    private Long idSede;
    private String codigo;
    private String nombre;
    private Integer numeroPisos;
}
