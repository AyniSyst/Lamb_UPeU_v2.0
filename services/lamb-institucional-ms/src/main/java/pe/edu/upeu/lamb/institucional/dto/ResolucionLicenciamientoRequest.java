package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResolucionLicenciamientoRequest {

    @NotBlank
    @Size(max = 100)
    private String numeroResolucion;

    @NotNull
    private Long idInstitucion;

    @Size(max = 100)
    private String tipoLicenciamiento;

    @NotNull
    private LocalDate fechaEmision;

    private LocalDate fechaVigencia;

    @Size(max = 150)
    private String entidadEmisora;

    @Size(max = 255)
    private String observaciones;
}
