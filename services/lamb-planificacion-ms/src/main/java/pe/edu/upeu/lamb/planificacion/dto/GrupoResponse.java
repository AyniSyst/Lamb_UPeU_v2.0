package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoGrupo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoResponse {
    private Long id;
    private String codigo;
    private Long idOferta;
    private Long idPrograma;
    private String periodo;
    private Integer cupoMaximo;
    private EstadoGrupo estado;
    private String motivo;
    private Long version;
    private CursoResponse curso;
    private List<HorarioGrupoResponse> horarios;
    private List<AsignacionDocenteResponse> docentes;
}
