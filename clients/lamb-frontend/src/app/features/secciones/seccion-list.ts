import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../core/auth/auth-service';
import { SeccionService } from './seccion-service';
import { SeccionResponse } from './seccion.model';

@Component({
  selector: 'app-seccion-list',
  templateUrl: './seccion-list.html',
})
export class SeccionList {
  private readonly service = inject(SeccionService);
  protected readonly auth = inject(AuthService);

  protected readonly secciones = signal<SeccionResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.secciones.set(data),
      error: () => this.error.set('No se pudieron cargar las secciones.'),
    });
  }

  protected revalidar(seccion: SeccionResponse): void {
    this.errorAccion.set(null);
    this.service.revalidar(seccion.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo revalidar la seccion.'),
    });
  }

  protected eliminar(seccion: SeccionResponse): void {
    this.errorAccion.set(null);
    this.service.eliminar(seccion.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo eliminar la seccion.'),
    });
  }
}
