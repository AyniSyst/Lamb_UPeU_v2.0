import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth-service';
import { MICROSERVICIOS } from './ms-catalogo';

@Component({
  selector: 'app-microservicios-inicio',
  imports: [RouterLink],
  template: `
    <h2>Lamb UPeU</h2>
    @if (!auth.autenticado()) {
      <p class="error">Inicia sesion (arriba a la derecha) para operar los microservicios.</p>
    }
    <p>
      Flujo academico: Personas → Facultades y Programas → Postulaciones (admision) → Planes de estudio → Grupos →
      Matriculas → Pagos → Notas → Actas.
    </p>
    <h3>Microservicios</h3>
    <div class="tarjetas">
      @for (m of microservicios; track m.clave) {
        <a class="tarjeta" [routerLink]="['/ms', m.clave]">
          <strong>{{ m.nombre }}</strong>
          <span>{{ m.descripcion }}</span>
          <small class="ayuda">{{ m.recursos.length }} recursos</small>
        </a>
      }
    </div>
  `,
  styles: `
    .tarjetas {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(230px, 1fr));
      gap: 0.75rem;
    }
    .tarjeta {
      display: grid;
      gap: 0.3rem;
      padding: 0.8rem;
      background: #fff;
      border: 1px solid var(--borde);
      border-radius: 6px;
      color: var(--texto);
      text-decoration: none;
    }
    .tarjeta:hover {
      border-color: var(--azul);
    }
    .tarjeta strong {
      color: var(--azul);
    }
  `,
})
export class MicroserviciosInicio {
  protected readonly auth = inject(AuthService);
  protected readonly microservicios = MICROSERVICIOS;
}
