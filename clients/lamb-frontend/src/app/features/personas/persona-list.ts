import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth-service';
import { PersonaService } from './persona-service';
import { PersonaResponse } from './persona.model';

@Component({
  selector: 'app-persona-list',
  imports: [RouterLink],
  templateUrl: './persona-list.html',
})
export class PersonaList {
  private readonly service = inject(PersonaService);
  private readonly router = inject(Router);
  protected readonly auth = inject(AuthService);

  protected readonly personas = signal<PersonaResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.personas.set(data),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar las personas.')),
    });
  }

  protected editar(persona: PersonaResponse): void {
    this.router.navigate(['/personas', persona.id, 'editar']);
  }

  protected eliminar(persona: PersonaResponse): void {
    if (!confirm(`Eliminar a ${persona.nombres} ${persona.apellidos}?`)) {
      return;
    }
    this.errorAccion.set(null);
    this.service.eliminar(persona.id).subscribe({
      next: () => this.cargar(),
      error: (e) => this.errorAccion.set(mensajeError(e, 'No se pudo eliminar la persona.')),
    });
  }
}
