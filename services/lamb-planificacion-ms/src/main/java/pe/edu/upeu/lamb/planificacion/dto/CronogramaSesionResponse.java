package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoSesion;
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
public class CronogramaSesionResponse {
    private Long id;
    private Long idSesion;
    private String temaSesion;
    private LocalDate fechaProgramada;
    private LocalDate fechaEjecutada;
    private EstadoSesion estado;
    private String observacion;
}
