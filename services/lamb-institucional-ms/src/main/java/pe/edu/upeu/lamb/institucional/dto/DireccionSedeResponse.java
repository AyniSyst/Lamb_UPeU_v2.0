package pe.edu.upeu.lamb.institucional.dto;

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
public class DireccionSedeResponse {
    private Long id;
    private Long idSede;
    private Long idUbigeo;
    private String direccionDetalle;
    private String referencia;
}
