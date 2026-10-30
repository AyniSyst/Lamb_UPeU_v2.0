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
public class AutoridadInstitucionalResponse {
    private Long id;
    private Long idPersona;
    private String cargo;
    private Long idInstitucion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
