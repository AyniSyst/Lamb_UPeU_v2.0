import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatriculaService } from './matricula-service';

@Component({
  selector: 'app-matricula-form',
  imports: [ReactiveFormsModule],
  templateUrl: './matricula-form.html',
})
export class MatriculaForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(MatriculaService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);

  protected readonly form = this.fb.nonNullable.group({
    idPersona: [null as number | null, [Validators.required]],
    periodo: ['', [Validators.required, Validators.maxLength(20)]],
    detalles: this.fb.nonNullable.array([this.crearDetalle()]),
  });

  protected get detalles() {
    return this.form.controls.detalles;
  }

  private crearDetalle() {
    return this.fb.nonNullable.group({
      idSeccion: [null as number | null, [Validators.required]],
      creditos: [null as number | null, [Validators.required, Validators.min(1)]],
    });
  }

  protected agregarDetalle(): void {
    this.detalles.push(this.crearDetalle());
  }

  protected quitarDetalle(index: number): void {
    this.detalles.removeAt(index);
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
        idPersona: valores.idPersona as number,
        periodo: valores.periodo,
        detalles: valores.detalles.map((detalle) => ({
          idSeccion: detalle.idSeccion as number,
          creditos: detalle.creditos as number,
        })),
      })
      .subscribe({
        next: () => this.router.navigate(['/matriculas']),
        error: () => {
          this.error.set('No se pudo registrar la matricula.');
          this.enviando.set(false);
        },
      });
  }
}
