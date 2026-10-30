import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PlanEstudioService } from './plan-estudio-service';

@Component({
  selector: 'app-plan-estudio-form',
  imports: [ReactiveFormsModule],
  templateUrl: './plan-estudio-form.html',
})
export class PlanEstudioForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(PlanEstudioService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);

  protected readonly form = this.fb.nonNullable.group({
    idPrograma: [null as number | null, [Validators.required]],
    version: ['', [Validators.required, Validators.maxLength(20)]],
    nombre: ['', [Validators.required, Validators.maxLength(150)]],
    idModalidad: [null as number | null],
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
        idPrograma: valores.idPrograma as number,
        version: valores.version,
        nombre: valores.nombre,
        idModalidad: valores.idModalidad,
      })
      .subscribe({
        next: () => this.router.navigate(['/planes-estudio']),
        error: () => {
          this.error.set('No se pudo registrar el plan de estudio.');
          this.enviando.set(false);
        },
      });
  }
}
