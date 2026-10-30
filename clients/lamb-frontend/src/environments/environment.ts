export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:18080',
  keycloak: {
    issuer: 'http://localhost:18180/realms/lamb',
    clientId: 'lamb-frontend',
    redirectUri: 'http://localhost:4200/auth/callback',
    logoutRedirectUri: 'http://localhost:4200/',
  },
};
