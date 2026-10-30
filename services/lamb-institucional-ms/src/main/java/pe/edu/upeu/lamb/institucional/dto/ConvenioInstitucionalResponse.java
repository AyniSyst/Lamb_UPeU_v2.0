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
public class ConvenioInstitucionalResponse {
    private Long id;
    private Long idInstitucion;
    private String nombreConvenio;
    private String institucionSocia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;
}
