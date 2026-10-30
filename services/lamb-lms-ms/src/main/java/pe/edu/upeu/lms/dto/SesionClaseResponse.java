package pe.edu.upeu.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SesionClaseResponse {
    private Long id;
    private SeccionCursoResponse seccionCurso;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String enlaceVirtual;
    private Boolean activo;
}
