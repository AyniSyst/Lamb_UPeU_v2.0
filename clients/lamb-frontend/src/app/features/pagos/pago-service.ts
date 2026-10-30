import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { PagoRequest, PagoResponse } from './pago.model';

@Injectable({ providedIn: 'root' })
export class PagoService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/pagos`;

  listar(): Observable<PagoResponse[]> {
    return this.http.get<PagoResponse[]>(this.baseUrl);
  }

  obtener(id: number): Observable<PagoResponse> {
    return this.http.get<PagoResponse>(`${this.baseUrl}/${id}`);
  }

  procesar(request: PagoRequest): Observable<PagoResponse> {
    return this.http.post<PagoResponse>(this.baseUrl, request);
  }

  reembolsar(id: number): Observable<PagoResponse> {
    return this.http.post<PagoResponse>(`${this.baseUrl}/${id}/reembolsar`, {});
  }
}
