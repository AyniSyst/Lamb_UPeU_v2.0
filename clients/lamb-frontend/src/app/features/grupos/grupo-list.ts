import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth-service';
import { GrupoService } from './grupo-service';
import { GrupoResponse } from './grupo.model';

@Component({
  selector: 'app-grupo-list',
  imports: [RouterLink],
  templateUrl: './grupo-list.html',
})
export class GrupoList {
  private readonly service = inject(GrupoService);
  protected readonly auth = inject(AuthService);

  protected readonly grupos = signal<GrupoResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.grupos.set(data),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar los grupos.')),
    });
  }

  protected revalidar(grupo: GrupoResponse): void {
    this.errorAccion.set(null);
    this.service.revalidar(grupo.id).subscribe({
      next: () => this.cargar(),
      error: (e) => this.errorAccion.set(mensajeError(e, 'No se pudo revalidar el grupo.')),
    });
  }

  protected eliminar(grupo: GrupoResponse): void {
    if (!confirm(`Eliminar el grupo ${grupo.codigo} de ${grupo.curso?.nombre}?`)) {
      return;
    }
    this.errorAccion.set(null);
    this.service.eliminar(grupo.id).subscribe({
      next: () => this.cargar(),
      error: (e) => this.errorAccion.set(mensajeError(e, 'No se pudo eliminar el grupo.')),
    });
  }
}
