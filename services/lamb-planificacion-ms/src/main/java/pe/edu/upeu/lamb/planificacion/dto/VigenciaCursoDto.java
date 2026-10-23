package pe.edu.upeu.lamb.planificacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lo que planificacion necesita de curriculo para abrir una seccion (no es copia de su entidad).
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VigenciaCursoDto {
    private String codigoCurso;
    private boolean enPlanVigente;
    private Long idPlan;
    private String version;
}
