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
public class AsistenciaResponse {
    private Long id;
    private SesionClaseResponse sesionClase;
    private String idEstudiante;
    private Boolean presente;
    private LocalDateTime fechaRegistro;
}
