export type EstadoMatricula = 'REGISTRADA' | 'CONFIRMADA' | 'ANULADA';

export interface DetalleMatriculaRequest {
  idSeccion: number;
  creditos: number;
}

export interface DetalleMatriculaResponse {
  id: number;
  idSeccion: number;
  creditos: number;
}

export interface CondicionMatriculaResponse {
  id: number;
  nombre: string;
  descripcion: string;
}

export interface MatriculaRequest {
  idPersona: number;
  periodo: string;
  detalles: DetalleMatriculaRequest[];
}

export interface MatriculaResponse {
  id: number;
  idPersona: number;
  periodo: string;
  fecha: string;
  estado: EstadoMatricula;
  detalles: DetalleMatriculaResponse[];
  condiciones: CondicionMatriculaResponse[];
}
