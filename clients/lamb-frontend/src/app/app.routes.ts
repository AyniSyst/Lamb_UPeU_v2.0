import { Routes } from '@angular/router';
import { AuthCallback } from './core/auth/auth-callback';
import { authGuard } from './core/auth/auth-guard';
import { rolGuard } from './core/auth/rol-guard';
import { Layout } from './core/layout/layout';

export const routes: Routes = [
  {
    path: 'auth/callback',
    component: AuthCallback,
  },
  {
    path: '',
    component: Layout,
    children: [
      {
        path: '',
        loadComponent: () => import('./features/inicio/inicio').then((m) => m.Inicio),
      },
      {
        path: 'postulaciones',
        loadComponent: () =>
          import('./features/postulaciones/postulacion-list').then((m) => m.PostulacionList),
        canActivate: [authGuard],
      },
      {
        path: 'postulaciones/nueva',
        loadComponent: () =>
          import('./features/postulaciones/postulacion-form').then((m) => m.PostulacionForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR')],
      },
      {
        path: 'mi-trayectoria',
        loadComponent: () =>
          import('./features/trayectoria/mi-trayectoria').then((m) => m.MiTrayectoria),
        canActivate: [authGuard],
      },
      {
        path: 'personas',
        loadComponent: () => import('./features/personas/persona-list').then((m) => m.PersonaList),
        canActivate: [authGuard],
      },
      {
        path: 'personas/nueva',
        loadComponent: () => import('./features/personas/persona-form').then((m) => m.PersonaForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR')],
      },
      {
        path: 'personas/:id/editar',
        loadComponent: () => import('./features/personas/persona-form').then((m) => m.PersonaForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR')],
      },
      {
        path: 'facultades',
        loadComponent: () =>
          import('./features/facultades/facultad-list').then((m) => m.FacultadList),
        canActivate: [authGuard],
      },
      {
        path: 'facultades/nueva',
        loadComponent: () =>
          import('./features/facultades/facultad-form').then((m) => m.FacultadForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR')],
      },
      {
        path: 'programas',
        loadComponent: () => import('./features/programas/programa-list').then((m) => m.ProgramaList),
        canActivate: [authGuard],
      },
      {
        path: 'programas/nueva',
        loadComponent: () => import('./features/programas/programa-form').then((m) => m.ProgramaForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR')],
      },
      {
        path: 'cursos',
        loadComponent: () => import('./features/cursos/curso-list').then((m) => m.CursoList),
        canActivate: [authGuard],
      },
      {
        path: 'secciones',
        loadComponent: () => import('./features/secciones/seccion-list').then((m) => m.SeccionList),
        canActivate: [authGuard],
      },
      {
        path: 'secciones/nueva',
        loadComponent: () => import('./features/secciones/seccion-form').then((m) => m.SeccionForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR')],
      },
      {
        path: 'planes-estudio',
        loadComponent: () =>
          import('./features/planes-estudio/plan-estudio-list').then((m) => m.PlanEstudioList),
        canActivate: [authGuard],
      },
      {
        path: 'planes-estudio/nueva',
        loadComponent: () =>
          import('./features/planes-estudio/plan-estudio-form').then((m) => m.PlanEstudioForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR')],
      },
      {
        path: 'matriculas',
        loadComponent: () =>
          import('./features/matriculas/matricula-list').then((m) => m.MatriculaList),
        canActivate: [authGuard],
      },
      {
        path: 'matriculas/nueva',
        loadComponent: () =>
          import('./features/matriculas/matricula-form').then((m) => m.MatriculaForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR', 'ESTUDIANTE')],
      },
      {
        path: 'notas',
        loadComponent: () => import('./features/notas/nota-list').then((m) => m.NotaList),
        canActivate: [authGuard],
      },
      {
        path: 'notas/nueva',
        loadComponent: () => import('./features/notas/nota-form').then((m) => m.NotaForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'DOCENTE')],
      },
      {
        path: 'actas',
        loadComponent: () => import('./features/actas/acta-list').then((m) => m.ActaList),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR', 'DOCENTE')],
      },
      {
        path: 'pagos',
        loadComponent: () => import('./features/pagos/pago-list').then((m) => m.PagoList),
        canActivate: [authGuard],
      },
      {
        path: 'pagos/nueva',
        loadComponent: () => import('./features/pagos/pago-form').then((m) => m.PagoForm),
        canActivate: [authGuard, rolGuard('ADMIN', 'COORDINADOR')],
      },
    ],
  },
];
