export type EstadoActa = 'ABIERTA' | 'RECHAZADA' | 'CERRADA';

export interface Acta {
  id: number;
  idGrupo: number;
  codigoCurso: string;
  periodo: string;
  idDocente: number | null;
  estado: EstadoActa;
  motivo: string | null;
  creditos: number | null;
  fechaRegistro: string;
  fechaCierre: string | null;
}
