package pe.edu.upeu.lamb.institucional.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganigramaInstitucionalResponse {
    private Long id;
    private String nombre;
    private Long idInstitucion;
    private String urlDocumento;
    private Boolean vigente;
}
