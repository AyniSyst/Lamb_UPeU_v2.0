import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CatalogosService, ComponenteEvaluacion } from '../../core/catalogos/catalogos-service';
import { GrupoService } from '../grupos/grupo-service';
import { GrupoResponse } from '../grupos/grupo.model';
import { PersonaService } from '../personas/persona-service';
import { PersonaResponse } from '../personas/persona.model';
import { NotaService } from './nota-service';

@Component({
  selector: 'app-nota-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './nota-form.html',
})
export class NotaForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(NotaService);
  private readonly catalogos = inject(CatalogosService);
  private readonly grupoService = inject(GrupoService);
  private readonly personaService = inject(PersonaService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly grupos = signal<GrupoResponse[]>([]);
  protected readonly estudiantes = signal<PersonaResponse[]>([]);
  protected readonly componentes = signal<ComponenteEvaluacion[]>([]);
  protected readonly creandoComponente = signal(false);
  protected readonly tiposComponente = ['EXAMEN', 'EVALUACION_PRODUCTO', 'EVALUACION_SESIONES'];
  protected idNota: number | null = null;

  protected readonly form = this.fb.nonNullable.group({
    idGrupo: [null as number | null, [Validators.required]],
    idPersona: [null as number | null, [Validators.required]],
    idComponente: [null as number | null, [Validators.required]],
    calificacion: [null as number | null, [Validators.required, Validators.min(0), Validators.max(20)]],
    periodo: ['', [Validators.required, Validators.maxLength(20)]],
  });

  protected readonly componenteForm = this.fb.nonNullable.group({
    codigo: ['', [Validators.required, Validators.maxLength(20)]],
    nombre: ['', [Validators.required, Validators.maxLength(100)]],
    tipo: ['EXAMEN', [Validators.required]],
  });

  constructor() {
    this.grupoService.listar().subscribe({
      next: (g) => this.grupos.set(g.filter((x) => x.estado === 'ABIERTO')),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar los grupos.')),
    });
    this.personaService.listar().subscribe({ next: (p) => this.estudiantes.set(p.filter((x) => x.tipo === 'ESTUDIANTE')) });
    this.cargarComponentes();

    const parametro = this.route.snapshot.paramMap.get('id');
    if (parametro) {
      this.idNota = Number(parametro);
      this.service.obtener(this.idNota).subscribe({
        next: (nota) => this.form.patchValue(nota),
        error: (e) => this.error.set(mensajeError(e, 'No se pudo cargar la nota.')),
      });
    }
  }

  private cargarComponentes(seleccionar?: number): void {
    this.catalogos.componentesEvaluacion().subscribe((c) => {
      this.componentes.set(c);
      if (seleccionar) {
        this.form.controls.idComponente.setValue(seleccionar);
      }
    });
  }

  // El periodo de la nota es el del grupo
  protected alElegirGrupo(): void {
    const grupo = this.grupos().find((g) => g.id === Number(this.form.controls.idGrupo.value));
    if (grupo) {
      this.form.controls.periodo.setValue(grupo.periodo);
    }
  }

  protected guardarComponente(): void {
    if (this.componenteForm.invalid) {
      this.componenteForm.markAllAsTouched();
      return;
    }
    this.catalogos.crearComponenteEvaluacion(this.componenteForm.getRawValue()).subscribe({
      next: (c) => {
        this.creandoComponente.set(false);
        this.cargarComponentes(c.id);
      },
      error: (e) => this.error.set(mensajeError(e, 'No se pudo crear el componente.')),
    });
  }

  protected guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.error.set(null);
    this.enviando.set(true);
    const v = this.form.getRawValue();
    const request = {
      idPersona: Number(v.idPersona),
      idGrupo: Number(v.idGrupo),
      idComponente: Number(v.idComponente),
      calificacion: Number(v.calificacion),
      periodo: v.periodo,
    };
    const peticion = this.idNota ? this.service.actualizar(this.idNota, request) : this.service.crear(request);
    peticion.subscribe({
      next: () => this.router.navigate(['/notas']),
      error: (e) => {
        this.error.set(mensajeError(e, 'No se pudo guardar la nota (verifica que el acta no este cerrada).'));
        this.enviando.set(false);
      },
    });
  }
}
