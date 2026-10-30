import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth-service';
import { ProgramaService } from './programa-service';
import { ProgramaResponse } from './programa.model';

@Component({
  selector: 'app-programa-list',
  imports: [RouterLink],
  templateUrl: './programa-list.html',
})
export class ProgramaList {
  private readonly service = inject(ProgramaService);
  protected readonly auth = inject(AuthService);

  protected readonly programas = signal<ProgramaResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.programas.set(data),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar los programas.')),
    });
  }

  protected eliminar(programa: ProgramaResponse): void {
    this.errorAccion.set(null);
    this.service.eliminar(programa.id).subscribe({
      next: () => this.cargar(),
      error: (e) => this.errorAccion.set(mensajeError(e, 'No se pudo eliminar el programa.')),
    });
  }
}
