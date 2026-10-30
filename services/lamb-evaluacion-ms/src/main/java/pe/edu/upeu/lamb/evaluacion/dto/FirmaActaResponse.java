package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.RolFirma;
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
public class FirmaActaResponse {
    private Long id;
    private Long idActa;
    private String codigoCursoActa;
    private Long idPersona;
    private RolFirma rol;
    private LocalDate fecha;
}
