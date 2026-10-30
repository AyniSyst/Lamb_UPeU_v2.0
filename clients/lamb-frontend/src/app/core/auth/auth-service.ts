import { Injectable, computed, signal } from '@angular/core';
import { environment } from '../../../environments/environment';
import { decodeJwtPayload } from './jwt';
import { generarCodeChallenge, generarCodeVerifier, generarState } from './pkce';

interface Sesion {
  accessToken: string;
  refreshToken: string;
  idToken: string;
  expiraEn: number;
}

interface TokenClaims {
  sub: string;
  idPersona?: number;
  preferred_username?: string;
  realm_access?: { roles?: string[] };
}

const KEY_VERIFIER = 'lamb.pkce.verifier';
const KEY_STATE = 'lamb.pkce.state';
const KEY_DESTINO = 'lamb.pkce.destino';
const KEY_SESION = 'lamb.sesion';
// Margen para renovar antes de que Keycloak rechace el token (accessTokenLifespan = 300 s)
const MARGEN_RENOVACION_MS = 30_000;

@Injectable({ providedIn: 'root' })
export class AuthService {
  // sessionStorage: la sesion sobrevive a un F5 pero no a cerrar la pestana
  private readonly sesion = signal<Sesion | null>(this.leerSesionGuardada());
  private renovando: Promise<string | null> | null = null;
  private readonly claims = computed<TokenClaims | null>(() => {
    const token = this.sesion()?.accessToken;
    return token ? decodeJwtPayload<TokenClaims>(token) : null;
  });

  readonly autenticado = computed(() => this.sesion() !== null);
  readonly accessToken = computed(() => this.sesion()?.accessToken ?? null);
  readonly roles = computed(() => this.claims()?.realm_access?.roles ?? []);
  readonly nombreUsuario = computed(() => this.claims()?.preferred_username ?? null);

  // idPersona es la PK real de Persona en lamb-personas-ms, reutilizada por 6 microservicios.
  // Es un claim aparte de `sub` (keycloak/README.md) — nunca se colapsa a sub como en pagatu S07b.
  readonly idPersona = computed(() => this.claims()?.idPersona ?? null);

  tieneRol(...rolesEsperados: string[]): boolean {
    const propios = this.roles();
    return rolesEsperados.some((r) => propios.includes(r));
  }

  async iniciarLogin(destino: string): Promise<void> {
    const verifier = generarCodeVerifier();
    const challenge = await generarCodeChallenge(verifier);
    const state = generarState();

    sessionStorage.setItem(KEY_VERIFIER, verifier);
    sessionStorage.setItem(KEY_STATE, state);
    sessionStorage.setItem(KEY_DESTINO, destino);

    const params = new URLSearchParams({
      client_id: environment.keycloak.clientId,
      redirect_uri: environment.keycloak.redirectUri,
      response_type: 'code',
      scope: 'openid',
      code_challenge: challenge,
      code_challenge_method: 'S256',
      state,
    });

    location.href = `${environment.keycloak.issuer}/protocol/openid-connect/auth?${params.toString()}`;
  }

  async completarLogin(code: string, stateRecibido: string): Promise<void> {
    const stateEsperado = sessionStorage.getItem(KEY_STATE);
    const verifier = sessionStorage.getItem(KEY_VERIFIER);

    if (!stateEsperado || stateRecibido !== stateEsperado) {
      throw new Error('El parametro state no coincide: posible CSRF o pestana reusada.');
    }
    if (!verifier) {
      throw new Error('No se encontro code_verifier en sessionStorage.');
    }

    const body = new URLSearchParams({
      grant_type: 'authorization_code',
      client_id: environment.keycloak.clientId,
      code,
      redirect_uri: environment.keycloak.redirectUri,
      code_verifier: verifier,
    });

    // fetch(), no HttpClient: todavia no hay token que adjuntar y authInterceptor no debe intervenir aqui.
    const respuesta = await fetch(`${environment.keycloak.issuer}/protocol/openid-connect/token`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: body.toString(),
    });

    if (!respuesta.ok) {
      throw new Error('Keycloak rechazo el intercambio del code por un token.');
    }

    this.guardarSesion(await respuesta.json());

    sessionStorage.removeItem(KEY_VERIFIER);
    sessionStorage.removeItem(KEY_STATE);
  }

  /**
   * Token listo para enviar: si esta por vencer lo renueva con el refresh_token.
   * Las peticiones concurrentes comparten una sola renovacion.
   */
  async tokenVigente(): Promise<string | null> {
    const actual = this.sesion();
    if (!actual) {
      return null;
    }
    if (actual.expiraEn - Date.now() > MARGEN_RENOVACION_MS) {
      return actual.accessToken;
    }
    return this.renovar();
  }

  renovar(): Promise<string | null> {
    this.renovando ??= this.pedirRenovacion().finally(() => (this.renovando = null));
    return this.renovando;
  }

  private async pedirRenovacion(): Promise<string | null> {
    const refreshToken = this.sesion()?.refreshToken;
    if (!refreshToken) {
      return null;
    }
    const body = new URLSearchParams({
      grant_type: 'refresh_token',
      client_id: environment.keycloak.clientId,
      refresh_token: refreshToken,
    });
    const respuesta = await fetch(`${environment.keycloak.issuer}/protocol/openid-connect/token`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: body.toString(),
    });
    if (!respuesta.ok) {
      // Sesion de Keycloak vencida (ssoSessionIdleTimeout): hay que volver a iniciar sesion
      this.borrarSesion();
      return null;
    }
    this.guardarSesion(await respuesta.json());
    return this.sesion()?.accessToken ?? null;
  }

  private guardarSesion(datos: { access_token: string; refresh_token: string; id_token?: string; expires_in: number }): void {
    const sesion: Sesion = {
      accessToken: datos.access_token,
      refreshToken: datos.refresh_token,
      idToken: datos.id_token ?? this.sesion()?.idToken ?? '',
      expiraEn: Date.now() + datos.expires_in * 1000,
    };
    this.sesion.set(sesion);
    sessionStorage.setItem(KEY_SESION, JSON.stringify(sesion));
  }

  private borrarSesion(): void {
    this.sesion.set(null);
    sessionStorage.removeItem(KEY_SESION);
  }

  private leerSesionGuardada(): Sesion | null {
    try {
      const guardada = sessionStorage.getItem(KEY_SESION);
      return guardada ? (JSON.parse(guardada) as Sesion) : null;
    } catch {
      return null;
    }
  }

  destinoPendiente(): string {
    const destino = sessionStorage.getItem(KEY_DESTINO) ?? '/';
    sessionStorage.removeItem(KEY_DESTINO);
    return destino;
  }

  cerrarSesion(): void {
    const idToken = this.sesion()?.idToken;
    this.borrarSesion();
    const params = new URLSearchParams({
      post_logout_redirect_uri: environment.keycloak.logoutRedirectUri,
      ...(idToken ? { id_token_hint: idToken } : {}),
    });
    location.href = `${environment.keycloak.issuer}/protocol/openid-connect/logout?${params.toString()}`;
  }
}
