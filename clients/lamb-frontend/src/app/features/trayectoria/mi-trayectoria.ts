import { Component, OnDestroy, OnInit, inject, signal } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { TrayectoriaService } from './trayectoria-service';
import { TrayectoriaResponse } from './trayectoria.model';

const INTERVALO_POLLING_MS = 4000;

@Component({
  selector: 'app-mi-trayectoria',
  templateUrl: './mi-trayectoria.html',
})
export class MiTrayectoria implements OnInit, OnDestroy {
  private readonly service = inject(TrayectoriaService);
  private intervaloId: ReturnType<typeof setInterval> | null = null;

  protected readonly trayectoria = signal<TrayectoriaResponse | null>(null);
  protected readonly pendiente = signal(false);
  protected readonly error = signal<string | null>(null);

  ngOnInit(): void {
    this.consultar();
    this.intervaloId = setInterval(() => this.consultar(), INTERVALO_POLLING_MS);
  }

  ngOnDestroy(): void {
    if (this.intervaloId !== null) {
      clearInterval(this.intervaloId);
    }
  }

  private consultar(): void {
    this.service.obtenerMia().subscribe({
      next: (data) => {
        this.trayectoria.set(data);
        this.pendiente.set(false);
        this.error.set(null);
        if (this.intervaloId !== null) {
          clearInterval(this.intervaloId);
          this.intervaloId = null;
        }
      },
      error: (err: HttpErrorResponse) => {
        if (err.status === 404) {
          this.pendiente.set(true);
          this.error.set(null);
        } else {
          this.error.set('No se pudo consultar la trayectoria.');
        }
      },
    });
  }
}
