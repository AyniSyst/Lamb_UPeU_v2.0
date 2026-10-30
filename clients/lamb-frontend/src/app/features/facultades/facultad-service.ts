import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { FacultadRequest, FacultadResponse } from './facultad.model';

@Injectable({ providedIn: 'root' })
export class FacultadService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/facultades`;

  listar(): Observable<FacultadResponse[]> {
    return this.http.get<FacultadResponse[]>(this.baseUrl);
  }

  obtener(id: number): Observable<FacultadResponse> {
    return this.http.get<FacultadResponse>(`${this.baseUrl}/${id}`);
  }

  crear(request: FacultadRequest): Observable<FacultadResponse> {
    return this.http.post<FacultadResponse>(this.baseUrl, request);
  }

  actualizar(id: number, request: FacultadRequest): Observable<FacultadResponse> {
    return this.http.put<FacultadResponse>(`${this.baseUrl}/${id}`, request);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
