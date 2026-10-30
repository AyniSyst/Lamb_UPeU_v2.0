export type EstadoPlan = 'BORRADOR' | 'VIGENTE' | 'DEROGADO';

export interface PlanEstudioRequest {
  idPrograma: number;
  version: string;
  nombre: string;
  idModalidad: number | null;
}

export interface PlanCursoRequest {
  idCursoCatalogo: number;
  ciclo: number;
  idTipoCurso: number;
  idAreaCurricular: number | null;
}

export interface PlanCursoResponse {
  id: number;
  idCursoCatalogo: number;
  codigoCurso: string;
  nombreCurso: string;
  ciclo: number;
  creditos: number;
  tipo: string;
  areaCurricular: string;
  prerrequisitos: string[];
}

export interface PlanEstudioResponse {
  id: number;
  idPrograma: number;
  version: string;
  nombre: string;
  idModalidad: number | null;
  estado: EstadoPlan;
  fechaRegistro: string;
  fechaPublicacion: string | null;
  totalCreditos: number;
  cursos: PlanCursoResponse[];
}
