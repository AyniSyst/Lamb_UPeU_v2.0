package pe.edu.upeu.lamb.admision.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamenAdmisionResponse {
    private Long id;
    private Long idConvocatoria;
    private String codigoConvocatoria;
    private String codigo;
    private String nombre;
    private Instant fechaExamen;
    private Integer duracionMinutos;
    private Long idAula;
    private Integer aforoMaximo;
    private Integer aforoAsignado;
    private String estado;
}
