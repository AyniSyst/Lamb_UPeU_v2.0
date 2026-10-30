package pe.edu.upeu.lamb.admision.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoPresentadoResponse {
    private Long id;
    private Long idTipoDocumento;
    private String codigoTipoDocumento;
    private String nombreTipoDocumento;
    private String numeroDocumento;
    private String urlArchivo;
    private String observacion;
    private Instant fechaEntrega;
}
