package pe.edu.upeu.lamb.admision.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostulanteResponse {
    private Long id;
    private Long idPersona;
    private Long idConvocatoria;
    private String codigoConvocatoria;
    private Long idPrograma;
    private Long idModalidad;
    private String codigoModalidad;
    private String codigoPostulante;
    private String colegioProcedencia;
    private Integer anioEgresoColegio;
    private BigDecimal promedioColegio;
    private String telefonoContacto;
    private String correoContacto;
    private String nombreApoderado;
    private String telefonoApoderado;
    private String estado;
    private Instant fechaRegistro;
}
