import { CursoResponse } from '../cursos/curso.model';

export type EstadoGrupo = 'PENDIENTE_VALIDACION' | 'PENDIENTE_ACTA' | 'ABIERTO' | 'ANULADO';
export type DiaSemana = 'LUNES' | 'MARTES' | 'MIERCOLES' | 'JUEVES' | 'VIERNES' | 'SABADO' | 'DOMINGO';
export type RolDocente = 'TITULAR' | 'PRACTICA' | 'APOYO';

export const DIAS_SEMANA: DiaSemana[] = ['LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO'];
export const ROLES_DOCENTE: RolDocente[] = ['TITULAR', 'PRACTICA', 'APOYO'];

export interface HorarioDetalleRequest {
  idAmbiente: number;
  diaSemana: DiaSemana;
  horaInicio: string;
  horaFin: string;
}

export interface DocenteDetalleRequest {
  idDocente: number;
  rol: RolDocente;
  horasSemanales: number;
}

export interface GrupoRequest {
  idOferta: number;
  codigo: string;
  cupoMaximo: number;
  horarios: HorarioDetalleRequest[];
  docentes: DocenteDetalleRequest[];
}

export interface HorarioGrupoResponse {
  id: number;
  idAmbiente: number;
  codigoAmbiente: string;
  diaSemana: DiaSemana;
  horaInicio: string;
  horaFin: string;
}

export interface AsignacionDocenteResponse {
  id: number;
  idDocente: number;
  rol: RolDocente;
  horasSemanales: number;
}

export interface GrupoResponse {
  id: number;
  codigo: string;
  idOferta: number;
  idPrograma: number | null;
  periodo: string;
  cupoMaximo: number;
  estado: EstadoGrupo;
  motivo: string | null;
  curso: CursoResponse;
  horarios: HorarioGrupoResponse[];
  docentes: AsignacionDocenteResponse[];
}
