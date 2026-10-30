package pe.edu.upeu.lamb.curriculo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MallaCurricularResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private String nombreArchivo;
    private String url;
    private Integer version;
    private LocalDate fechaCarga;
}
