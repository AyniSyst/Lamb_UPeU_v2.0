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
public class CambioUnidadAcademicaResponse {
    private Long id;
    private Long idFacultad;
    private Long idEscuela;
    private String tipoCambio;
    private String numeroResolucion;
    private LocalDate fechaCambio;
    private String descripcion;
}
