package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.PlataformaVirtual;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AulaVirtualGrupoRequest {

    @NotNull
    private Long idGrupo;

    @NotNull
    private PlataformaVirtual plataforma;

    @NotBlank
    @Size(max = 500)
    private String enlace;

    @Size(max = 50)
    private String codigoAcceso;
}
