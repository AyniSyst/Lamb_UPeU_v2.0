package pe.edu.upeu.lamb.planificacion.entity;

public enum EstadoSeccion {
    // curriculo no respondio (circuit breaker): falta confirmar que el curso esta en un plan vigente
    PENDIENTE_VALIDACION,
    // validada y publicada como seccion.abierta: espera que evaluacion abra su acta
    PENDIENTE_ACTA,
    // evaluacion confirmo el acta (acta.abierta)
    ABIERTA,
    // compensacion: evaluacion rechazo el acta (acta.rechazada)
    ANULADA
}
