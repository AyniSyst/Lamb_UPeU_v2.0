package pe.edu.upeu.lamb.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaSolicitudAperturaResponse {
    private Long id;
    private Long idSolicitud;
    private Boolean aprobada;
    private String observacion;
    private Long idResponsable;
    private LocalDateTime fecha;
}
