import { CursoResponse } from '../cursos/curso.model';

export type EstadoSeccion = 'PENDIENTE_VALIDACION' | 'PENDIENTE_ACTA' | 'ABIERTA' | 'ANULADA';

export interface SeccionRequest {
  cursoId: number;
  periodo: string;
  docente?: string;
  cupoMaximo: number;
  horario?: string;
}

export interface SeccionResponse {
  id: number;
  periodo: string;
  docente: string | null;
  cupoMaximo: number;
  horario: string | null;
  estado: EstadoSeccion;
  motivo: string | null;
  curso: CursoResponse;
}
