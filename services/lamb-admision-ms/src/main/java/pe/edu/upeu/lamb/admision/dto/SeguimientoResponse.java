package pe.edu.upeu.lamb.admision.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoResponse {
    private Long id;
    private Long idPostulante;
    private String codigoPostulante;
    private String canal;
    private String medioCaptacion;
    private String asunto;
    private String detalle;
    private String resultado;
    private Long idResponsable;
    private Instant fechaContacto;
    private Instant proximoContacto;
}
