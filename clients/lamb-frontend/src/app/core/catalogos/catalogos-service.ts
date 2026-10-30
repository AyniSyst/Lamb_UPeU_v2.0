import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { environment } from '../../../environments/environment';

// Elementos de catalogo para listas desplegables (rutas /ms/<servicio>/... del Gateway)
export interface OfertaAcademica {
  id: number;
  idPeriodo: number;
  codigoPeriodo: string;
  idPrograma: number | null;
  idCurso: number;
  codigoCurso: string;
  ciclo: number;
  estado: string;
}

export interface Ambiente {
  id: number;
  codigo: string;
  nombre: string;
  codigoPabellon: string;
  aforo: number;
  activo: boolean;
}

export interface PeriodoAcademico {
  id: number;
  codigo: string;
  nombre: string;
  estado: string;
}

export interface CursoCatalogo {
  id: number;
  codigo: string;
  nombre: string;
  creditos: number;
}

export interface ItemCatalogo {
  id: number;
  codigo: string;
  nombre: string;
}

export interface ComponenteEvaluacion {
  id: number;
  codigo: string;
  nombre: string;
  tipo: string;
}

@Injectable({ providedIn: 'root' })
export class CatalogosService {
  private readonly http = inject(HttpClient);
  private readonly gw = environment.apiBaseUrl;

  ofertas(): Observable<OfertaAcademica[]> {
    return this.lista(`${this.gw}/ms/planificacion/api/v1/ofertas-academicas`);
  }

  crearOferta(request: { idPeriodo: number; idPrograma: number; idCurso: number; ciclo: number; estado: string }): Observable<OfertaAcademica> {
    return this.http.post<OfertaAcademica>(`${this.gw}/ms/planificacion/api/v1/ofertas-academicas`, request);
  }

  ambientes(): Observable<Ambiente[]> {
    return this.lista(`${this.gw}/ms/planificacion/api/v1/ambientes`);
  }

  periodos(): Observable<PeriodoAcademico[]> {
    return this.lista(`${this.gw}/ms/planificacion/api/v1/periodos-academicos`);
  }

  cursosCatalogo(): Observable<CursoCatalogo[]> {
    return this.lista(`${this.gw}/ms/curriculo/api/v1/cursos-catalogo`);
  }

  crearCursoCatalogo(request: { codigo: string; nombre: string; creditos: number }): Observable<CursoCatalogo> {
    return this.http.post<CursoCatalogo>(`${this.gw}/ms/curriculo/api/v1/cursos-catalogo`, request);
  }

  tiposCurso(): Observable<ItemCatalogo[]> {
    return this.lista(`${this.gw}/ms/curriculo/api/v1/tipos-curso`);
  }

  areasCurriculares(): Observable<ItemCatalogo[]> {
    return this.lista(`${this.gw}/ms/curriculo/api/v1/areas-curriculares`);
  }

  componentesEvaluacion(): Observable<ComponenteEvaluacion[]> {
    return this.lista(`${this.gw}/ms/evaluacion/api/v1/componentes-evaluacion`);
  }

  crearComponenteEvaluacion(request: { codigo: string; nombre: string; tipo: string }): Observable<ComponenteEvaluacion> {
    return this.http.post<ComponenteEvaluacion>(`${this.gw}/ms/evaluacion/api/v1/componentes-evaluacion`, request);
  }

  // Un catalogo que no carga no debe romper el formulario: queda vacio
  private lista<T>(url: string): Observable<T[]> {
    return this.http.get<T[]>(url).pipe(catchError(() => of([] as T[])));
  }
}
