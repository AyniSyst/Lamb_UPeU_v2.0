import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotaService } from './nota-service';

@Component({
  selector: 'app-nota-form',
  imports: [ReactiveFormsModule],
  templateUrl: './nota-form.html',
})
export class NotaForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(NotaService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);

  protected readonly form = this.fb.nonNullable.group({
    idPersona: [null as number | null, [Validators.required]],
    idGrupo: [null as number | null, [Validators.required]],
    idComponente: [null as number | null, [Validators.required]],
    calificacion: [null as number | null, [Validators.required, Validators.min(0), Validators.max(20)]],
    periodo: ['', [Validators.required, Validators.maxLength(20)]],
  });

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
        idPersona: valores.idPersona as number,
        idGrupo: valores.idGrupo as number,
        idComponente: valores.idComponente as number,
        calificacion: valores.calificacion as number,
        periodo: valores.periodo,
      })
      .subscribe({
        next: () => this.router.navigate(['/notas']),
        error: () => {
          this.error.set('No se pudo registrar la nota (verifica que el acta no este cerrada).');
          this.enviando.set(false);
        },
      });
  }
}
