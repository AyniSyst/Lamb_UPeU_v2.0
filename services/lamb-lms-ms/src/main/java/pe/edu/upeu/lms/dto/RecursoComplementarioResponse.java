package pe.edu.upeu.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.lms.entity.TipoRecursoComplementario;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecursoComplementarioResponse {
    private Long id;
    private CursoResponse curso;
    private String nombre;
    private TipoRecursoComplementario tipo;
    private String url;
    private Boolean activo;
}
