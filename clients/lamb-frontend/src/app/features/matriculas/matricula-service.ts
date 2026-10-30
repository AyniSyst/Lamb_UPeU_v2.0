import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { MatriculaRequest, MatriculaResponse } from './matricula.model';

@Injectable({ providedIn: 'root' })
export class MatriculaService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/matriculas`;

  listar(): Observable<MatriculaResponse[]> {
    return this.http.get<MatriculaResponse[]>(this.baseUrl);
  }

  obtener(id: number): Observable<MatriculaResponse> {
    return this.http.get<MatriculaResponse>(`${this.baseUrl}/${id}`);
  }

  crear(request: MatriculaRequest): Observable<MatriculaResponse> {
    return this.http.post<MatriculaResponse>(this.baseUrl, request);
  }

  confirmar(id: number): Observable<MatriculaResponse> {
    return this.http.post<MatriculaResponse>(`${this.baseUrl}/${id}/confirmar`, {});
  }

  anular(id: number): Observable<MatriculaResponse> {
    return this.http.post<MatriculaResponse>(`${this.baseUrl}/${id}/anular`, {});
  }
}
