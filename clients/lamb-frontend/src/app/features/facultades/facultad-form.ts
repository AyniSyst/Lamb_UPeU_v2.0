import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FacultadService } from './facultad-service';

@Component({
  selector: 'app-facultad-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './facultad-form.html',
})
export class FacultadForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(FacultadService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected idFacultad: number | null = null;

  protected readonly form = this.fb.nonNullable.group({
    nombre: ['', [Validators.required, Validators.maxLength(100)]],
    decano: ['', [Validators.maxLength(150)]],
  });

  constructor() {
    const parametro = this.route.snapshot.paramMap.get('id');
    if (parametro) {
      this.idFacultad = Number(parametro);
      this.service.obtener(this.idFacultad).subscribe({
        next: (facultad) => this.form.patchValue({ nombre: facultad.nombre, decano: facultad.decano ?? '' }),
        error: (e) => this.error.set(mensajeError(e, 'No se pudo cargar la facultad.')),
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
    const request = { nombre: valores.nombre, decano: valores.decano || null };
    const peticion = this.idFacultad
      ? this.service.actualizar(this.idFacultad, request)
      : this.service.crear(request);
    peticion.subscribe({
      next: () => this.router.navigate(['/facultades']),
      error: (e) => {
        this.error.set(mensajeError(e, 'No se pudo guardar la facultad.'));
        this.enviando.set(false);
      },
    });
  }
}
