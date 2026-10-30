package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoHito;
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
public class HitoCalendarioResponse {
    private Long id;
    private Long idCalendario;
    private String nombreCalendario;
    private TipoHito tipo;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
