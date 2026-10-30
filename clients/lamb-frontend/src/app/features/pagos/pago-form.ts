import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PagoService } from './pago-service';
import { MetodoPago } from './pago.model';

@Component({
  selector: 'app-pago-form',
  imports: [ReactiveFormsModule],
  templateUrl: './pago-form.html',
})
export class PagoForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(PagoService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly metodos: MetodoPago[] = ['TARJETA', 'YAPE', 'PLIN', 'TRANSFERENCIA', 'EFECTIVO'];

  protected readonly form = this.fb.nonNullable.group({
    idPersona: [null as number | null, [Validators.required]],
    concepto: ['', [Validators.required, Validators.maxLength(150)]],
    monto: [null as number | null, [Validators.required, Validators.min(0.01)]],
    metodo: ['' as MetodoPago | '', [Validators.required]],
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
      .procesar({
        idPersona: valores.idPersona as number,
        concepto: valores.concepto,
        monto: valores.monto as number,
        metodo: valores.metodo as MetodoPago,
      })
      .subscribe({
        next: () => this.router.navigate(['/pagos']),
        error: () => {
          this.error.set('No se pudo procesar el pago.');
          this.enviando.set(false);
        },
      });
  }
}
