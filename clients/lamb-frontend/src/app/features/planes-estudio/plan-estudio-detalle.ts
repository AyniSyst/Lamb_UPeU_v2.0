import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../core/auth/auth-service';
import { CatalogosService, CursoCatalogo, ItemCatalogo } from '../../core/catalogos/catalogos-service';
import { PlanEstudioService } from './plan-estudio-service';
import { PlanCursoResponse, PlanEstudioResponse } from './plan-estudio.model';

@Component({
  selector: 'app-plan-estudio-detalle',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './plan-estudio-detalle.html',
})
export class PlanEstudioDetalle {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(PlanEstudioService);
  private readonly catalogos = inject(CatalogosService);
  private readonly route = inject(ActivatedRoute);
  protected readonly auth = inject(AuthService);

  protected readonly plan = signal<PlanEstudioResponse | null>(null);
  protected readonly cursosCatalogo = signal<CursoCatalogo[]>([]);
  protected readonly tiposCurso = signal<ItemCatalogo[]>([]);
  protected readonly areas = signal<ItemCatalogo[]>([]);
  protected readonly creandoCurso = signal(false);
  protected readonly error = signal<string | null>(null);
  protected readonly exito = signal<string | null>(null);
  private readonly idPlan = Number(this.route.snapshot.paramMap.get('id'));

  protected readonly cursoForm = this.fb.nonNullable.group({
    idCursoCatalogo: [null as number | null, [Validators.required]],
    ciclo: [1, [Validators.required, Validators.min(1), Validators.max(14)]],
    idTipoCurso: [null as number | null, [Validators.required]],
    idAreaCurricular: [null as number | null],
  });

  protected readonly catalogoForm = this.fb.nonNullable.group({
    codigo: ['', [Validators.required, Validators.maxLength(20)]],
    nombre: ['', [Validators.required, Validators.maxLength(150)]],
    creditos: [4, [Validators.required, Validators.min(1), Validators.max(10)]],
  });

  constructor() {
    this.cargar();
    this.cargarCatalogo();
    this.catalogos.tiposCurso().subscribe((t) => {
      this.tiposCurso.set(t);
      if (t.length) {
        this.cursoForm.controls.idTipoCurso.setValue(t[0].id);
      }
    });
    this.catalogos.areasCurriculares().subscribe((a) => this.areas.set(a));
  }

  protected cargar(): void {
    this.service.obtener(this.idPlan).subscribe({
      next: (p) => this.plan.set(p),
      error: (e) => this.error.set(mensajeError(e, 'No se pudo cargar el plan de estudio.')),
    });
  }

  private cargarCatalogo(seleccionar?: number): void {
    this.catalogos.cursosCatalogo().subscribe((c) => {
      this.cursosCatalogo.set(c);
      if (seleccionar) {
        this.cursoForm.controls.idCursoCatalogo.setValue(seleccionar);
      }
    });
  }

  protected get editable(): boolean {
    return this.plan()?.estado === 'BORRADOR' && this.auth.tieneRol('ADMIN', 'COORDINADOR');
  }

  protected crearCursoCatalogo(): void {
    if (this.catalogoForm.invalid) {
      this.catalogoForm.markAllAsTouched();
      return;
    }
    const v = this.catalogoForm.getRawValue();
    this.catalogos.crearCursoCatalogo({ ...v, codigo: v.codigo.toUpperCase(), creditos: Number(v.creditos) }).subscribe({
      next: (c) => {
        this.creandoCurso.set(false);
        this.catalogoForm.reset();
        this.cargarCatalogo(c.id);
      },
      error: (e) => this.error.set(mensajeError(e, 'No se pudo crear el curso en el catalogo.')),
    });
  }

  protected agregarCurso(): void {
    if (this.cursoForm.invalid) {
      this.cursoForm.markAllAsTouched();
      return;
    }
    const v = this.cursoForm.getRawValue();
    this.accion(
      this.service.agregarCurso(this.idPlan, {
        idCursoCatalogo: Number(v.idCursoCatalogo),
        ciclo: Number(v.ciclo),
        idTipoCurso: Number(v.idTipoCurso),
        idAreaCurricular: v.idAreaCurricular ? Number(v.idAreaCurricular) : null,
      }),
      'Curso agregado al plan.',
      'No se pudo agregar el curso.',
    );
  }

  protected quitarCurso(curso: PlanCursoResponse): void {
    this.accion(this.service.quitarCurso(this.idPlan, curso.id), 'Curso quitado del plan.', 'No se pudo quitar el curso.');
  }

  protected publicar(): void {
    if (!confirm('Publicar el plan? Queda VIGENTE y ya no se pueden cambiar sus cursos.')) {
      return;
    }
    this.accion(
      this.service.publicar(this.idPlan),
      'Plan publicado: Planificacion recibe sus cursos y ya se pueden ofertar y abrir grupos.',
      'No se pudo publicar el plan.',
    );
  }

  private accion(peticion: Observable<unknown>, ok: string, fallo: string): void {
    this.error.set(null);
    this.exito.set(null);
    peticion.subscribe({
      next: () => {
        this.exito.set(ok);
        this.cargar();
      },
      error: (e) => this.error.set(mensajeError(e, fallo)),
    });
  }
}
