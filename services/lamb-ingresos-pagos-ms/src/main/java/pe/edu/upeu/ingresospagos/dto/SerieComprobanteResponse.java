package pe.edu.upeu.ingresospagos.dto;

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
public class SerieComprobanteResponse {
    private Long id;
    private TipoComprobanteResponse tipoComprobante;
    private String serie;
    private Integer correlativoActual;
    private Boolean activo;
}
