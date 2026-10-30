export type TipoPersona = 'ESTUDIANTE' | 'DOCENTE' | 'ADMINISTRATIVO';

// Documento de identidad de Peru; el backend valida el mismo formato (TipoDocumentoPeru)
export interface TipoDocumentoPeru {
  codigo: string;
  nombre: string;
  patron: string;
  formato: string;
}

// Respaldo si /tipos-documento no responde: mismo contenido que el enum del backend
export const TIPOS_DOCUMENTO_PERU: TipoDocumentoPeru[] = [
  { codigo: 'DNI', nombre: 'Documento Nacional de Identidad', patron: '\\d{8}', formato: '8 digitos' },
  { codigo: 'CE', nombre: 'Carne de Extranjeria', patron: '[A-Za-z0-9]{9,12}', formato: '9 a 12 caracteres alfanumericos' },
  { codigo: 'PASAPORTE', nombre: 'Pasaporte', patron: '[A-Za-z0-9]{6,12}', formato: '6 a 12 caracteres alfanumericos' },
  { codigo: 'RUC', nombre: 'Registro Unico de Contribuyentes', patron: '(10|15|17|20)\\d{9}', formato: '11 digitos que empiezan con 10, 15, 17 o 20' },
  { codigo: 'PTP', nombre: 'Permiso Temporal de Permanencia', patron: '[A-Za-z0-9]{9,15}', formato: '9 a 15 caracteres alfanumericos' },
  { codigo: 'CPP', nombre: 'Carne de Permiso Temporal de Permanencia', patron: '[A-Za-z0-9]{9,15}', formato: '9 a 15 caracteres alfanumericos' },
  { codigo: 'CDI', nombre: 'Cedula de Identidad Diplomatica', patron: '\\d{6,15}', formato: '6 a 15 digitos' },
  { codigo: 'PARTIDA', nombre: 'Partida de Nacimiento', patron: '[A-Za-z0-9-]{6,15}', formato: '6 a 15 caracteres' },
];

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
