import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SeccionService } from './seccion-service';

@Component({
  selector: 'app-seccion-form',
  imports: [ReactiveFormsModule],
  templateUrl: './seccion-form.html',
})
export class SeccionForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(SeccionService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);

  protected readonly form = this.fb.nonNullable.group({
    cursoId: [null as number | null, [Validators.required]],
    periodo: ['', [Validators.required, Validators.maxLength(20)]],
    docente: ['', [Validators.maxLength(150)]],
    cupoMaximo: [null as number | null, [Validators.required, Validators.min(1)]],
    horario: ['', [Validators.maxLength(100)]],
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
        cursoId: valores.cursoId as number,
        periodo: valores.periodo,
        docente: valores.docente || undefined,
        cupoMaximo: valores.cupoMaximo as number,
        horario: valores.horario || undefined,
      })
      .subscribe({
        next: () => this.router.navigate(['/secciones']),
        error: () => {
          this.error.set('No se pudo registrar la seccion.');
          this.enviando.set(false);
        },
      });
  }
}
