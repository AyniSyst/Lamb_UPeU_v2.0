import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FacultadService } from '../facultades/facultad-service';
import { FacultadResponse } from '../facultades/facultad.model';
import { ProgramaService } from './programa-service';
import { GradoAcademico } from './programa.model';

@Component({
  selector: 'app-programa-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './programa-form.html',
})
export class ProgramaForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(ProgramaService);
  private readonly facultadService = inject(FacultadService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly facultades = signal<FacultadResponse[]>([]);
  protected readonly grados: GradoAcademico[] = ['BACHILLER', 'LICENCIATURA', 'MAESTRIA', 'DOCTORADO'];
  protected idPrograma: number | null = null;

  protected readonly form = this.fb.nonNullable.group({
    nombre: ['', [Validators.required, Validators.maxLength(150)]],
    gradoAcademico: ['LICENCIATURA' as GradoAcademico, [Validators.required]],
    duracionSemestres: [10, [Validators.required, Validators.min(1), Validators.max(20)]],
    facultadId: [null as number | null, [Validators.required]],
  });

  constructor() {
    this.facultadService.listar().subscribe({
      next: (data) => this.facultades.set(data),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar las facultades.')),
    });
    const parametro = this.route.snapshot.paramMap.get('id');
    if (parametro) {
      this.idPrograma = Number(parametro);
      this.service.obtener(this.idPrograma).subscribe({
        next: (programa) =>
          this.form.patchValue({
            nombre: programa.nombre,
            gradoAcademico: programa.gradoAcademico,
            duracionSemestres: programa.duracionSemestres,
            facultadId: programa.facultad.id,
          }),
        error: (e) => this.error.set(mensajeError(e, 'No se pudo cargar el programa.')),
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
      nombre: valores.nombre,
      gradoAcademico: valores.gradoAcademico,
      duracionSemestres: Number(valores.duracionSemestres),
      facultadId: Number(valores.facultadId),
    };
    const peticion = this.idPrograma
      ? this.service.actualizar(this.idPrograma, request)
      : this.service.crear(request);
    peticion.subscribe({
      next: () => this.router.navigate(['/programas']),
      error: (e) => {
        this.error.set(mensajeError(e, 'No se pudo guardar el programa.'));
        this.enviando.set(false);
      },
    });
  }
}
