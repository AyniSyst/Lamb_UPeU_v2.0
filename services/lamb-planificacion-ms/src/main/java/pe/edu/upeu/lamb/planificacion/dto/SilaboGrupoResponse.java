package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoSilabo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SilaboGrupoResponse {
    private Long id;
    private Long idGrupo;
    private String codigoGrupo;
    private Long idSilaboBase;
    private String metodologia;
    private EstadoSilabo estado;
    private LocalDate fechaPresentacion;
}
