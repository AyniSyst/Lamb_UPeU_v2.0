export function decodeJwtPayload<T = Record<string, unknown>>(token: string): T {
  const payload = token.split('.')[1];
  const normalizado = payload.replace(/-/g, '+').replace(/_/g, '/');
  const json = decodeURIComponent(
    atob(normalizado)
      .split('')
      .map((c) => '%' + c.charCodeAt(0).toString(16).padStart(2, '0'))
      .join(''),
  );
  return JSON.parse(json) as T;
}
