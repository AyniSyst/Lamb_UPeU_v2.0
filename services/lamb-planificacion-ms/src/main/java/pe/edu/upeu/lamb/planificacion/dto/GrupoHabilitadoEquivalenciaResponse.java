package pe.edu.upeu.lamb.planificacion.dto;

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
public class GrupoHabilitadoEquivalenciaResponse {
    private Long id;
    private Long idEquivalencia;
    private Long idGrupo;
    private String codigoGrupo;
    private Integer cuposHabilitados;
    private Integer cuposDisponibles;
}
