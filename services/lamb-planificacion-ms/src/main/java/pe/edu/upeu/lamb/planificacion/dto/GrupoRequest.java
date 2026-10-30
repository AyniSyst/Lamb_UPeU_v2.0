package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Apertura de un grupo: cabecera + detalles en una sola peticion (y en una sola transaccion).
 * Los docentes pueden quedar vacios: sin titular, evaluacion rechaza el acta y la saga anula el grupo.
 */
@Getter
@Setter
public class GrupoRequest {

    @NotNull
    private Long idOferta;

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotNull
    @Min(1)
    private Integer cupoMaximo;

    @NotEmpty
    private List<@Valid @NotNull HorarioDetalleRequest> horarios = new ArrayList<>();

    private List<@Valid @NotNull DocenteDetalleRequest> docentes = new ArrayList<>();
}
