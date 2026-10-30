export type EstadoPostulacion = 'INSCRITO' | 'EVALUADO' | 'ADMITIDO' | 'RECHAZADO';

export interface PostulacionRequest {
  idPersona: number;
  idPrograma: number;
  periodo: string;
}

export interface EvaluarPostulacionRequest {
  puntajeExamen: number;
}

export interface PostulacionResponse {
  id: number;
  idPersona: number;
  idPrograma: number;
  periodo: string;
  puntajeExamen: number | null;
  estado: EstadoPostulacion;
  fechaRegistro: string;
}
