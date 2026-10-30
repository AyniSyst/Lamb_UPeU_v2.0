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
public class AnuncioCursoResponse {
    private Long id;
    private CursoResponse curso;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaPublicacion;
}
