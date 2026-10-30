package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AcreditacionProgramaRequest {

    @NotNull
    private Long idPrograma;

    @Size(max = 150)
    private String entidadAcreditadora;

    private LocalDate fechaAcreditacion;

    private LocalDate fechaVigencia;
}
