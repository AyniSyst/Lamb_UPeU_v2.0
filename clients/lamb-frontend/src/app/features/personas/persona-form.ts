import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonaService } from './persona-service';
import { TipoPersona } from './persona.model';

@Component({
  selector: 'app-persona-form',
  imports: [ReactiveFormsModule],
  templateUrl: './persona-form.html',
})
export class PersonaForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(PersonaService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  private idPersona: number | null = null;

  protected readonly form = this.fb.nonNullable.group({
    tipoDocumento: ['', [Validators.required, Validators.maxLength(20)]],
    numeroDocumento: ['', [Validators.required, Validators.maxLength(20)]],
    nombres: ['', [Validators.required, Validators.maxLength(100)]],
    apellidos: ['', [Validators.required, Validators.maxLength(100)]],
    email: ['', [Validators.required, Validators.email, Validators.maxLength(150)]],
    telefono: ['', [Validators.maxLength(20)]],
    fechaNacimiento: [''],
    tipo: ['ESTUDIANTE' as TipoPersona, [Validators.required]],
  });

  constructor() {
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
        error: () => this.error.set('No se pudo cargar la persona.'),
      });
    }
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
      telefono: valores.telefono || null,
      fechaNacimiento: valores.fechaNacimiento || null,
    };
    const peticion = this.idPersona
      ? this.service.actualizar(this.idPersona, request)
      : this.service.crear(request);
    peticion.subscribe({
      next: () => this.router.navigate(['/personas']),
      error: () => {
        this.error.set('No se pudo guardar la persona.');
        this.enviando.set(false);
      },
    });
  }
}
