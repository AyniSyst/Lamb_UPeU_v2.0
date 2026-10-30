import { mensajeError } from '../../core/http/mensaje-error';
import { Component, computed, effect, inject, input, signal } from '@angular/core';
import { JsonPipe } from '@angular/common';
import { Observable } from 'rxjs';
import { AuthService } from '../../core/auth/auth-service';
import { FormularioDinamico } from './formulario-dinamico';
import { Fila, MsApiService } from './ms-api.service';
import { Campo, Microservicio, Operacion, Recurso } from './ms.model';

type Modo =
  | { tipo: 'crear' }
  | { tipo: 'editar'; fila: Fila }
  | { tipo: 'operacion'; op: Operacion; fila: Fila | null; campos: Campo[] };

const MAX_COLUMNAS = 9;
const MAX_FILAS = 200;

/** CRUD generico de un recurso del catalogo: listar, crear, editar, eliminar, acciones y consultas. */
@Component({
  selector: 'app-recurso-crud',
  imports: [FormularioDinamico, JsonPipe],
  templateUrl: './recurso-crud.html',
})
export class RecursoCrud {
  private readonly api = inject(MsApiService);
  protected readonly auth = inject(AuthService);

  readonly ms = input.required<Microservicio>();
  readonly recurso = input.required<Recurso>();

  protected readonly filas = signal<Fila[]>([]);
  protected readonly cargando = signal(false);
  protected readonly enviando = signal(false);
  protected readonly error = signal<string | null>(null);
  protected readonly exito = signal<string | null>(null);
  protected readonly resultado = signal<unknown>(null);
  protected readonly modo = signal<Modo | null>(null);
  protected readonly filtro = signal('');
  protected readonly filtrosListar = signal<Fila>({});

  protected readonly columnas = computed(() => {
    const claves: string[] = [];
    for (const fila of this.filas().slice(0, 20)) {
      for (const clave of Object.keys(fila)) {
        if (!claves.includes(clave)) {
          claves.push(clave);
        }
      }
    }
    const orden = (c: string) => (c === 'id' ? 0 : /^(codigo|nombre)/.test(c) ? 1 : /^estado/.test(c) ? 2 : 3);
    return claves.sort((a, b) => orden(a) - orden(b)).slice(0, MAX_COLUMNAS);
  });

  protected readonly filasVisibles = computed(() => {
    const texto = this.filtro().trim().toLowerCase();
    const filas = texto ? this.filas().filter((f) => JSON.stringify(f).toLowerCase().includes(texto)) : this.filas();
    return filas.slice(0, MAX_FILAS);
  });

  protected readonly puedeEscribir = computed(() => this.auth.tieneRol('ADMIN', 'COORDINADOR', 'DOCENTE', 'ESTUDIANTE'));

  constructor() {
    // Cambiar de recurso reinicia la vista
    effect(() => {
      this.recurso();
      this.modo.set(null);
      this.resultado.set(null);
      this.error.set(null);
      this.exito.set(null);
      this.filtro.set('');
      this.filtrosListar.set({});
      this.cargar();
    });
  }

  protected cargar(): void {
    const recurso = this.recurso();
    if (!recurso.listar) {
      this.filas.set([]);
      return;
    }
    this.cargando.set(true);
    this.api.listar(this.ms(), recurso, this.filtrosListar()).subscribe({
      next: (data) => {
        this.filas.set(Array.isArray(data) ? (data as Fila[]) : data ? [data as Fila] : []);
        this.cargando.set(false);
      },
      error: (e) => {
        this.error.set(mensajeError(e, 'No se pudo listar.'));
        this.filas.set([]);
        this.cargando.set(false);
      },
    });
  }

  protected cambiarFiltroListar(nombre: string, valor: string): void {
    this.filtrosListar.update((f) => ({ ...f, [nombre]: valor }));
  }

  protected celda(valor: unknown): string {
    if (valor === null || valor === undefined || valor === '') {
      return '-';
    }
    if (Array.isArray(valor)) {
      return valor.length ? `${valor.length} elemento(s)` : '-';
    }
    if (typeof valor === 'object') {
      const o = valor as Fila;
      return String(o['codigo'] ?? o['nombre'] ?? o['id'] ?? JSON.stringify(o).slice(0, 40));
    }
    if (typeof valor === 'boolean') {
      return valor ? 'Si' : 'No';
    }
    const texto = String(valor);
    return /^\d{4}-\d{2}-\d{2}T/.test(texto) ? texto.slice(0, 16).replace('T', ' ') : texto;
  }

  protected esEstado(columna: string): boolean {
    return /^estado/i.test(columna);
  }

  // --- Crear / editar ---

  protected nuevo(): void {
    this.limpiarMensajes();
    this.modo.set({ tipo: 'crear' });
  }

  protected editar(fila: Fila): void {
    this.limpiarMensajes();
    this.modo.set({ tipo: 'editar', fila });
  }

