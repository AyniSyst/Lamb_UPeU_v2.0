package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private Long idPrograma;

    @NotNull
    private Long idModalidad;

    @NotBlank
    @Size(max = 20)
    private String periodo;

    @Size(max = 500)
    private String observacion;

    // Detalle: la cabecera y los documentos entran en una sola transaccion
    @NotNull
    @Size(min = 1, message = "Debe presentar al menos un documento")
    @Valid
    private List<DocumentoPresentadoRequest> documentos;
}
