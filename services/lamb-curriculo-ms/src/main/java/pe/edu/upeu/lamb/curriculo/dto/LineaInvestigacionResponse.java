package pe.edu.upeu.lamb.curriculo.dto;

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
public class LineaInvestigacionResponse {
    private Long id;
    private Long idPrograma;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean activa;
}
