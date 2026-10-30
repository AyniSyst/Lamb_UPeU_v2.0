package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoPeriodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoAcademicoResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private Long idTipoPeriodo;
    private String codigoTipoPeriodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoPeriodo estado;
}
