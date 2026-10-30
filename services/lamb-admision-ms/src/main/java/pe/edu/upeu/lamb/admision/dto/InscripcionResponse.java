package pe.edu.upeu.lamb.admision.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionResponse {
    private Long id;
    private Long idPersona;
    private Long idPrograma;
    private Long idModalidad;
    private String codigoModalidad;
    private String nombreModalidad;
    private String periodo;
    private String estado;
    private String observacion;
    private Instant fechaRegistro;
    private Instant fechaAprobacion;
    private Long idAprobadoPor;
    private Long version;
    private List<DocumentoPresentadoResponse> documentos;
}
