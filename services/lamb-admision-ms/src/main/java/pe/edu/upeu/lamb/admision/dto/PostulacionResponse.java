package pe.edu.upeu.lamb.admision.dto;

import pe.edu.upeu.lamb.admision.entity.EstadoPostulacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostulacionResponse {
    private Long id;
    private Long idPersona;
    private Long idPrograma;
    private String periodo;
    private BigDecimal puntajeExamen;
    private EstadoPostulacion estado;
    private Instant fechaRegistro;
}
