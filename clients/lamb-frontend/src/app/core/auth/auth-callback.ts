import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from './auth-service';

@Component({
  selector: 'app-auth-callback',
  template: '<p>Completando inicio de sesion...</p>',
})
export class AuthCallback implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly auth = inject(AuthService);

  async ngOnInit(): Promise<void> {
    const params = this.route.snapshot.queryParamMap;
    const code = params.get('code');
    const state = params.get('state');

    if (!code || !state) {
      await this.router.navigateByUrl('/');
      return;
    }

    try {
      await this.auth.completarLogin(code, state);
      await this.router.navigateByUrl(this.auth.destinoPendiente());
    } catch (error) {
      console.error('No se pudo completar el login:', error);
      await this.router.navigateByUrl('/');
    }
  }
}
