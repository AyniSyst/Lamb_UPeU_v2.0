package pe.edu.upeu.lamb.institucional.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstitucionResponse {
    private Long id;
    private String razonSocial;
    private String ruc;
    private String nombreComercial;
    private Long idTipoInstitucion;
    private String direccion;
    private String telefono;
    private String email;
    private String sitioWeb;
    private LocalDate fechaFundacion;
}
