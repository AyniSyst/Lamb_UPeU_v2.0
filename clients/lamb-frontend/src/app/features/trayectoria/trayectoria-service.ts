import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { TrayectoriaResponse } from './trayectoria.model';

@Injectable({ providedIn: 'root' })
export class TrayectoriaService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/api/v1/trayectorias`;

  obtenerMia(): Observable<TrayectoriaResponse> {
    return this.http.get<TrayectoriaResponse>(`${this.baseUrl}/me`);
  }
}
