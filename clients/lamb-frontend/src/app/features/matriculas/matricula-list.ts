import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../core/auth/auth-service';
import { GrupoService } from '../grupos/grupo-service';
import { GrupoResponse } from '../grupos/grupo.model';
import { MatriculaService } from './matricula-service';
import { MatriculaResponse } from './matricula.model';

@Component({
  selector: 'app-matricula-list',
  imports: [RouterLink],
  templateUrl: './matricula-list.html',
})
export class MatriculaList {
  private readonly service = inject(MatriculaService);
  private readonly grupoService = inject(GrupoService);
  protected readonly auth = inject(AuthService);

  protected readonly matriculas = signal<MatriculaResponse[]>([]);
  protected readonly grupos = signal<Map<number, GrupoResponse>>(new Map());
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);
  protected readonly exito = signal<string | null>(null);

  constructor() {
    this.cargar();
    this.grupoService.listar().subscribe({ next: (g) => this.grupos.set(new Map(g.map((x) => [x.id, x]))) });
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.matriculas.set(data),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar las matriculas.')),
    });
  }

  protected nombreGrupo(id: number): string {
    const g = this.grupos().get(id);
    return g ? `${g.curso?.codigo} ${g.curso?.nombre} (${g.codigo})` : `Grupo ${id}`;
  }

  protected confirmar(matricula: MatriculaResponse): void {
    this.accion(this.service.confirmar(matricula.id), 'Matricula confirmada: Finanzas genera el cobro pendiente.', 'No se pudo confirmar la matricula.');
  }

  protected anular(matricula: MatriculaResponse): void {
    if (!confirm(`Anular la matricula ${matricula.id}?`)) {
      return;
    }
    this.accion(this.service.anular(matricula.id), 'Matricula anulada.', 'No se pudo anular la matricula.');
  }

  protected eliminar(matricula: MatriculaResponse): void {
    if (!confirm(`Eliminar la matricula ${matricula.id}? Solo se puede mientras esta REGISTRADA.`)) {
      return;
    }
    this.accion(this.service.eliminar(matricula.id), 'Matricula eliminada.', 'No se pudo eliminar la matricula.');
  }

  private accion(peticion: Observable<unknown>, ok: string, fallo: string): void {
    this.errorAccion.set(null);
    this.exito.set(null);
    peticion.subscribe({
      next: () => {
        this.exito.set(ok);
        this.cargar();
      },
      error: (e) => this.errorAccion.set(mensajeError(e, fallo)),
    });
  }
}
