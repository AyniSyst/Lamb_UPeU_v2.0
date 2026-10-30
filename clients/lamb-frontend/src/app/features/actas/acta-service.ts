import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Acta } from './acta.model';

@Injectable({ providedIn: 'root' })
export class ActaService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/actas`;

  listar(): Observable<Acta[]> {
    return this.http.get<Acta[]>(this.baseUrl);
  }

  obtener(id: number): Observable<Acta> {
    return this.http.get<Acta>(`${this.baseUrl}/${id}`);
  }

  obtenerPorGrupo(idGrupo: number): Observable<Acta> {
    return this.http.get<Acta>(`${this.baseUrl}/grupo/${idGrupo}`);
  }

  cerrar(id: number): Observable<Acta> {
    return this.http.post<Acta>(`${this.baseUrl}/${id}/cerrar`, {});
  }
}
