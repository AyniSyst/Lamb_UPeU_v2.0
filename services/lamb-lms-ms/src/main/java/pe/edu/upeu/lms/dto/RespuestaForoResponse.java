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
public class RespuestaForoResponse {
    private Long id;
    private ForoHiloResponse foroHilo;
    private String idEstudiante;
    private String mensaje;
    private LocalDateTime fecha;
}
