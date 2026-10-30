import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { PersonaService } from '../personas/persona-service';
import { PersonaResponse } from '../personas/persona.model';
import { PagoService } from './pago-service';
import { MetodoPago } from './pago.model';

@Component({
  selector: 'app-pago-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './pago-form.html',
})
export class PagoForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(PagoService);
  private readonly personaService = inject(PersonaService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly personas = signal<PersonaResponse[]>([]);
  protected readonly metodos: MetodoPago[] = ['TARJETA', 'YAPE', 'PLIN', 'TRANSFERENCIA', 'EFECTIVO'];

  protected readonly form = this.fb.nonNullable.group({
    idPersona: [null as number | null, [Validators.required]],
    concepto: ['', [Validators.required, Validators.maxLength(150)]],
    monto: [null as number | null, [Validators.required, Validators.min(0.01)]],
    metodo: ['YAPE' as MetodoPago, [Validators.required]],
  });

  constructor() {
    this.personaService.listar().subscribe({
      next: (p) => this.personas.set(p),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar las personas.')),
    });
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
      .procesar({
        idPersona: Number(valores.idPersona),
        concepto: valores.concepto,
        monto: Number(valores.monto),
        metodo: valores.metodo,
      })
      .subscribe({
        next: () => this.router.navigate(['/pagos']),
        error: (e) => {
          this.error.set(mensajeError(e, 'No se pudo procesar el pago.'));
          this.enviando.set(false);
        },
      });
  }
}
