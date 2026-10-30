package pe.edu.upeu.lamb.curriculo.dto;

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
public class UnidadAprendizajeResponse {
    private Long id;
    private Long idSilabo;
    private String versionSilabo;
    private Integer numero;
    private String nombre;
    private Integer semanaInicio;
    private Integer semanaFin;
    private String contenidos;
}
