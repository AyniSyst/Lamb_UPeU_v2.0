import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../core/auth/auth-service';
import { PostulacionService } from './postulacion-service';
import { EvaluarPostulacionRequest, PostulacionResponse } from './postulacion.model';

@Component({
  selector: 'app-postulacion-list',
  templateUrl: './postulacion-list.html',
})
export class PostulacionList {
  private readonly service = inject(PostulacionService);
  protected readonly auth = inject(AuthService);

  protected readonly postulaciones = signal<PostulacionResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);
  protected readonly puntajes = signal<Record<number, number>>({});

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.postulaciones.set(data),
      error: () => this.error.set('No se pudieron cargar las postulaciones.'),
    });
  }

  protected actualizarPuntaje(id: number, valor: string): void {
    this.puntajes.update((actual) => ({ ...actual, [id]: Number(valor) }));
  }

  protected evaluar(postulacion: PostulacionResponse): void {
    this.errorAccion.set(null);
    const puntaje = this.puntajes()[postulacion.id];
    if (puntaje === undefined || Number.isNaN(puntaje)) {
      this.errorAccion.set('Ingresa un puntaje valido antes de evaluar.');
      return;
    }
    const request: EvaluarPostulacionRequest = { puntajeExamen: puntaje };
    this.service.evaluar(postulacion.id, request).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo evaluar la postulacion.'),
    });
  }

  protected admitir(postulacion: PostulacionResponse): void {
    this.errorAccion.set(null);
    this.service.admitir(postulacion.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo admitir la postulacion (revisa el puntaje minimo de 11).'),
    });
  }

  protected rechazar(postulacion: PostulacionResponse): void {
    this.errorAccion.set(null);
    this.service.rechazar(postulacion.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo rechazar la postulacion.'),
    });
  }
}
