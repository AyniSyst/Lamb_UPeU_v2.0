import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../core/auth/auth-service';
import { NotaService } from './nota-service';
import { NotaResponse } from './nota.model';

@Component({
  selector: 'app-nota-list',
  templateUrl: './nota-list.html',
})
export class NotaList {
  private readonly service = inject(NotaService);
  protected readonly auth = inject(AuthService);

  protected readonly notas = signal<NotaResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.notas.set(data),
      error: () => this.error.set('No se pudieron cargar las notas.'),
    });
  }

  protected eliminar(nota: NotaResponse): void {
    this.errorAccion.set(null);
    this.service.eliminar(nota.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo eliminar la nota (el acta puede estar cerrada).'),
    });
  }
}
