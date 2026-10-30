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
public class AutoridadFacultadResponse {
    private Long id;
    private Long idPersona;
    private Long idFacultad;
    private String cargo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
