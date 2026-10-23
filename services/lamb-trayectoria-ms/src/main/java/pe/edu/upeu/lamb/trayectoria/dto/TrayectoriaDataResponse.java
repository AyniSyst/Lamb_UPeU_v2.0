package pe.edu.upeu.lamb.trayectoria.dto;

import lombok.Builder;
import pe.edu.upeu.lamb.trayectoria.entity.EstadoTrayectoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TrayectoriaDataResponse(
        Long id,
        Long idPersona,
        Long idPrograma,
        Long idPostulacion,
        String periodoIngreso,
        EstadoTrayectoria estado,
        Integer creditosAprobados,
        BigDecimal promedioPonderado,
        LocalDateTime fechaRegistro
) {
}
