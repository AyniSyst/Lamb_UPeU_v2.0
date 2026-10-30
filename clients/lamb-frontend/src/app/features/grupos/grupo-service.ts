import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { GrupoRequest, GrupoResponse } from './grupo.model';

@Injectable({ providedIn: 'root' })
export class GrupoService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/grupos`;

  listar(): Observable<GrupoResponse[]> {
    return this.http.get<GrupoResponse[]>(this.baseUrl);
  }

  obtener(id: number): Observable<GrupoResponse> {
    return this.http.get<GrupoResponse>(`${this.baseUrl}/${id}`);
  }

  crear(request: GrupoRequest): Observable<GrupoResponse> {
    return this.http.post<GrupoResponse>(this.baseUrl, request);
  }

  actualizar(id: number, request: GrupoRequest): Observable<GrupoResponse> {
    return this.http.put<GrupoResponse>(`${this.baseUrl}/${id}`, request);
  }

  revalidar(id: number): Observable<GrupoResponse> {
    return this.http.post<GrupoResponse>(`${this.baseUrl}/${id}/revalidar`, {});
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
