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
public class CursoCatalogoResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private Integer creditos;
    private Long idDepartamento;
    private String codigoDepartamento;
    private Long idModalidad;
    private String codigoModalidad;
}
