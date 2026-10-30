package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoPresentadoRequest {

    @NotNull
    private Long idTipoDocumento;

    @Size(max = 80)
    private String numeroDocumento;

    @Size(max = 500)
    private String urlArchivo;

    @Size(max = 300)
    private String observacion;
}
