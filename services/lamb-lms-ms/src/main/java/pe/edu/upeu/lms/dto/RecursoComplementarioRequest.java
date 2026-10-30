package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.lms.entity.TipoRecursoComplementario;

@Getter
@Setter
public class RecursoComplementarioRequest {

    @NotNull
    private Long cursoId;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotNull
    private TipoRecursoComplementario tipo;

    @NotBlank
    @Size(max = 500)
    private String url;

    @NotNull
    private Boolean activo;
}
