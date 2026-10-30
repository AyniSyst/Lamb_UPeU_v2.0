package pe.edu.upeu.lamb.trayectoria.dto;

public record NivelRiesgoResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion,
        boolean activo
) {
}
