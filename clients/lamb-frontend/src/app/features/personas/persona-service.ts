import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { PersonaRequest, PersonaResponse } from './persona.model';

@Injectable({ providedIn: 'root' })
export class PersonaService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/persona/personas`;

  listar(): Observable<PersonaResponse[]> {
    return this.http.get<PersonaResponse[]>(this.baseUrl);
  }

  obtener(id: number): Observable<PersonaResponse> {
    return this.http.get<PersonaResponse>(`${this.baseUrl}/${id}`);
  }

  crear(request: PersonaRequest): Observable<PersonaResponse> {
    return this.http.post<PersonaResponse>(this.baseUrl, request);
  }

  actualizar(id: number, request: PersonaRequest): Observable<PersonaResponse> {
    return this.http.put<PersonaResponse>(`${this.baseUrl}/${id}`, request);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
