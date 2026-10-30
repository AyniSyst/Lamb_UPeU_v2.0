package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoVoluntariado;
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
public class VoluntariadoResponse {
    private Long id;
    private String nombre;
    private String organizacion;
    private TipoVoluntariado tipo;
    private Integer horasRequeridas;
    private String descripcion;
    private Boolean activo;
}
