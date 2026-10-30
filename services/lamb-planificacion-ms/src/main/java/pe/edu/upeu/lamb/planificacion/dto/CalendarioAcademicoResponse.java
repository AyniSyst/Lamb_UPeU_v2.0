package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoCalendario;
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
public class CalendarioAcademicoResponse {
    private Long id;
    private Long idPeriodo;
    private String codigoPeriodo;
    private Long idSede;
    private String nombre;
    private String resolucion;
    private EstadoCalendario estado;
}
