package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnioAcademicoRequest {

    @NotNull
    private Integer anio;

    private String descripcion;
}
