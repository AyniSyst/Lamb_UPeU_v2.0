package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoActividadFormacion;
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
public class ActividadFormacionResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private TipoActividadFormacion tipo;
    private Integer creditos;
    private Integer horas;
    private String descripcion;
}
