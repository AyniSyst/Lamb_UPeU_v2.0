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
public class ReprogramacionEventoResponse {
    private Long id;
    private Long idEventoCalendario;
    private String numeroResolucion;
    private LocalDate fechaInicioNueva;
    private LocalDate fechaFinNueva;
    private String motivo;
}
