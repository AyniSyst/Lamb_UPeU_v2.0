export type TipoPersona = 'ESTUDIANTE' | 'DOCENTE' | 'ADMINISTRATIVO';

export interface PersonaRequest {
  tipoDocumento: string;
  numeroDocumento: string;
  nombres: string;
  apellidos: string;
  email: string;
  telefono: string | null;
  fechaNacimiento: string | null;
  tipo: TipoPersona;
}

export interface PersonaResponse {
  id: number;
  tipoDocumento: string;
  numeroDocumento: string;
  nombres: string;
  apellidos: string;
  email: string;
  telefono: string | null;
  fechaNacimiento: string | null;
  tipo: TipoPersona;
}
