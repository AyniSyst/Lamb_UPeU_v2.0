import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth-service';
import { GrupoService } from '../grupos/grupo-service';
import { GrupoResponse } from '../grupos/grupo.model';
import { NotaService } from './nota-service';
import { NotaResponse } from './nota.model';

@Component({
  selector: 'app-nota-list',
  imports: [RouterLink],
  templateUrl: './nota-list.html',
})
export class NotaList {
  private readonly service = inject(NotaService);
  private readonly grupoService = inject(GrupoService);
  protected readonly auth = inject(AuthService);

  protected readonly notas = signal<NotaResponse[]>([]);
  protected readonly grupos = signal<GrupoResponse[]>([]);
  protected readonly idGrupo = signal<number | null>(null);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.grupoService.listar().subscribe({ next: (g) => this.grupos.set(g) });
    this.cargar();
  }

  protected filtrar(valor: string): void {
    this.idGrupo.set(valor ? Number(valor) : null);
    this.cargar();
  }

  protected nombreGrupo(id: number): string {
    const g = this.grupos().find((x) => x.id === id);
    return g ? `${g.curso?.codigo} (${g.codigo})` : `Grupo ${id}`;
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar(this.idGrupo() ?? undefined).subscribe({
      next: (data) => this.notas.set(data),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar las notas.')),
    });
  }

  protected eliminar(nota: NotaResponse): void {
    if (!confirm(`Eliminar la nota ${nota.calificacion} (${nota.codigoComponente}) de la persona ${nota.idPersona}?`)) {
      return;
    }
    this.errorAccion.set(null);
    this.service.eliminar(nota.id).subscribe({
      next: () => this.cargar(),
      error: (e) => this.errorAccion.set(mensajeError(e, 'No se pudo eliminar la nota (el acta puede estar cerrada).')),
    });
  }
}
