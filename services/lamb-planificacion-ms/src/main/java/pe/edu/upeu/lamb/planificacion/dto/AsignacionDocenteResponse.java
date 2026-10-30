package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.RolDocente;
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
public class AsignacionDocenteResponse {
    private Long id;
    private Long idGrupo;
    private String codigoGrupo;
    private Long idDocente;
    private RolDocente rol;
    private Integer horasSemanales;
}
