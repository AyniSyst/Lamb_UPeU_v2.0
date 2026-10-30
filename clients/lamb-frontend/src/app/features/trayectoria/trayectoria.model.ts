export interface TrayectoriaDataResponse {
  id: number;
  idPersona: number;
  idPrograma: number;
  periodo: string;
  estado: string;
  fechaIngreso: string;
}

export interface HistorialCursoResponse {
  idCurso: number;
  nombreCurso: string;
  estado: string;
}

export interface TrayectoriaResponse {
  trayectoria: TrayectoriaDataResponse;
  historial: HistorialCursoResponse[];
}
