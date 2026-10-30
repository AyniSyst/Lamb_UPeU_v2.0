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
public class EntregaTareaResponse {
    private Long id;
    private TareaResponse tarea;
    private String idEstudiante;
    private String urlArchivo;
    private LocalDateTime fechaEntrega;
    private BigDecimal nota;
}
