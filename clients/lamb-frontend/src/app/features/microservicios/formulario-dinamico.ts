import { Component, computed, effect, input, output } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { convertir, Fila, valorParaInput } from './ms-api.service';
import { Campo } from './ms.model';

/** Formulario construido a partir de los campos del catalogo (DTO Request del backend). */
@Component({
  selector: 'app-formulario-dinamico',
  imports: [ReactiveFormsModule],
  templateUrl: './formulario-dinamico.html',
})
export class FormularioDinamico {
  readonly campos = input.required<Campo[]>();
  readonly inicial = input<Fila | null>(null);
  readonly textoBoton = input('Guardar');
  readonly enviando = input(false);
  readonly enviar = output<Fila>();
  readonly cancelar = output<void>();

  protected readonly form = computed(() => {
    const grupo = new FormGroup<Record<string, FormControl>>({});
    for (const campo of this.campos()) {
      grupo.addControl(campo.nombre, new FormControl<unknown>(this.valorInicial(campo), this.validadores(campo)));
    }
    return grupo;
  });

  constructor() {
    effect(() => {
      const fila = this.inicial();
      const form = this.form();
      if (fila) {
        for (const campo of this.campos()) {
          form.controls[campo.nombre]?.setValue(valorParaInput(campo, this.buscarValor(fila, campo.nombre)));
        }
      }
    });
  }

  private valorInicial(campo: Campo): unknown {
    if (campo.tipo === 'checkbox') {
      return campo.requerido;
    }
    if (campo.tipo === 'json') {
      return campo.ejemplo ?? '';
    }
    if (campo.tipo === 'select' && campo.requerido) {
      return campo.opciones?.[0] ?? '';
    }
    return '';
  }

  // Al editar, el response puede traer idX o un objeto x con id (facultad -> facultadId)
  private buscarValor(fila: Fila, nombre: string): unknown {
    if (nombre in fila) {
      return fila[nombre];
    }
    const sinId = nombre.replace(/^id([A-Z])/, (_, l: string) => l.toLowerCase()).replace(/Id$/, '');
    const anidado = fila[sinId];
    if (anidado && typeof anidado === 'object' && 'id' in anidado) {
      return (anidado as Fila)['id'];
    }
    return undefined;
  }

  private validadores(campo: Campo) {
    const v = [];
    if (campo.requerido && campo.tipo !== 'checkbox') {
      v.push(Validators.required);
    }
    if (campo.max) {
      v.push(Validators.maxLength(campo.max));
    }
    if (campo.tipo === 'email') {
      v.push(Validators.email);
    }
    if (campo.tipo === 'json') {
      v.push((c: AbstractControl): ValidationErrors | null => {
        if (!c.value) {
          return null;
        }
        try {
          JSON.parse(c.value);
          return null;
        } catch {
          return { json: true };
        }
      });
    }
    return v;
  }

  protected tipoInput(campo: Campo): string {
    switch (campo.tipo) {
      case 'number':
      case 'decimal':
        return 'number';
      case 'datetime':
        return 'datetime-local';
      case 'date':
      case 'time':
      case 'email':
        return campo.tipo;
      default:
        return 'text';
    }
  }

  protected guardar(): void {
    const form = this.form();
    if (form.invalid) {
      form.markAllAsTouched();
      return;
    }
    const valores: Fila = {};
    for (const campo of this.campos()) {
      valores[campo.nombre] = convertir(campo, form.controls[campo.nombre].value);
    }
    this.enviar.emit(valores);
  }
}
