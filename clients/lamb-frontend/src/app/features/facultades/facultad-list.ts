import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth-service';
import { FacultadService } from './facultad-service';
import { FacultadResponse } from './facultad.model';

@Component({
  selector: 'app-facultad-list',
  imports: [RouterLink],
  templateUrl: './facultad-list.html',
})
export class FacultadList {
  private readonly service = inject(FacultadService);
  protected readonly auth = inject(AuthService);

  protected readonly facultades = signal<FacultadResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.facultades.set(data),
      error: () => this.error.set('No se pudieron cargar las facultades.'),
    });
  }

  protected eliminar(facultad: FacultadResponse): void {
    this.errorAccion.set(null);
    this.service.eliminar(facultad.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo eliminar la facultad.'),
    });
  }
}
