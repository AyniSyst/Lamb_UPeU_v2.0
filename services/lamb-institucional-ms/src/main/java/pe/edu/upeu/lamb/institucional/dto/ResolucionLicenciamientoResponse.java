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
public class ResolucionLicenciamientoResponse {
    private Long id;
    private String numeroResolucion;
    private Long idInstitucion;
    private String tipoLicenciamiento;
    private LocalDate fechaEmision;
    private LocalDate fechaVigencia;
    private String entidadEmisora;
    private String observaciones;
}
