package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConvenioInstitucionalRequest {

    @NotNull
    private Long idInstitucion;

    @NotBlank
    @Size(max = 150)
    private String nombreConvenio;

    @Size(max = 150)
    private String institucionSocia;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Size(max = 255)
    private String descripcion;
}
