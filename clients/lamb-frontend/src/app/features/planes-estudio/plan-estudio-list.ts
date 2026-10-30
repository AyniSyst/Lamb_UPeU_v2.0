import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth-service';
import { PlanEstudioService } from './plan-estudio-service';
import { PlanEstudioResponse } from './plan-estudio.model';

@Component({
  selector: 'app-plan-estudio-list',
  imports: [RouterLink],
  templateUrl: './plan-estudio-list.html',
})
export class PlanEstudioList {
  private readonly service = inject(PlanEstudioService);
  protected readonly auth = inject(AuthService);

  protected readonly planes = signal<PlanEstudioResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.planes.set(data),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar los planes de estudio.')),
    });
  }

  protected publicar(plan: PlanEstudioResponse): void {
    this.errorAccion.set(null);
    this.service.publicar(plan.id).subscribe({
      next: () => this.cargar(),
      error: (e) => this.errorAccion.set(mensajeError(e, 'No se pudo publicar el plan de estudio.')),
    });
  }

  protected eliminar(plan: PlanEstudioResponse): void {
    this.errorAccion.set(null);
    this.service.eliminar(plan.id).subscribe({
      next: () => this.cargar(),
      error: (e) => this.errorAccion.set(mensajeError(e, 'No se pudo eliminar el plan de estudio.')),
    });
  }
}
