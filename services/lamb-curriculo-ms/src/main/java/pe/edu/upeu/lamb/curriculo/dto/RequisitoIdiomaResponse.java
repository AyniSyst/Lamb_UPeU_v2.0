package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoIdioma;
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
public class RequisitoIdiomaResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private String idioma;
    private TipoIdioma tipo;
    private String nivel;
    private String modoAcreditacion;
}
