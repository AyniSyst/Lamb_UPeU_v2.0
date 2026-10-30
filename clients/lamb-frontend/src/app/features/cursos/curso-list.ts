import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { CursoService } from './curso-service';
import { CursoResponse } from './curso.model';

@Component({
  selector: 'app-curso-list',
  templateUrl: './curso-list.html',
})
export class CursoList {
  private readonly service = inject(CursoService);

  protected readonly cursos = signal<CursoResponse[]>([]);
  protected readonly error = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.cursos.set(data),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar los cursos.')),
    });
  }
}
