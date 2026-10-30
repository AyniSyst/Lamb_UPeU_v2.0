import { FacultadResponse } from '../facultades/facultad.model';

export type GradoAcademico = 'BACHILLER' | 'LICENCIATURA' | 'MAESTRIA' | 'DOCTORADO';

export interface ProgramaRequest {
  nombre: string;
  gradoAcademico: GradoAcademico;
  duracionSemestres: number;
  facultadId: number;
}

export interface ProgramaResponse {
  id: number;
  nombre: string;
  gradoAcademico: GradoAcademico;
  duracionSemestres: number;
  facultad: FacultadResponse;
}
