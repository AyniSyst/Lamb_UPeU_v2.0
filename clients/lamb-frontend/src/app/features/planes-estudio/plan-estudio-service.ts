import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { PlanCursoRequest, PlanEstudioRequest, PlanEstudioResponse } from './plan-estudio.model';

@Injectable({ providedIn: 'root' })
export class PlanEstudioService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/planes-estudio`;

  listar(idPrograma?: number): Observable<PlanEstudioResponse[]> {
    const params = idPrograma ? { params: { idPrograma } } : {};
    return this.http.get<PlanEstudioResponse[]>(this.baseUrl, params);
  }

  obtener(id: number): Observable<PlanEstudioResponse> {
    return this.http.get<PlanEstudioResponse>(`${this.baseUrl}/${id}`);
  }

  crear(request: PlanEstudioRequest): Observable<PlanEstudioResponse> {
    return this.http.post<PlanEstudioResponse>(this.baseUrl, request);
  }

  actualizar(id: number, request: PlanEstudioRequest): Observable<PlanEstudioResponse> {
    return this.http.put<PlanEstudioResponse>(`${this.baseUrl}/${id}`, request);
  }

  agregarCurso(id: number, request: PlanCursoRequest): Observable<unknown> {
    return this.http.post(`${this.baseUrl}/${id}/cursos`, request);
  }

  quitarCurso(id: number, idPlanCurso: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}/cursos/${idPlanCurso}`);
  }

  // Publicar envia plan.publicado: planificacion copia los cursos para poder abrir grupos
  publicar(id: number): Observable<PlanEstudioResponse> {
    return this.http.post<PlanEstudioResponse>(`${this.baseUrl}/${id}/publicar`, {});
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
