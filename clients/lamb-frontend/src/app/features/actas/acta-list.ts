import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../core/auth/auth-service';
import { ActaService } from './acta-service';
import { Acta } from './acta.model';

@Component({
  selector: 'app-acta-list',
  templateUrl: './acta-list.html',
})
export class ActaList {
  private readonly service = inject(ActaService);
  protected readonly auth = inject(AuthService);

  protected readonly actas = signal<Acta[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.actas.set(data),
      error: () => this.error.set('No se pudieron cargar las actas.'),
    });
  }

  protected cerrar(acta: Acta): void {
    this.errorAccion.set(null);
    this.service.cerrar(acta.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo cerrar el acta.'),
    });
  }
}
