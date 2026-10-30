import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { CursoResponse } from './curso.model';

@Injectable({ providedIn: 'root' })
export class CursoService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/cursos`;

  listar(): Observable<CursoResponse[]> {
    return this.http.get<CursoResponse[]>(this.baseUrl);
  }

  obtener(id: number): Observable<CursoResponse> {
    return this.http.get<CursoResponse>(`${this.baseUrl}/${id}`);
  }
}
