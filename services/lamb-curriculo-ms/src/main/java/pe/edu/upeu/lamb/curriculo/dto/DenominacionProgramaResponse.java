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
public class DenominacionProgramaResponse {
    private Long id;
    private Long idPrograma;
    private String denominacion;
    private Long idModalidad;
    private String codigoModalidad;
    private Integer duracionSemestres;
    private String resolucion;
}
