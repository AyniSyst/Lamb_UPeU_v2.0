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
public class DistribucionHorasResponse {
    private Long id;
    private Long idCurso;
    private String codigoCurso;
    private Integer horasTeoria;
    private Integer horasPractica;
    private Integer horasLaboratorio;
}
