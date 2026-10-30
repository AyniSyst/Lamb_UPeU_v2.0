import { HttpErrorResponse } from '@angular/common/http';

/** Texto para el usuario: el motivo que devuelve el backend, o uno por estado HTTP. */
export function mensajeError(error: unknown, porDefecto: string): string {
  if (!(error instanceof HttpErrorResponse)) {
    return porDefecto;
  }
  const cuerpo = error.error;
  const motivo = typeof cuerpo === 'string' ? cuerpo : (cuerpo?.message ?? cuerpo?.detail ?? cuerpo?.error);
  switch (error.status) {
    case 0:
      return `${porDefecto} No hay conexion con el servidor (Gateway en localhost:18080).`;
    case 401:
      return `${porDefecto} La sesion expiro: vuelve a iniciar sesion.`;
    case 403:
      return `${porDefecto} Tu rol no tiene permiso para esta operacion.`;
    case 503:
      return `${porDefecto} El microservicio no esta disponible en este momento.`;
  }
  return motivo ? `${porDefecto} ${motivo}` : `${porDefecto} (HTTP ${error.status})`;
}
