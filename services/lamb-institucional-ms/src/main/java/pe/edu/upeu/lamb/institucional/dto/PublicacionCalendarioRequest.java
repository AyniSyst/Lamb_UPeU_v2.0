package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PublicacionCalendarioRequest {

    @NotNull
    private Long idCalendarioAcademico;

    private Instant fechaPublicacion;

    private String urlDocumento;
}
