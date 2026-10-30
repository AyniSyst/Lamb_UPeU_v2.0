import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth-service';

export function rolGuard(...roles: string[]): CanActivateFn {
  return () => {
    const auth = inject(AuthService);
    const router = inject(Router);
    if (auth.tieneRol(...roles)) {
      return true;
    }
    alert('No tienes el rol necesario para acceder a esta pantalla.');
    return router.createUrlTree(['/']);
  };
}
