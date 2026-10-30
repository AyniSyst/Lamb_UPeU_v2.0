package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoIdioma;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisitoIdiomaRequest {

    @NotNull
    private Long idPlan;

    @NotBlank
    @Size(max = 50)
    private String idioma;

    @NotNull
    private TipoIdioma tipo;

    @NotBlank
    @Size(max = 30)
    private String nivel;

    @Size(max = 200)
    private String modoAcreditacion;
}
