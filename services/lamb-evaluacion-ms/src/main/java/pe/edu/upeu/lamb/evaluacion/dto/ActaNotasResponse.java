package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.EstadoActa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// Vista: acta de notas (cabecera del acta + nota final de cada estudiante)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActaNotasResponse {
    private Long idActa;
    private Long idGrupo;
    private String codigoCurso;
    private String periodo;
    private Long idDocente;
    private EstadoActa estado;
    private String escala;
    private LocalDateTime fechaCierre;
    private List<NotaFinalResponse> notas;
}
