import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ProgramaRequest, ProgramaResponse } from './programa.model';

@Injectable({ providedIn: 'root' })
export class ProgramaService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/programas`;

  listar(): Observable<ProgramaResponse[]> {
    return this.http.get<ProgramaResponse[]>(this.baseUrl);
  }

  obtener(id: number): Observable<ProgramaResponse> {
    return this.http.get<ProgramaResponse>(`${this.baseUrl}/${id}`);
  }

  crear(request: ProgramaRequest): Observable<ProgramaResponse> {
    return this.http.post<ProgramaResponse>(this.baseUrl, request);
  }

  actualizar(id: number, request: ProgramaRequest): Observable<ProgramaResponse> {
    return this.http.put<ProgramaResponse>(`${this.baseUrl}/${id}`, request);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
