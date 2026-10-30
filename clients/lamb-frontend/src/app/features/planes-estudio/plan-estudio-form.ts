import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProgramaService } from '../programas/programa-service';
import { ProgramaResponse } from '../programas/programa.model';
import { PlanEstudioService } from './plan-estudio-service';

@Component({
  selector: 'app-plan-estudio-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './plan-estudio-form.html',
})
export class PlanEstudioForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(PlanEstudioService);
  private readonly programaService = inject(ProgramaService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly programas = signal<ProgramaResponse[]>([]);
  protected idPlan: number | null = null;

  protected readonly form = this.fb.nonNullable.group({
    idPrograma: [null as number | null, [Validators.required]],
    version: ['', [Validators.required, Validators.maxLength(20)]],
    nombre: ['', [Validators.required, Validators.maxLength(150)]],
    idModalidad: [null as number | null],
  });

  constructor() {
    this.programaService.listar().subscribe({
      next: (p) => this.programas.set(p),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar los programas.')),
    });
    const parametro = this.route.snapshot.paramMap.get('id');
    if (parametro) {
      this.idPlan = Number(parametro);
      this.service.obtener(this.idPlan).subscribe({
        next: (plan) => this.form.patchValue(plan),
        error: (e) => this.error.set(mensajeError(e, 'No se pudo cargar el plan de estudio.')),
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
      idPrograma: Number(valores.idPrograma),
      version: valores.version,
      nombre: valores.nombre,
      idModalidad: valores.idModalidad ? Number(valores.idModalidad) : null,
    };
    const peticion = this.idPlan ? this.service.actualizar(this.idPlan, request) : this.service.crear(request);
    peticion.subscribe({
      // Tras crear, se va al detalle para cargar los cursos del plan
      next: (plan) => this.router.navigate(['/planes-estudio', plan.id]),
      error: (e) => {
        this.error.set(mensajeError(e, 'No se pudo guardar el plan de estudio.'));
        this.enviando.set(false);
      },
    });
  }
}
