package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.EstadoAsistencia;
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
public class RegistroAsistenciaResponse {
    private Long id;
    private Long idGrupo;
    private Long idPersona;
    private Long idSesionClase;
    private LocalDate fecha;
    private EstadoAsistencia estado;
}
