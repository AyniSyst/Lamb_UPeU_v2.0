package pe.edu.upeu.lamb.evaluacion.dto;

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
public class PlazoAperturaResponse {
    private Long id;
    private Long idGrupo;
    private Long idDocente;
    private Long idSolicitud;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String motivo;
}
