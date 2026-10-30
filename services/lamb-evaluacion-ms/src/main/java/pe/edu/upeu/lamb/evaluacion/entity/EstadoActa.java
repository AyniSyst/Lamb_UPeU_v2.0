package pe.edu.upeu.lamb.evaluacion.entity;

public enum EstadoActa {
    // acta lista para registrar notas del grupo
    ABIERTA,
    // no se pudo abrir (regla de negocio): dispara la compensacion en planificacion
    RECHAZADA,
    // notas finales calculadas y publicadas (acta.cerrada): ya no se editan notas
    CERRADA
}
