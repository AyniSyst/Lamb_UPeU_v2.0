import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../core/auth/auth-service';
import { MatriculaService } from './matricula-service';
import { MatriculaResponse } from './matricula.model';

@Component({
  selector: 'app-matricula-list',
  templateUrl: './matricula-list.html',
})
export class MatriculaList {
  private readonly service = inject(MatriculaService);
  protected readonly auth = inject(AuthService);

  protected readonly matriculas = signal<MatriculaResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.matriculas.set(data),
      error: () => this.error.set('No se pudieron cargar las matriculas.'),
    });
  }

  protected confirmar(matricula: MatriculaResponse): void {
    this.errorAccion.set(null);
    this.service.confirmar(matricula.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo confirmar la matricula.'),
    });
  }

  protected anular(matricula: MatriculaResponse): void {
    this.errorAccion.set(null);
    this.service.anular(matricula.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo anular la matricula.'),
    });
  }
}
