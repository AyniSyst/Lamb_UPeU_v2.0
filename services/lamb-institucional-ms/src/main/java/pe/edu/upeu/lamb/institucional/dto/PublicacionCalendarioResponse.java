package pe.edu.upeu.lamb.institucional.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionCalendarioResponse {
    private Long id;
    private Long idCalendarioAcademico;
    private Instant fechaPublicacion;
    private String urlDocumento;
}
