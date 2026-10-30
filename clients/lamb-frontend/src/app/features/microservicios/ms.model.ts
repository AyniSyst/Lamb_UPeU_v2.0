export type TipoCampo =
  | 'text'
  | 'textarea'
  | 'email'
  | 'number'
  | 'decimal'
  | 'checkbox'
  | 'date'
  | 'datetime'
  | 'time'
  | 'select'
  | 'json';

export interface Campo {
  nombre: string;
  etiqueta: string;
  tipo: TipoCampo;
  requerido: boolean;
  max?: number;
  opciones?: string[];
  // JSON de ejemplo para listas u objetos anidados
  ejemplo?: string;
}

export interface Operacion {
  metodo: 'GET' | 'POST' | 'PUT' | 'PATCH' | 'DELETE';
  // Relativa a la ruta del recurso; puede llevar {variables}
  ruta: string;
  etiqueta: string;
  vars: Campo[];
  params: Campo[];
  campos: Campo[];
  cuerpo: boolean;
  lista: boolean;
}

export interface Recurso {
  clave: string;
  etiqueta: string;
  grupo: string;
  // Ruta del backend (/api/v1/...), sin el prefijo del Gateway
  ruta: string;
  listar: Operacion | null;
  obtener: boolean;
  crear: Operacion | null;
  actualizar: Operacion | null;
  eliminar: boolean;
  acciones: Operacion[];
  consultas: Operacion[];
}

export interface Microservicio {
  clave: string;
  nombre: string;
  descripcion: string;
  // Prefijo en el Gateway: /ms/<clave> -> el microservicio
  prefijo: string;
  recursos: Recurso[];
}
