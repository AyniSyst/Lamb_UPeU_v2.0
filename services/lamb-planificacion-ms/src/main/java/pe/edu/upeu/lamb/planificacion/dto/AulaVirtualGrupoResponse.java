package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.PlataformaVirtual;
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
public class AulaVirtualGrupoResponse {
    private Long id;
    private Long idGrupo;
    private String codigoGrupo;
    private PlataformaVirtual plataforma;
    private String enlace;
    private String codigoAcceso;
}
