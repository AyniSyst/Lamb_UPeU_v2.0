package pe.edu.upeu.lamb.admision.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvocatoriaResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private String periodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaExamen;
    private String estado;
    private Integer vacantesTotales;
    private String observacion;
}
