package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.RolComision;
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
public class MiembroComisionResponse {
    private Long id;
    private Long idComision;
    private String nombreComision;
    private Long idPersona;
    private RolComision rol;
    private LocalDate fechaIncorporacion;
}
