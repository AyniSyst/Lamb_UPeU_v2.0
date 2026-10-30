package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoDiaNoLectivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DiaNoLectivoRequest {

    @NotNull
    private Long idCalendario;

    @NotNull
    private LocalDate fecha;

    @NotBlank
    @Size(max = 150)
    private String motivo;

    @NotNull
    private TipoDiaNoLectivo tipo;
}
