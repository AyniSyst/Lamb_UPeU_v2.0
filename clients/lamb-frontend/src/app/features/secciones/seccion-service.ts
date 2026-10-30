import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { SeccionRequest, SeccionResponse } from './seccion.model';

@Injectable({ providedIn: 'root' })
export class SeccionService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/secciones`;

  listar(): Observable<SeccionResponse[]> {
    return this.http.get<SeccionResponse[]>(this.baseUrl);
  }

  obtener(id: number): Observable<SeccionResponse> {
    return this.http.get<SeccionResponse>(`${this.baseUrl}/${id}`);
  }

  crear(request: SeccionRequest): Observable<SeccionResponse> {
    return this.http.post<SeccionResponse>(this.baseUrl, request);
  }

  actualizar(id: number, request: SeccionRequest): Observable<SeccionResponse> {
    return this.http.put<SeccionResponse>(`${this.baseUrl}/${id}`, request);
  }

  revalidar(id: number): Observable<SeccionResponse> {
    return this.http.post<SeccionResponse>(`${this.baseUrl}/${id}/revalidar`, {});
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
