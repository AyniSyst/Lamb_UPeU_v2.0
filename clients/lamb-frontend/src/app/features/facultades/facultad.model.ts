export interface FacultadRequest {
  nombre: string;
  decano: string | null;
}

export interface FacultadResponse {
  id: number;
  nombre: string;
  decano: string | null;
}
