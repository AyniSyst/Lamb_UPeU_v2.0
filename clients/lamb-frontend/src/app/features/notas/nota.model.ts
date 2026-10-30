export interface NotaRequest {
  idPersona: number;
  idGrupo: number;
  idComponente: number;
  calificacion: number;
  periodo: string;
}

export interface NotaResponse {
  id: number;
  idPersona: number;
  idGrupo: number;
  idComponente: number;
  codigoComponente: string;
  calificacion: number;
  periodo: string;
}
