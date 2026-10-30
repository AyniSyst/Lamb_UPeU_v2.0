import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PostulacionService } from './postulacion-service';

@Component({
  selector: 'app-postulacion-form',
  imports: [ReactiveFormsModule],
  templateUrl: './postulacion-form.html',
})
export class PostulacionForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(PostulacionService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);

  protected readonly form = this.fb.nonNullable.group({
    idPersona: [null as number | null, [Validators.required]],
    idPrograma: [null as number | null, [Validators.required]],
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
        idPrograma: valores.idPrograma as number,
        periodo: valores.periodo,
      })
      .subscribe({
        next: () => this.router.navigate(['/postulaciones']),
        error: () => {
          this.error.set('No se pudo registrar la postulacion.');
          this.enviando.set(false);
        },
      });
  }
}
