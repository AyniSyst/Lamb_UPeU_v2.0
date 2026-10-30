package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.EstadoSilabo;
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
public class SilaboBaseResponse {
    private Long id;
    private Long idCurso;
    private String codigoCurso;
    private String version;
    private String competencias;
    private String metodologia;
    private String evaluacion;
    private String bibliografia;
    private EstadoSilabo estado;
}
