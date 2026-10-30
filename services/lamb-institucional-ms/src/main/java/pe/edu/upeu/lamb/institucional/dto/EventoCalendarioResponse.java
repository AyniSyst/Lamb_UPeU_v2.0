package pe.edu.upeu.lamb.institucional.dto;

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
public class EventoCalendarioResponse {
    private Long id;
    private String nombre;
    private Long idCalendarioAcademico;
    private Long idTipoEvento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
