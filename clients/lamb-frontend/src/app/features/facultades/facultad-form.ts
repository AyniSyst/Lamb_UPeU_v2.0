import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FacultadService } from './facultad-service';

@Component({
  selector: 'app-facultad-form',
  imports: [ReactiveFormsModule],
  templateUrl: './facultad-form.html',
})
export class FacultadForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(FacultadService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);

  protected readonly form = this.fb.nonNullable.group({
    nombre: ['', [Validators.required, Validators.maxLength(100)]],
    decano: ['', [Validators.maxLength(150)]],
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
        nombre: valores.nombre,
        decano: valores.decano || null,
      })
      .subscribe({
        next: () => this.router.navigate(['/facultades']),
        error: () => {
          this.error.set('No se pudo registrar la facultad.');
          this.enviando.set(false);
        },
      });
  }
}
