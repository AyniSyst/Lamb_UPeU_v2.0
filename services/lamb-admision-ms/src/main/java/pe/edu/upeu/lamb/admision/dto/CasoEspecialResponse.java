package pe.edu.upeu.lamb.admision.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasoEspecialResponse {
    private Long id;
    private Long idPostulante;
    private String codigoPostulante;
    private String tipoCaso;
    private String descripcion;
    private Boolean requiereAjuste;
    private String ajusteOtorgado;
    private String nivelSocioeconomico;
    private Long idResponsable;
    private String estado;
    private Instant fechaRegistro;
    private Instant fechaResolucion;
}
