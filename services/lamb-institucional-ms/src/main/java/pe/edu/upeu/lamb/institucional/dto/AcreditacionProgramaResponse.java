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
public class AcreditacionProgramaResponse {
    private Long id;
    private Long idPrograma;
    private String entidadAcreditadora;
    private LocalDate fechaAcreditacion;
    private LocalDate fechaVigencia;
}
