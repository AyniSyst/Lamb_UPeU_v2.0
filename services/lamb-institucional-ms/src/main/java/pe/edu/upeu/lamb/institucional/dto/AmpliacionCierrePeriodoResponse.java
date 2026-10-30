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
public class AmpliacionCierrePeriodoResponse {
    private Long id;
    private Long idPeriodoAcademico;
    private String tipoAccion;
    private String numeroResolucion;
    private LocalDate fechaAccion;
    private String descripcion;
}
