package pe.edu.upeu.lamb.matricula.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lo que matricula necesita de planificacion para validar una seccion (no es copia de su entidad).
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeccionDisponibleDto {
    private Long id;
    private String estado;
    private Integer cupoMaximo;
}
