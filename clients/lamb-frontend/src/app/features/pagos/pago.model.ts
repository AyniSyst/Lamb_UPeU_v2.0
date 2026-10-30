export type MetodoPago = 'TARJETA' | 'YAPE' | 'PLIN' | 'TRANSFERENCIA' | 'EFECTIVO';

export type EstadoPago = 'PENDIENTE' | 'APROBADO' | 'RECHAZADO' | 'REEMBOLSADO';

export interface PagoRequest {
  idPersona: number;
  concepto: string;
  monto: number;
  metodo: MetodoPago;
}

export interface PagoResponse {
  id: number;
  idPersona: number;
  matriculaId: number | null;
  concepto: string;
  monto: number;
  // null mientras el cargo esta PENDIENTE
  metodo: MetodoPago | null;
  estado: EstadoPago;
  fechaPago: string;
  referenciaExterna: string | null;
}
