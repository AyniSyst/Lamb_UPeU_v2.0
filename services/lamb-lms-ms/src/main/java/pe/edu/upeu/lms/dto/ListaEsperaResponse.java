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
public class ListaEsperaResponse {
    private Long id;
    private SeccionCursoResponse seccionCurso;
    private String idEstudiante;
    private LocalDateTime fechaRegistro;
    private Integer posicion;
}
