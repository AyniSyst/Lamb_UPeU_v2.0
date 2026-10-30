package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InstitucionRequest {

    @NotBlank
    @Size(max = 255)
    private String razonSocial;

    @NotBlank
    @Size(max = 11)
    private String ruc;

    @Size(max = 150)
    private String nombreComercial;

    @NotNull
    private Long idTipoInstitucion;

    @Size(max = 255)
    private String direccion;

    @Size(max = 20)
    private String telefono;

    @Size(max = 100)
    private String email;

    @Size(max = 150)
    private String sitioWeb;

    private LocalDate fechaFundacion;
}
