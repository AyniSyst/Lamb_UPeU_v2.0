import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { NotaRequest, NotaResponse } from './nota.model';

@Injectable({ providedIn: 'root' })
export class NotaService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/notas`;

  listar(idGrupo?: number): Observable<NotaResponse[]> {
    const params = idGrupo != null ? { idGrupo } : undefined;
    return this.http.get<NotaResponse[]>(this.baseUrl, { params });
  }

  obtener(id: number): Observable<NotaResponse> {
    return this.http.get<NotaResponse>(`${this.baseUrl}/${id}`);
  }

  crear(request: NotaRequest): Observable<NotaResponse> {
    return this.http.post<NotaResponse>(this.baseUrl, request);
  }

  actualizar(id: number, request: NotaRequest): Observable<NotaResponse> {
    return this.http.put<NotaResponse>(`${this.baseUrl}/${id}`, request);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
