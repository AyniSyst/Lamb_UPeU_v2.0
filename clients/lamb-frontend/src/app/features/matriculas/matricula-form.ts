import { mensajeError } from '../../core/http/mensaje-error';
import { Component, computed, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth-service';
import { GrupoService } from '../grupos/grupo-service';
import { GrupoResponse } from '../grupos/grupo.model';
import { PersonaService } from '../personas/persona-service';
import { PersonaResponse } from '../personas/persona.model';
import { MatriculaService } from './matricula-service';

@Component({
  selector: 'app-matricula-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './matricula-form.html',
})
export class MatriculaForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(MatriculaService);
  private readonly grupoService = inject(GrupoService);
  private readonly personaService = inject(PersonaService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);
  protected readonly auth = inject(AuthService);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly estudiantes = signal<PersonaResponse[]>([]);
  protected readonly grupos = signal<GrupoResponse[]>([]);
  // Solo grupos ABIERTOS admiten matricula (planificacion los valida de nuevo al registrar)
  protected readonly gruposAbiertos = computed(() => this.grupos().filter((g) => g.estado === 'ABIERTO'));
  protected idMatricula: number | null = null;

  protected readonly form = this.fb.nonNullable.group({
    idPersona: [null as number | null, [Validators.required]],
    periodo: ['', [Validators.required, Validators.maxLength(20)]],
    detalles: this.fb.nonNullable.array([this.crearDetalle()]),
  });

  constructor() {
    this.grupoService.listar().subscribe({
      next: (g) => this.grupos.set(g),
      error: (e) => this.error.set(mensajeError(e, 'No se pudieron cargar los grupos.')),
    });
    // Un estudiante solo se matricula a si mismo
    const propia = this.auth.tieneRol('ESTUDIANTE') && !this.auth.tieneRol('ADMIN', 'COORDINADOR');
    if (propia && this.auth.idPersona()) {
      this.form.controls.idPersona.setValue(this.auth.idPersona());
    } else {
      this.personaService.listar().subscribe({
        next: (p) => this.estudiantes.set(p.filter((x) => x.tipo === 'ESTUDIANTE')),
      });
    }

    const parametro = this.route.snapshot.paramMap.get('id');
    if (parametro) {
      this.idMatricula = Number(parametro);
      this.service.obtener(this.idMatricula).subscribe({
        next: (m) => {
          this.detalles.clear();
          m.detalles.forEach((d) => this.detalles.push(this.crearDetalle(d.idSeccion, d.creditos)));
          this.form.patchValue({ idPersona: m.idPersona, periodo: m.periodo });
        },
        error: (e) => this.error.set(mensajeError(e, 'No se pudo cargar la matricula.')),
      });
    }
  }

  protected get detalles() {
    return this.form.controls.detalles;
  }

  protected get matriculaPropia(): boolean {
    return this.estudiantes().length === 0 && this.form.controls.idPersona.value === this.auth.idPersona();
  }

  private crearDetalle(idGrupo: number | null = null, creditos: number | null = null) {
    return this.fb.nonNullable.group({
      idSeccion: [idGrupo, [Validators.required]],
      creditos: [creditos, [Validators.required, Validators.min(1)]],
    });
  }

  // Al elegir un grupo se completan los creditos del curso y el periodo
  protected alElegirGrupo(index: number): void {
    const id = Number(this.detalles.at(index).controls.idSeccion.value);
    const grupo = this.grupos().find((g) => g.id === id);
    if (!grupo) {
      return;
    }
    this.detalles.at(index).controls.creditos.setValue(grupo.curso?.creditos ?? null);
    if (!this.form.controls.periodo.value) {
      this.form.controls.periodo.setValue(grupo.periodo);
    }
  }

  protected agregarDetalle(): void {
    this.detalles.push(this.crearDetalle());
  }

  protected quitarDetalle(index: number): void {
    this.detalles.removeAt(index);
  }

  protected get totalCreditos(): number {
    return this.detalles.getRawValue().reduce((s, d) => s + Number(d.creditos ?? 0), 0);
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
      idPersona: Number(valores.idPersona),
      periodo: valores.periodo,
      detalles: valores.detalles.map((d) => ({ idSeccion: Number(d.idSeccion), creditos: Number(d.creditos) })),
    };
    const peticion = this.idMatricula
      ? this.service.actualizar(this.idMatricula, request)
      : this.service.crear(request);
    peticion.subscribe({
      next: () => this.router.navigate(['/matriculas']),
      error: (e) => {
        this.error.set(mensajeError(e, 'No se pudo guardar la matricula.'));
        this.enviando.set(false);
      },
    });
  }
}
