package pe.edu.upeu.ingresospagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BecaAsignadaResponse {
    private Long id;
    private String idEstudiante;
    private BecaResponse beca;
    private LocalDateTime fechaAsignacion;
    private Boolean activo;
}
