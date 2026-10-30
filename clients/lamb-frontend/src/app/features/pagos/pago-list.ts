import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../core/auth/auth-service';
import { PagoService } from './pago-service';
import { PagoResponse } from './pago.model';

@Component({
  selector: 'app-pago-list',
  templateUrl: './pago-list.html',
})
export class PagoList {
  private readonly service = inject(PagoService);
  protected readonly auth = inject(AuthService);

  protected readonly pagos = signal<PagoResponse[]>([]);
  protected readonly error = signal<string | null>(null);
  protected readonly errorAccion = signal<string | null>(null);

  constructor() {
    this.cargar();
  }

  protected cargar(): void {
    this.error.set(null);
    this.service.listar().subscribe({
      next: (data) => this.pagos.set(data),
      error: () => this.error.set('No se pudieron cargar los pagos.'),
    });
  }

  protected reembolsar(pago: PagoResponse): void {
    this.errorAccion.set(null);
    this.service.reembolsar(pago.id).subscribe({
      next: () => this.cargar(),
      error: () => this.errorAccion.set('No se pudo reembolsar el pago.'),
    });
  }
}
