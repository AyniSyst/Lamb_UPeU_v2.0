package pe.edu.upeu.lamb.curriculo.dto;

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
public class ComisionCurricularResponse {
    private Long id;
    private Long idPrograma;
    private String nombre;
    private String resolucion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean activa;
}
