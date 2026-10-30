package pe.edu.upeu.lamb.planificacion.entity;

public enum EstadoGrupo {
    // curriculo no respondio (circuit breaker): falta confirmar que el curso esta en un plan vigente
    PENDIENTE_VALIDACION,
    // validado y publicado como grupo.abierto: espera que evaluacion abra su acta
    PENDIENTE_ACTA,
    // evaluacion confirmo el acta (acta.abierta)
    ABIERTO,
    // compensacion: evaluacion rechazo el acta (acta.rechazada). Libera sus horarios
    ANULADO
}
