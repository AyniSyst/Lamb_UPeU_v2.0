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
public class UnidadAdministrativaResponse {
    private Long id;
    private String nombre;
    private Long idFacultad;
    private String responsable;
    private String descripcion;
}
