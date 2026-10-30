import { mensajeError } from '../../core/http/mensaje-error';
import { Component, computed, inject, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { AbstractControl, FormBuilder, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PersonaService } from './persona-service';
import { TIPOS_DOCUMENTO_PERU, TipoDocumentoPeru, TipoPersona } from './persona.model';

@Component({
  selector: 'app-persona-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './persona-form.html',
})
export class PersonaForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(PersonaService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly tiposDocumento = signal<TipoDocumentoPeru[]>(TIPOS_DOCUMENTO_PERU);
  protected idPersona: number | null = null;

  protected readonly form = this.fb.nonNullable.group({
    tipoDocumento: ['DNI', [Validators.required]],
    numeroDocumento: ['', [Validators.required, Validators.maxLength(20), (c: AbstractControl) => this.validarNumero(c)]],
    nombres: ['', [Validators.required, Validators.maxLength(100)]],
    apellidos: ['', [Validators.required, Validators.maxLength(100)]],
    email: ['', [Validators.required, Validators.email, Validators.maxLength(150)]],
    telefono: ['', [Validators.maxLength(20), Validators.pattern(/^\+?\d{6,15}$/)]],
    fechaNacimiento: [''],
    tipo: ['ESTUDIANTE' as TipoPersona, [Validators.required]],
  });

  private readonly tipoElegido = toSignal(this.form.controls.tipoDocumento.valueChanges, {
    initialValue: this.form.controls.tipoDocumento.value,
  });
  protected readonly documentoActual = computed(() =>
    this.tiposDocumento().find((t) => t.codigo === this.tipoElegido()),
  );

  constructor() {
    this.service.tiposDocumento().subscribe({ next: (tipos) => tipos.length && this.tiposDocumento.set(tipos) });
    // Cambiar de tipo vuelve a validar el numero ya escrito
    this.form.controls.tipoDocumento.valueChanges.subscribe(() =>
      this.form.controls.numeroDocumento.updateValueAndValidity(),
    );

    const parametro = this.route.snapshot.paramMap.get('id');
    if (parametro) {
      this.idPersona = Number(parametro);
      this.service.obtener(this.idPersona).subscribe({
        next: (persona) =>
          this.form.patchValue({
            ...persona,
            telefono: persona.telefono ?? '',
            fechaNacimiento: persona.fechaNacimiento ?? '',
          }),
        error: (e) => this.error.set(mensajeError(e, 'No se pudo cargar la persona.')),
      });
    }
  }

  private validarNumero(control: AbstractControl): ValidationErrors | null {
    const tipo = this.tiposDocumento?.().find((t) => t.codigo === this.form?.controls.tipoDocumento.value);
    const numero = String(control.value ?? '').trim();
    if (!tipo || !numero) {
      return null;
    }
    return new RegExp(`^(?:${tipo.patron})$`).test(numero) ? null : { formato: tipo.formato };
  }

  protected guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.error.set(null);
    this.enviando.set(true);
    const valores = this.form.getRawValue();
    const request = {
      ...valores,
      numeroDocumento: valores.numeroDocumento.trim().toUpperCase(),
      telefono: valores.telefono || null,
      fechaNacimiento: valores.fechaNacimiento || null,
    };
    const peticion = this.idPersona
      ? this.service.actualizar(this.idPersona, request)
      : this.service.crear(request);
    peticion.subscribe({
      next: () => this.router.navigate(['/personas']),
      error: (e) => {
        this.error.set(mensajeError(e, 'No se pudo guardar la persona.'));
        this.enviando.set(false);
      },
    });
  }
}
