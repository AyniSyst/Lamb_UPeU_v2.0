package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganigramaInstitucionalRequest {

    @NotBlank
    @Size(max = 150)
    private String nombre;

    private Long idInstitucion;

    @Size(max = 255)
    private String urlDocumento;

    private Boolean vigente;
}
