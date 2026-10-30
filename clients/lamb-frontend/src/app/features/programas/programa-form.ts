import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FacultadService } from '../facultades/facultad-service';
import { FacultadResponse } from '../facultades/facultad.model';
import { ProgramaService } from './programa-service';
import { GradoAcademico } from './programa.model';

@Component({
  selector: 'app-programa-form',
  imports: [ReactiveFormsModule],
  templateUrl: './programa-form.html',
})
export class ProgramaForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(ProgramaService);
  private readonly facultadService = inject(FacultadService);
  private readonly router = inject(Router);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly facultades = signal<FacultadResponse[]>([]);
  protected readonly grados: GradoAcademico[] = [
    'BACHILLER',
    'LICENCIATURA',
    'MAESTRIA',
    'DOCTORADO',
  ];

  protected readonly form = this.fb.nonNullable.group({
    nombre: ['', [Validators.required, Validators.maxLength(150)]],
    gradoAcademico: ['LICENCIATURA' as GradoAcademico, [Validators.required]],
    duracionSemestres: [1, [Validators.required, Validators.min(1)]],
    facultadId: [null as number | null, [Validators.required]],
  });

  constructor() {
    this.facultadService.listar().subscribe({
      next: (data) => this.facultades.set(data),
      error: () => this.error.set('No se pudieron cargar las facultades.'),
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
      .crear({
        nombre: valores.nombre,
        gradoAcademico: valores.gradoAcademico,
        duracionSemestres: valores.duracionSemestres,
        facultadId: valores.facultadId as number,
      })
      .subscribe({
        next: () => this.router.navigate(['/programas']),
        error: () => {
          this.error.set('No se pudo registrar el programa.');
          this.enviando.set(false);
        },
      });
  }
}
