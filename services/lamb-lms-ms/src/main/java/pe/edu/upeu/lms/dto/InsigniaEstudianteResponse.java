package pe.edu.upeu.lms.dto;

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
public class InsigniaEstudianteResponse {
    private Long id;
    private InsigniaResponse insignia;
    private String idEstudiante;
    private LocalDateTime fechaOtorgamiento;
}
