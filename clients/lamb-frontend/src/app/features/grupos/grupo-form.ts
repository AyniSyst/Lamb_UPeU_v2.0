import { mensajeError } from '../../core/http/mensaje-error';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Ambiente, CatalogosService, OfertaAcademica, PeriodoAcademico } from '../../core/catalogos/catalogos-service';
import { CursoService } from '../cursos/curso-service';
import { CursoResponse } from '../cursos/curso.model';
import { PersonaService } from '../personas/persona-service';
import { PersonaResponse } from '../personas/persona.model';
import { ProgramaService } from '../programas/programa-service';
import { ProgramaResponse } from '../programas/programa.model';
import { GrupoService } from './grupo-service';
import { DIAS_SEMANA, DiaSemana, ROLES_DOCENTE, RolDocente } from './grupo.model';

@Component({
  selector: 'app-grupo-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './grupo-form.html',
})
export class GrupoForm {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(GrupoService);
  private readonly catalogos = inject(CatalogosService);
  private readonly cursoService = inject(CursoService);
  private readonly programaService = inject(ProgramaService);
  private readonly personaService = inject(PersonaService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  protected readonly error = signal<string | null>(null);
  protected readonly enviando = signal(false);
  protected readonly ofertas = signal<OfertaAcademica[]>([]);
  protected readonly ambientes = signal<Ambiente[]>([]);
  protected readonly docentes = signal<PersonaResponse[]>([]);
  protected readonly periodos = signal<PeriodoAcademico[]>([]);
  protected readonly programas = signal<ProgramaResponse[]>([]);
  protected readonly cursos = signal<CursoResponse[]>([]);
  protected readonly creandoOferta = signal(false);
  protected readonly dias = DIAS_SEMANA;
  protected readonly roles = ROLES_DOCENTE;
  protected idGrupo: number | null = null;

  protected readonly form = this.fb.nonNullable.group({
    idOferta: [null as number | null, [Validators.required]],
    codigo: ['G1', [Validators.required, Validators.maxLength(20)]],
    cupoMaximo: [30, [Validators.required, Validators.min(1)]],
    horarios: this.fb.nonNullable.array([this.crearHorario()]),
    docentes: this.fb.nonNullable.array([this.crearDocente()]),
  });

  protected readonly ofertaForm = this.fb.nonNullable.group({
    idPeriodo: [null as number | null, [Validators.required]],
    idPrograma: [null as number | null, [Validators.required]],
    idCurso: [null as number | null, [Validators.required]],
    ciclo: [1, [Validators.required, Validators.min(1), Validators.max(14)]],
  });

  constructor() {
    this.cargarOfertas();
    this.catalogos.ambientes().subscribe((a) => this.ambientes.set(a.filter((x) => x.activo !== false)));
    this.catalogos.periodos().subscribe((p) => this.periodos.set(p));
    this.programaService.listar().subscribe({ next: (p) => this.programas.set(p) });
    this.cursoService.listar().subscribe({ next: (c) => this.cursos.set(c) });
    this.personaService.listar().subscribe({ next: (p) => this.docentes.set(p.filter((x) => x.tipo === 'DOCENTE')) });

    const parametro = this.route.snapshot.paramMap.get('id');
    if (parametro) {
      this.idGrupo = Number(parametro);
      this.service.obtener(this.idGrupo).subscribe({
        next: (grupo) => {
          this.horarios.clear();
          grupo.horarios.forEach((h) => this.horarios.push(this.crearHorario(h)));
          this.docentesForm.clear();
          grupo.docentes.forEach((d) => this.docentesForm.push(this.crearDocente(d)));
          this.form.patchValue({ idOferta: grupo.idOferta, codigo: grupo.codigo, cupoMaximo: grupo.cupoMaximo });
        },
        error: (e) => this.error.set(mensajeError(e, 'No se pudo cargar el grupo.')),
      });
    }
  }

  protected get horarios() {
    return this.form.controls.horarios;
  }

  protected get docentesForm() {
    return this.form.controls.docentes;
  }

  private crearHorario(h?: { idAmbiente: number; diaSemana: DiaSemana; horaInicio: string; horaFin: string }) {
    return this.fb.nonNullable.group({
      idAmbiente: [h?.idAmbiente ?? (null as number | null), [Validators.required]],
      diaSemana: [h?.diaSemana ?? ('LUNES' as DiaSemana), [Validators.required]],
      horaInicio: [h?.horaInicio?.slice(0, 5) ?? '08:00', [Validators.required]],
      horaFin: [h?.horaFin?.slice(0, 5) ?? '10:00', [Validators.required]],
    });
  }

  private crearDocente(d?: { idDocente: number | null; rol: RolDocente; horasSemanales: number }) {
    return this.fb.nonNullable.group({
      idDocente: [d?.idDocente ?? (null as number | null), [Validators.required]],
      rol: [d?.rol ?? ('TITULAR' as RolDocente), [Validators.required]],
      horasSemanales: [d?.horasSemanales ?? 4, [Validators.required, Validators.min(1), Validators.max(40)]],
    });
  }

  protected agregarHorario(): void {
    this.horarios.push(this.crearHorario());
  }

  protected quitarHorario(i: number): void {
    this.horarios.removeAt(i);
  }

  protected agregarDocente(): void {
    this.docentesForm.push(this.crearDocente({ idDocente: null, rol: 'PRACTICA', horasSemanales: 2 }));
  }

  protected quitarDocente(i: number): void {
    this.docentesForm.removeAt(i);
  }

  private cargarOfertas(seleccionar?: number): void {
    this.catalogos.ofertas().subscribe((o) => {
      this.ofertas.set(o.filter((x) => x.estado !== 'CERRADA'));
      if (seleccionar) {
        this.form.controls.idOferta.setValue(seleccionar);
      }
    });
  }

  protected guardarOferta(): void {
    if (this.ofertaForm.invalid) {
      this.ofertaForm.markAllAsTouched();
      return;
    }
    const v = this.ofertaForm.getRawValue();
    this.catalogos
      .crearOferta({
        idPeriodo: Number(v.idPeriodo),
        idPrograma: Number(v.idPrograma),
        idCurso: Number(v.idCurso),
        ciclo: Number(v.ciclo),
        estado: 'APROBADA',
      })
      .subscribe({
        next: (oferta) => {
          this.creandoOferta.set(false);
          this.cargarOfertas(oferta.id);
        },
        error: (e) => this.error.set(mensajeError(e, 'No se pudo crear la oferta academica.')),
      });
  }

  protected guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    if (!this.docentesForm.getRawValue().some((d) => d.rol === 'TITULAR')) {
      this.error.set('El grupo necesita un docente TITULAR: sin el, Evaluacion rechaza el acta y el grupo queda ANULADO.');
      return;
    }
    this.error.set(null);
    this.enviando.set(true);
    const v = this.form.getRawValue();
    const request = {
      idOferta: Number(v.idOferta),
      codigo: v.codigo,
      cupoMaximo: Number(v.cupoMaximo),
      horarios: v.horarios.map((h) => ({ ...h, idAmbiente: Number(h.idAmbiente) })),
      docentes: v.docentes.map((d) => ({ ...d, idDocente: Number(d.idDocente), horasSemanales: Number(d.horasSemanales) })),
    };
    const peticion = this.idGrupo ? this.service.actualizar(this.idGrupo, request) : this.service.crear(request);
    peticion.subscribe({
      next: () => this.router.navigate(['/grupos']),
      error: (e) => {
        this.error.set(mensajeError(e, 'No se pudo guardar el grupo.'));
        this.enviando.set(false);
      },
    });
  }
}
