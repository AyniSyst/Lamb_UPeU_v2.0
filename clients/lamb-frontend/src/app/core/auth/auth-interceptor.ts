import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, from, switchMap, throwError } from 'rxjs';
import { AuthService } from './auth-service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = inject(AuthService);
  const conToken = (token: string | null) =>
    token ? req.clone({ setHeaders: { Authorization: `Bearer ${token}` } }) : req;

  // Renueva el token antes de enviar si esta por vencer (dura 5 min en Keycloak)
  return from(auth.tokenVigente()).pipe(
    switchMap((token) => next(conToken(token))),
    catchError((error: HttpErrorResponse) => {
      if (error.status !== 401) {
        return throwError(() => error);
      }
      // Un 401 con token aun "vigente" (reloj desfasado, sesion revocada): un reintento con token renovado
      return from(auth.renovar()).pipe(
        switchMap((token) => {
          if (!token) {
            auth.iniciarLogin(location.pathname);
            return throwError(() => error);
          }
          return next(conToken(token));
        }),
      );
    }),
  );
};
