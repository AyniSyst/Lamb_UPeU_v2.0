package pe.edu.upeu.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareaResponse {
    private Long id;
    private CursoResponse curso;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaLimite;
    private BigDecimal puntajeMaximo;
    private Boolean activo;
}
