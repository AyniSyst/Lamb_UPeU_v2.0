package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.EstadoEncuesta;
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
public class EncuestaDocenteResponse {
    private Long id;
    private String nombre;
    private Long idPeriodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoEncuesta estado;
}
