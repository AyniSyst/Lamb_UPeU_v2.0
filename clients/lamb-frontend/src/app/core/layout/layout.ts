import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AuthService } from '../auth/auth-service';

@Component({
  selector: 'app-layout',
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './layout.html',
  styleUrl: './layout.css',
})
export class Layout {
  protected readonly auth = inject(AuthService);

  // Solo nombre y clave: el catalogo completo se carga con la pagina del microservicio
  protected readonly microservicios = [
    { clave: 'personas', nombre: 'Personas' },
    { clave: 'institucional', nombre: 'Institucional' },
    { clave: 'admision', nombre: 'Admision' },
    { clave: 'curriculo', nombre: 'Curriculo' },
    { clave: 'planificacion', nombre: 'Planificacion' },
    { clave: 'matricula', nombre: 'Matricula' },
    { clave: 'evaluacion', nombre: 'Evaluacion' },
    { clave: 'trayectoria', nombre: 'Trayectoria' },
    { clave: 'finanzas', nombre: 'Finanzas' },
    { clave: 'ingresos-pagos', nombre: 'Ingresos y Pagos' },
    { clave: 'lms', nombre: 'LMS' },
  ];

  protected iniciarLogin(): void {
    this.auth.iniciarLogin(location.pathname);
  }
}
