package pe.edu.upeu.lamb.planificacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SesionClaseResponse {
    private Long id;
    private Long idSilabo;
    private Integer numero;
    private Integer semana;
    private String tema;
    private Long idUnidadAprendizaje;
    private String actividades;
}
