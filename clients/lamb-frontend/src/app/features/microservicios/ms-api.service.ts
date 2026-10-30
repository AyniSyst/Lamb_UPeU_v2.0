import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Campo, Microservicio, Operacion, Recurso } from './ms.model';

export type Fila = Record<string, unknown>;

/** Llama a un recurso del catalogo por la ruta /ms/<servicio> del Gateway. */
@Injectable({ providedIn: 'root' })
export class MsApiService {
  private readonly http = inject(HttpClient);

  listar(ms: Microservicio, recurso: Recurso, params: Record<string, unknown> = {}): Observable<unknown> {
    return this.http.get(this.url(ms, recurso.ruta), { params: this.params(params) });
  }

  crear(ms: Microservicio, recurso: Recurso, cuerpo: unknown): Observable<unknown> {
    return this.http.post(this.url(ms, recurso.ruta), cuerpo);
  }

  actualizar(ms: Microservicio, recurso: Recurso, id: unknown, cuerpo: unknown): Observable<unknown> {
    return this.http.put(this.url(ms, `${recurso.ruta}/${id}`), cuerpo);
  }

  eliminar(ms: Microservicio, recurso: Recurso, id: unknown): Observable<unknown> {
    return this.http.delete(this.url(ms, `${recurso.ruta}/${id}`));
  }

  /** Accion o consulta: rellena las {variables} de la ruta y envia params/cuerpo segun la operacion. */
  ejecutar(ms: Microservicio, recurso: Recurso, op: Operacion, valores: { vars: Fila; params: Fila; cuerpo: unknown }): Observable<unknown> {
    const ruta = op.ruta.replace(/\{(\w+)\}/g, (_, nombre: string) => encodeURIComponent(String(valores.vars[nombre] ?? '')));
    const url = this.url(ms, recurso.ruta + ruta);
    const params = this.params(valores.params);
    const cuerpo = op.cuerpo ? valores.cuerpo : {};
    switch (op.metodo) {
      case 'GET':
        return this.http.get(url, { params });
      case 'DELETE':
        return this.http.delete(url, { params });
      case 'PUT':
        return this.http.put(url, cuerpo, { params });
      case 'PATCH':
        return this.http.patch(url, cuerpo, { params });
      default:
        return this.http.post(url, cuerpo, { params });
    }
  }

  private url(ms: Microservicio, ruta: string): string {
    return `${environment.apiBaseUrl}${ms.prefijo}${ruta}`;
  }

  private params(valores: Record<string, unknown>): HttpParams {
    let params = new HttpParams();
    for (const [k, v] of Object.entries(valores)) {
      if (v !== null && v !== undefined && v !== '') {
        params = params.set(k, String(v));
      }
    }
    return params;
  }
}

/** Convierte el valor de un input al tipo que espera el backend. */
export function convertir(campo: Campo, valor: unknown): unknown {
  if (valor === '' || valor === null || valor === undefined) {
    return campo.tipo === 'checkbox' ? false : null;
  }
  switch (campo.tipo) {
    case 'number':
      return Math.trunc(Number(valor));
    case 'decimal':
      return Number(valor);
    case 'checkbox':
      return Boolean(valor);
    case 'json':
      return typeof valor === 'string' ? JSON.parse(valor) : valor;
    case 'datetime':
      // input datetime-local: 2026-10-30T10:00 -> con segundos para LocalDateTime
      return String(valor).length === 16 ? `${valor}:00` : valor;
    case 'time':
      return String(valor).length === 5 ? `${valor}:00` : valor;
    default:
      return valor;
  }
}

/** Valor de una fila del backend para prellenar un input al editar. */
export function valorParaInput(campo: Campo, valor: unknown): unknown {
  if (valor === null || valor === undefined) {
    return campo.tipo === 'checkbox' ? false : campo.tipo === 'json' ? (campo.ejemplo ?? '') : '';
  }
  switch (campo.tipo) {
    case 'json':
      return JSON.stringify(valor, null, 1);
    case 'datetime':
      return String(valor).slice(0, 16);
    case 'time':
      return String(valor).slice(0, 5);
    default:
      return valor;
  }
}
