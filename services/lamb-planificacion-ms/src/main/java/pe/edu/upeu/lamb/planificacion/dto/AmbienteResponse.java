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
public class AmbienteResponse {
    private Long id;
    private Long idPabellon;
    private String codigoPabellon;
    private Long idTipoAmbiente;
    private String codigoTipoAmbiente;
    private String codigo;
    private String nombre;
    private Integer piso;
    private Integer aforo;
    private Boolean activo;
}
