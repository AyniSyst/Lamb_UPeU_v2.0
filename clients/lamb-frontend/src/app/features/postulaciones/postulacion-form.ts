import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { PersonaService } from '../personas/persona-service';
import { PersonaResponse } from '../personas/persona.model';
import { ProgramaService } from '../programas/programa-service';
import { ProgramaResponse } from '../programas/programa.model';
import { PostulacionService } from './postulacion-service';

@Component({
  selector: 'app-postulacion-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './postulacion-form.html',
})
export class PostulacionForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(PostulacionService);
  private readonly personaService = inject(PersonaService);
  private readonly programaService = inject(ProgramaService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly personas = signal<PersonaResponse[]>([]);
  protected readonly programas = signal<ProgramaResponse[]>([]);

  protected readonly form = this.fb.nonNullable.group({
    idPersona: [null as number | null, [Validators.required]],
    idPrograma: [null as number | null, [Validators.required]],
    periodo: ['', [Validators.required, Validators.maxLength(20)]],
  });

  constructor() {
    this.personaService.listar().subscribe({
      next: (p) => this.personas.set(p.filter((x) => x.tipo === 'ESTUDIANTE')),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar las personas.')),
    });
    this.programaService.listar().subscribe({ next: (p) => this.programas.set(p) });
  }

  protected guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.error.set(null);
    this.enviando.set(true);
    const valores = this.form.getRawValue();
    this.service
      .crear({
        idPersona: Number(valores.idPersona),
        idPrograma: Number(valores.idPrograma),
        periodo: valores.periodo,
      })
      .subscribe({
        next: () => this.router.navigate(['/postulaciones']),
        error: (e) => {
          this.error.set(mensajeError(e, 'No se pudo registrar la postulacion.'));
          this.enviando.set(false);
        },
      });
  }
}
