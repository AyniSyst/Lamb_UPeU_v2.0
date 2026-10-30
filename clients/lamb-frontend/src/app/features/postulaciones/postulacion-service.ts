import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import {
  EvaluarPostulacionRequest,
  PostulacionRequest,
  PostulacionResponse,
} from './postulacion.model';

@Injectable({ providedIn: 'root' })
export class PostulacionService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/postulaciones`;

  listar(): Observable<PostulacionResponse[]> {
    return this.http.get<PostulacionResponse[]>(this.baseUrl);
  }

  obtener(id: number): Observable<PostulacionResponse> {
    return this.http.get<PostulacionResponse>(`${this.baseUrl}/${id}`);
  }

  crear(request: PostulacionRequest): Observable<PostulacionResponse> {
    return this.http.post<PostulacionResponse>(this.baseUrl, request);
  }

  evaluar(id: number, request: EvaluarPostulacionRequest): Observable<PostulacionResponse> {
    return this.http.post<PostulacionResponse>(`${this.baseUrl}/${id}/evaluar`, request);
  }

  admitir(id: number): Observable<PostulacionResponse> {
    return this.http.post<PostulacionResponse>(`${this.baseUrl}/${id}/admitir`, {});
  }

  rechazar(id: number): Observable<PostulacionResponse> {
    return this.http.post<PostulacionResponse>(`${this.baseUrl}/${id}/rechazar`, {});
  }
}
