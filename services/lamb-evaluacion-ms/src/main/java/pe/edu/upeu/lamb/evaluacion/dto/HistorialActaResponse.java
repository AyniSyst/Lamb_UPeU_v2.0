package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.EstadoActa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Vista: un paso del historial de estados del acta
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialActaResponse {
    private EstadoActa estado;
    private LocalDateTime fecha;
    private String detalle;
}
