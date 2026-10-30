package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoDiaNoLectivo;
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
public class DiaNoLectivoResponse {
    private Long id;
    private Long idCalendario;
    private String nombreCalendario;
    private LocalDate fecha;
    private String motivo;
    private TipoDiaNoLectivo tipo;
}