  protected camposFormulario(): Campo[] {
    const modo = this.modo();
    if (modo?.tipo === 'editar') {
      return this.recurso().actualizar?.campos ?? this.recurso().crear?.campos ?? [];
    }
    if (modo?.tipo === 'operacion') {
      return modo.campos;
    }
    return this.recurso().crear?.campos ?? [];
  }

  protected tituloFormulario(): string {
    const modo = this.modo();
    if (!modo) {
      return '';
    }
    if (modo.tipo === 'crear') {
      return `Nuevo: ${this.recurso().etiqueta}`;
    }
    if (modo.tipo === 'editar') {
      return `Editar ${this.recurso().etiqueta} #${modo.fila['id']}`;
    }
    return modo.fila ? `${modo.op.etiqueta} · #${modo.fila['id']}` : modo.op.etiqueta;
  }

  protected inicialFormulario(): Fila | null {
    const modo = this.modo();
    return modo?.tipo === 'editar' ? modo.fila : null;
  }

  protected enviarFormulario(valores: Fila): void {
    const modo = this.modo();
    if (!modo) {
      return;
    }
    if (modo.tipo === 'crear') {
      this.ejecutarEscritura(this.api.crear(this.ms(), this.recurso(), this.cuerpo(valores)), 'Registro creado.');
    } else if (modo.tipo === 'editar') {
      this.ejecutarEscritura(
        this.api.actualizar(this.ms(), this.recurso(), modo.fila['id'], this.cuerpo(valores)),
        `Registro #${modo.fila['id']} actualizado.`,
      );
    } else {
      this.ejecutarOperacion(modo.op, modo.fila, valores);
    }
  }

  // Un request que no es un objeto (lista o JSON libre) viaja en el campo __cuerpo
  private cuerpo(valores: Fila): unknown {
    return '__cuerpo' in valores ? valores['__cuerpo'] : valores;
  }

  protected eliminar(fila: Fila): void {
    if (!confirm(`Eliminar ${this.recurso().etiqueta} #${fila['id']}?`)) {
      return;
    }
    this.limpiarMensajes();
    this.ejecutarEscritura(this.api.eliminar(this.ms(), this.recurso(), fila['id']), `Registro #${fila['id']} eliminado.`);
  }

  // --- Acciones de fila y consultas del recurso ---

  protected abrirOperacion(op: Operacion, fila: Fila | null): void {
    this.limpiarMensajes();
    // En una accion de fila la primera {variable} es el id de la fila; las demas se piden
    const varsPedidas = fila ? op.vars.slice(1) : op.vars;
    const campos: Campo[] = [
      ...varsPedidas.map((c) => ({ ...c, nombre: `__v_${c.nombre}`, requerido: true })),
      ...op.params.map((c) => ({ ...c, nombre: `__p_${c.nombre}` })),
      ...op.campos,
    ];
    if (!campos.length) {
      if (op.metodo === 'DELETE' && !confirm(`${op.etiqueta} #${fila?.['id']}?`)) {
        return;
      }
      this.ejecutarOperacion(op, fila, {});
      return;
    }
    this.modo.set({ tipo: 'operacion', op, fila, campos });
  }

  private ejecutarOperacion(op: Operacion, fila: Fila | null, valores: Fila): void {
    const vars: Fila = {};
    const params: Fila = {};
    const cuerpo: Fila = {};
    if (fila && op.vars.length) {
      vars[op.vars[0].nombre] = fila['id'];
    }
    for (const [clave, valor] of Object.entries(valores)) {
      if (clave.startsWith('__v_')) {
        vars[clave.slice(4)] = valor;
      } else if (clave.startsWith('__p_')) {
        params[clave.slice(4)] = valor;
      } else {
        cuerpo[clave] = valor;
      }
    }
    const peticion = this.api.ejecutar(this.ms(), this.recurso(), op, { vars, params, cuerpo: this.cuerpo(cuerpo) });
    if (op.metodo === 'GET') {
      this.enviando.set(true);
      peticion.subscribe({
        next: (r) => {
          this.resultado.set(r);
          this.enviando.set(false);
          this.modo.set(null);
        },
        error: (e) => {
          this.error.set(mensajeError(e, `No se pudo ejecutar "${op.etiqueta}".`));
          this.enviando.set(false);
        },
      });
      return;
    }
    this.ejecutarEscritura(peticion, `${op.etiqueta}: hecho.`);
  }

  private ejecutarEscritura(peticion: Observable<unknown>, ok: string): void {
    this.enviando.set(true);
    this.error.set(null);
    peticion.subscribe({
      next: () => {
        this.exito.set(ok);
        this.enviando.set(false);
        this.modo.set(null);
        this.cargar();
      },
      error: (e) => {
        this.error.set(mensajeError(e, 'La operacion no se completo.'));
        this.enviando.set(false);
      },
    });
  }

  protected limpiarMensajes(): void {
    this.error.set(null);
    this.exito.set(null);
    this.resultado.set(null);
  }
}
